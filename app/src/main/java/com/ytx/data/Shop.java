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

    public int id;  // 品牌id
    public String name; //品牌名称
    public String pic; // 品牌图标，icon，小图标
    public int coupon; //是否有优惠券，1表示有
    public ArrayList<Product> products = new ArrayList<Product>(); //品牌下的商品
    public ArrayList<ActivityInfo> activityInfo = new ArrayList<>();// 品牌下所有优惠活动

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
        shop.coupon = jsonObject.optInt("coupon");
        shop.pic = jsonObject.optString("pic");
        shop.products = new ArrayList<>();
        JSONArray productArray = jsonObject.optJSONArray("products");
        if(null != productArray) {
            int size = productArray.length();
            shop.products = new ArrayList<>();
            for(int i=0; i<size; i++) {
                JSONObject obj = productArray.optJSONObject(i);
                shop.products.add(Product.getInfo().create(obj));
            }
        }

        JSONArray activityInfo = jsonObject.optJSONArray("activityInfo");
        if(null != activityInfo) {
            int size = activityInfo.length();
            shop.activityInfo = new ArrayList<>();
            for(int i=0; i<size; i++) {
                JSONObject obj = activityInfo.optJSONObject(i);
                shop.activityInfo.add(ActivityInfo.getInfo().create(obj));
            }
        }

        return shop;
    }
}
