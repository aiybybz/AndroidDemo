package com.demon.aidl.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 用户
 * @date : 2020/4/21
 */
public class User implements Parcelable {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 标识
     */
    private boolean mark;

    public User() {

    }

    public User(String userName, String password, boolean mark) {
        this.userName = userName;
        this.password = password;
        this.mark = mark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mark=" + mark +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeByte((byte) (mark ? 1 : 0));
    }

    public void readFromParcel(Parcel dest) {
        userName = dest.readString();
        password = dest.readString();
        mark = dest.readByte() != 0;
    }

    protected User(Parcel in) {
        userName = in.readString();
        password = in.readString();
        mark = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
