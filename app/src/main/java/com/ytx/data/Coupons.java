package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class Coupons extends Entity implements Entity.Builder<Coupons> {

    private static Coupons coupons;

    public int id; //优惠券id
    public int shopId; // 优惠券所属品牌id
    public double price;//优惠券价格
    public String title;//优惠券标题
    public String intro;//优惠券说明
    public String invalidTime;//失效时间
    public int status;//状态 1为有效

    public static Builder<Coupons> getInfo() {
        if(null == coupons) {
            coupons = new Coupons();
        }
        return coupons;
    }

    @Override
    public Coupons create(JSONObject jsonObject) {
        Coupons product = new Coupons();
        product.id = jsonObject.optInt("id");
        product.shopId = jsonObject.optInt("shopId");
        product.status = jsonObject.optInt("status");
        product.price = jsonObject.optDouble("price");
        product.title = jsonObject.optString("title");
        product.intro = jsonObject.optString("intro");
        product.invalidTime = jsonObject.optString("invalidTime");
        return product;
    }
}
