package services;

import exceptions.InvalidRequestException;
import pojos.BasketItem;
import utils.Constants;
import utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketService {
    
    
    public void addItemToBasket(BasketItem item) throws Exception {

        Connection connection = SqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Constants.GET_PRODUCT_BY_ID);
        preparedStatement.setString(1,String.valueOf(item.getProductId()));
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Integer quantity = resultSet.getInt(2);
        System.out.println(quantity);

        if(quantity < item.getQuantity()) {
            throw new InvalidRequestException("Not enough quantity available");
        }

        int updatedQuantity = quantity - item.getQuantity();

        /*
         * Subtracting quantity of product.
         */

        preparedStatement = connection.prepareStatement(Constants.UPDATE_PRODUCT_QUANTITY);
        preparedStatement.setInt(1,updatedQuantity);
        preparedStatement.setInt(2,item.getProductId());
        preparedStatement.executeUpdate();

        /*
         * Adding item to basket
         */

        preparedStatement = connection.prepareStatement(Constants.INSERT_BASKET_ITEM);
        preparedStatement.setInt(1,item.getQuantity());
        preparedStatement.setInt(2,item.getProductId());
        preparedStatement.setInt(3,item.getBasketId());
        preparedStatement.execute();

        System.out.println("Added to DB.");
    }
    
    public List<Map<String,Object>> getBasketItems(int basketId) throws SQLException {
        
        Connection con = SqlConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(Constants.GET_ITEMS_BY_BASKET);
        preparedStatement.setString(1,String.valueOf(basketId));
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<Map<String,Object>> responseMap = new ArrayList<>();
        while(resultSet.next()) {
            Map<String,Object> innerMap = new HashMap<>();
            innerMap.put("product_id",resultSet.getInt("product_id"));
            innerMap.put("item_quantity",resultSet.getInt("item_quantity"));
            responseMap.add(innerMap);
        }
        
        return responseMap;
    }
    
    public void deleteBasketItem(int itemId) throws SQLException, InvalidRequestException {
    
        Connection con = SqlConnection.getConnection();
    
        PreparedStatement preparedStatement = con.prepareStatement(Constants.GET_ITEM_BY_ITEM_ID);
        preparedStatement.setString(1,String.valueOf(itemId));
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if(!resultSet.next()) {
            throw new InvalidRequestException("Basket item not found with given id.");
        }
        
        preparedStatement = con.prepareStatement(Constants.DELETE_ITEM_BY_ITEM_ID);
        preparedStatement.setString(1,String.valueOf(itemId));
        preparedStatement.execute();
        
    }
    
    public List<Map<String,Object>> getBasketFromUserId(int userId) throws SQLException, InvalidRequestException {
    
        Connection con = SqlConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement(Constants.GET_BASKET_BY_USER_ID);
        preparedStatement.setString(1,String.valueOf(userId));
        ResultSet resultSet = preparedStatement.executeQuery();
        int basketId = 0;
    
        if(!resultSet.next()) {
            throw new InvalidRequestException("User not found with given id.");
        }else {
            basketId = resultSet.getInt(1);
        }
        
        
        return getBasketItems(basketId);
    }
}
