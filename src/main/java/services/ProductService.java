package services;

import utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    
    public List<Map<String,Object>> getAllProducts() throws SQLException {
    
        Connection con = SqlConnection.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("select * from products ;");
        ResultSet resultSet = preparedStatement.executeQuery();
    
        List<Map<String,Object>> responseList = new ArrayList<>();
        
        while(resultSet.next()) {
            Map<String,Object> productMap = new HashMap<>();
            productMap.put("productId",resultSet.getInt(1));
            productMap.put("productName",resultSet.getString(2));
            productMap.put("quantity",resultSet.getInt(3));
            responseList.add(productMap);
        }
        
        return responseList;
    }
}
