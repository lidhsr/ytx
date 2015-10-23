package com.ytx.manager;

/**
 * Created by Augustus on 15/10/23.
 */
public class ShopManager {

    private static class SingletonHolder {

        private static final ShopManager INSTANCE = new ShopManager();
    }

    public static final ShopManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
