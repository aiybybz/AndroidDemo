package com.demon.aidl.client;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : Application
 * @date : 2020/4/22
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
