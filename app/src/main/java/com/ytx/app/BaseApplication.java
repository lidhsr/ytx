package com.ytx.app;

import org.kymjs.kjframe.app.MyApplication;
import org.kymjs.kjframe.bitmap.BitmapConfig;
import org.kymjs.kjframe.http.HttpConfig;

/**
 * Created by Augustus on 15/10/15.
 */
public class BaseApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BitmapConfig.CACHEPATH = AppConfig.imgCachePath;
        HttpConfig.CACHEPATH = AppConfig.httpCachePath;
        //CrashHandler.create(this);

    }

    @Override
    public void exit() {

    }
}
