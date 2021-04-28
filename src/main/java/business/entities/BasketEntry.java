package business.entities;

public class BasketEntry {
    private Bottom bottom;
    private Topping topping;
    private int amount;


    public BasketEntry(Topping topping, Bottom bottom, int amount) {
        this.bottom = bottom;
        this.topping = topping;
        this.amount = amount;
    }


    public Bottom getBottom() {
        return bottom;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return (topping.getPrice() + bottom.getPrice()) * amount;

    }
}
