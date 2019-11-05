package com.demon.service;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author Demon
 * @version 1.0
 * @Description Application
 * @DATE 2019/10/17 13
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
