package business.entities;

public class Bottom {
    int bottomId;
    String name;
    int price;

    public Bottom(int bottomId, String name, int price)
    {

        this.name = name;
        this.price = price;
        this.bottomId = bottomId;
    }



    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(String name) {
        this.bottomId = bottomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
