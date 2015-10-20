package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class Product extends Entity implements Entity.Builder<Product> {

    private static Product test;

    public String pName;

    public static Builder<Product> getInfo() {
        if(null == test) {
            test = new Product();
        }
        return test;
    }

    @Override
    public Product create(JSONObject jsonObject) {
        Product test = new Product();
        test.pName = jsonObject.optString("pName");

        return test;
    }
}
