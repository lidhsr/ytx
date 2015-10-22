package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class ActivityInfo extends Entity implements Entity.Builder<ActivityInfo> {

    private static ActivityInfo shop;

    public int id;
    public String name;
    public String type;

    public static Builder<ActivityInfo> getInfo() {
        if(null == shop) {
            shop = new ActivityInfo();
        }
        return shop;
    }

    @Override
    public ActivityInfo create(JSONObject jsonObject) {
        ActivityInfo shop = new ActivityInfo();
        shop.id = jsonObject.optInt("id");
        shop.name = jsonObject.optString("name");
        shop.type = jsonObject.optString("type");
        return shop;
    }
}
