package com.demon.permissions;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 运行时授权 Demo
 * @date : 2019/11/22 10:55
 */
@RuntimePermissions
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 2.拨打电话需要CALL_PHONE权限
     * === 在此标明注释 ===
     */
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:10010");
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 3.向用户说明为什么需要这些权限（可选）
     * === 在此标明注释 ===
     * @param request 权限
     */
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showCallPhone(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("需要拨打电话权限，否则不能拨打电话！")
                .setPositiveButton("取消", (dialog, button) -> request.cancel())
                .setNegativeButton("授权", (dialog, button) -> request.proceed())
                .show();
    }

    /**
     * 4.用户拒绝授权回调（可选）
     * === 在此标明注释 ===
     */
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void deniedCallPhone() {
        Toast.makeText(MainActivity.this, "无法获得权限", Toast.LENGTH_SHORT).show();
    }

    /**
     * 5.用户勾选了“不再提醒”时调用（可选）
     * === 在此标明注释 ===
     */
    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void neverAskCallPhone() {
        new AlertDialog.Builder(this)
                .setMessage("应用权限被拒绝，为了不影响您的正常使用，请在“权限”中开启对应权限！")
                .setPositiveButton("取消", (dialog, button) -> dialog.dismiss())
                .setNegativeButton("进入设置", (dialog, button) -> {
                    // 引导用户至设置页手动授权
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    dialog.dismiss();
                })
                .show();
    }

    /**
     * 6.权限回调，调用PermissionsDispatcher的回调方法
     * === 在此标明注释 ===
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnClick(R.id.btCall)
    public void onViewClicked() {
        MainActivityPermissionsDispatcher.callPhoneWithPermissionCheck(this);
    }
}
