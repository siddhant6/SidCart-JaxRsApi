package pojos;

import exceptions.InvalidRequestException;

public class BasketItem {
    
    private int basketId;
    
    private int productId;
    
    private int quantity;
    
    public void validate() throws InvalidRequestException {
        if(this.productId == 0 || this.quantity == 0 || this.basketId == 0) {
            throw new InvalidRequestException("Invalid Input.");
        }
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getBasketId() {
        return basketId;
    }
    
    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }
}
