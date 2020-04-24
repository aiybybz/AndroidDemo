package com.demon.aidl.service;
import com.demon.aidl.service.User;

interface UserApi {

    List<User> getUserList();

    void addUserInOut(inout User user);

    void addUserIn(in User user);

    void addUserOut(out User user);

}