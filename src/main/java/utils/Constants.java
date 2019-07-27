package utils;

public class Constants {
    
    /*
     * Queries
     */
    
    public static final String GET_PRODUCT_BY_ID = "select * from products where product_id = ?;";
    public static final String UPDATE_PRODUCT_QUANTITY = "update products set quantity = ? where product_id = ?;";
    public static final String INSERT_BASKET_ITEM = "insert into basket_item values (default,?,?,?)";
    public static final String GET_ITEMS_BY_BASKET = "select * from basket_item where basket_id = ?;";
    public static final String GET_ITEM_BY_ITEM_ID = "select * from basket_item where basket_item_id = ?;";
    public static final String DELETE_ITEM_BY_ITEM_ID = "delete * from basket_item where basket_item_id = ?;";
    public static final String GET_BASKET_BY_USER_ID = "select * from basket where userid = ?;";
    
    
}
