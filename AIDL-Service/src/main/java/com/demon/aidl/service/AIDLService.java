package com.demon.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : AIDL 服务（为 AIDL 提供接口服务）
 * @date : 2020/4/21
 */
public class AIDLService extends Service {

    private List<User> userList = new ArrayList<>();

    UserApi.Stub mBinder = new UserApi.Stub() {
        @Override
        public List<User> getUserList() {
            Logger.d("AIDL-Service: getUserList");
            return userList;
        }

        @Override
        public void addUserInOut(User user) {
            if (user != null) {
                userList.add(user);
                Logger.d("AIDL-Service: addUserInOut " + user.toString());
            } else {
                Logger.d("AIDL-Service: addUserInOut 接收到了一个空对象");
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("AIDL-Service: onCreate()");
        initData();
    }

    private void initData() {
        User userK = new User("Kassadin", "k", true);
        User userT = new User("Talon", "t", true);
        User userG = new User("Galio", "g", true);
        User userE = new User("Ekko", "e", false);
        User userD = new User("Diana", "d", true);
        User userR = new User("Ryze", "r", true);
        userList.add(userK);
        userList.add(userT);
        userList.add(userG);
        userList.add(userE);
        userList.add(userD);
        userList.add(userR);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("AIDL-Service: onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.d("AIDL-Service: onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.d("AIDL-Service: onBind()");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.d("AIDL-Service: onUnbind()");
        return super.onUnbind(intent);
    }
}
