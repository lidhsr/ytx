package com.ytx.data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/20.
 */
public class Shop extends Entity implements Entity.Builder<Shop> {

    private static Shop shop;

    public int id;
    public String name;
    public String pic;
    public ArrayList<Product> products = new ArrayList<>();

    public static Builder<Shop> getInfo() {
        if(null == shop) {
            shop = new Shop();
        }
        return shop;
    }

    @Override
    public Shop create(JSONObject jsonObject) {
        Shop shop = new Shop();
        shop.id = jsonObject.optInt("id");
        shop.name = jsonObject.optString("name");
        shop.pic = jsonObject.optString("pic");
        shop.products = new ArrayList<>();
        JSONArray productArray = jsonObject.optJSONArray("products");
        if(null != productArray) {
            int size = productArray.length();
            for(int i=0; i<size; i++) {
                JSONObject obj = productArray.optJSONObject(i);
                shop.products.add(Product.getInfo().create(obj));
            }
        }

        return shop;
    }
}
