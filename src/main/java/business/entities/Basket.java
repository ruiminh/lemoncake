package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketEntry> cupcakeList = new ArrayList<>();
    private int orderId;

    public Basket() {

    }

    public List<BasketEntry> getCupcakeList(){
        return cupcakeList;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCupcakeNumber(){
        return cupcakeList.size();
    }

    public void addToCart(BasketEntry basketEntry){
        cupcakeList.add(basketEntry);
    }

    public void removeFromCart(int cupcakeListIndex){
        cupcakeList.remove(cupcakeListIndex);
    }

    public int totalPrice()
    {
        int sum= 0;
        for(BasketEntry basketEntry:cupcakeList)
        {
            sum+=basketEntry.getPrice();
        }
        return sum;

    }
}
