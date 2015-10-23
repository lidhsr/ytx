package com.ytx.manager;

/**
 * Created by Augustus on 15/10/23.
 */
public class UserManager {

    private static class SingletonHolder {

        private static final UserManager INSTANCE = new UserManager();
    }

    public static final UserManager getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
