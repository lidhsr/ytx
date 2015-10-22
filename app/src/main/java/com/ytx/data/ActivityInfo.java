package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class ActivityInfo extends Entity implements Entity.Builder<ActivityInfo> {

    private static ActivityInfo shop;

    public int id; // 品牌活动id
    public String content; // 品牌活动内容
    public String type; //品牌活动类型

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
        shop.content = jsonObject.optString("content");
        shop.type = jsonObject.optString("type");
        return shop;
    }
}
