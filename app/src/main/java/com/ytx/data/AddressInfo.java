package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class AddressInfo extends Entity implements Entity.Builder<AddressInfo> {

    private static AddressInfo info;

    public int id; // 地址
    public String name; // 收货人
    public String mobile; //联系电话
    public String address; // 地址
    public int isDefault; // 是否默认地址，1为是
    public boolean isChecked; // 是否已经选择

    public static Builder<AddressInfo> getInfo() {
        if(null == info) {
            info = new AddressInfo();
        }
        return info;
    }

    @Override
    public AddressInfo create(JSONObject jsonObject) {
        AddressInfo info = new AddressInfo();
        info.id = jsonObject.optInt("id");
        info.mobile = jsonObject.optString("mobile");
        info.address = jsonObject.optString("address");
        info.isDefault = jsonObject.optInt("isDefault");
        info.isChecked = false;
        return info;
    }
}
