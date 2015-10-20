package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/20.
 */
public class Shop extends Entity implements Entity.Builder<Shop> {

    private static Shop test;

    public String name;
    public ArrayList<Product> pList = new ArrayList<Product>();

    public static Builder<Shop> getInfo() {
        if(null == test) {
            test = new Shop();
        }
        return test;
    }

    @Override
    public Shop create(JSONObject jsonObject) {
        Shop test = new Shop();
        test.name = jsonObject.optString("name");

        return test;
    }
}
