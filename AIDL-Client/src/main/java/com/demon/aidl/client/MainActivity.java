package com.demon.aidl.client;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.demon.aidl.service.User;
import com.demon.aidl.service.UserApi;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : AIDL 测试
 * @date : 2020/4/21
 */
public class MainActivity extends AppCompatActivity {

    /**
     * AIDL 接口
     */
    private UserApi api;
    /**
     * 链接状态
     */
    private boolean connected;
    /**
     * 用户列表
     */
    private List<User> userList;

    /**
     * 链接服务
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            api = UserApi.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindService();
    }

    /**
     * 绑定服务
     */
    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.demon.aidl.service");
        intent.setAction("com.demon.aidl.service.api");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    @OnClick({R.id.btn_getBookList, R.id.btn_addBook_inOut, R.id.btn_addBook_in, R.id.btn_addBook_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getBookList:
                if (connected) {
                    try {
                        userList = api.getUserList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Logger.d("AIDL-Client: " + userList.toString());
                }
                break;
            case R.id.btn_addBook_inOut:
                if (connected) {
                    User user = new User("新增人，张三", "zs", true);
                    try {
                        api.addUserInOut(user);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_addBook_in:
                if (connected) {
                    User user = new User("新增人，李四 In", "lsi", true);
                    try {
                        api.addUserIn(user);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_addBook_out:
                if (connected) {
                    User user = new User("新增人，王五 Out", "wwo", true);
                    try {
                        api.addUserOut(user);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
