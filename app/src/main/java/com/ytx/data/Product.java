package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class Product extends Entity implements Entity.Builder<Product> {

    private static Product product;

    public int id; //商品id
    public int shopId; // 商品所属品牌id
    public String pName; //商品名称
    public String pic; //商品图片
    public String size; //用户选择的商品尺寸
    public String color; //用户选择的商品颜色
    public String[] sizes; //商品拥有的所有尺寸
    public String[] colors; //商品拥有的所有颜色
    public int productNum; //商品数量
    public double price;//商品单价
    public boolean isChecked = false;
    public boolean editable  = false;

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
        product.shopId = jsonObject.optInt("shopId");
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
