package bean;

/**
 * Created by 小黑 on 2019/8/15.
 */
public class ShopCartData {
    private String image;
    private String price;
    private String commodityName;
    private int number;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ShopCartData(String image, String price, String commodityName, int number) {
        this.image = image;
        this.price = price;
        this.commodityName = commodityName;
        this.number = number;
    }

    public ShopCartData() {
    }
}
