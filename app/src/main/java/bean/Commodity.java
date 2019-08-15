package bean;

import java.io.Serializable;

/**
 * Created by 小黑 on 2019/8/12.
 */

public class Commodity implements Serializable{
    private String commodityName;
    private double price;
    private String type;
    private String image;
    private String intro;
    public String getCommodityName() {
        return commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public Commodity(String commodityName, double price, String type, String image, String intro) {
        super();
        this.commodityName = commodityName;
        this.price = price;
        this.type = type;
        this.image = image;
        this.intro = intro;
    }
    public Commodity() {
        super();
    }
}
