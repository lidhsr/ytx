package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class Product extends Entity implements Entity.Builder<Product> {

    private static Product product;

    public int id;
    public String pName;
    public String pic;
    public String size;
    public String color;
    public String[] sizes;
    public String[] colors;
    public boolean isChecked = false;
    public boolean editable  = false;
    public int productNum = 1;
    public double price = 0;

    public static Builder<Product> getInfo() {
        if(null == product) {
            product = new Product();
        }
        return product;
    }

    @Override
    public Product create(JSONObject jsonObject) {
        Product product = new Product();
        product.id = jsonObject.optInt("id");
        product.pName = jsonObject.optString("pName");
        product.pic = jsonObject.optString("pic");
        product.size = jsonObject.optString("size");
        product.color = jsonObject.optString("color");
        product.productNum = jsonObject.optInt("productNum");
        product.price = jsonObject.optDouble("price");
        String sizesObj = jsonObject.optString("sizes");
        product.sizes = sizesObj.split(",");
        String colorsObj = jsonObject.optString("colors");
        product.colors = colorsObj.split(",");
        return product;
    }
}
