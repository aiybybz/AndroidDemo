package org.demon.permissions

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import permissions.dispatcher.*

const val GPS_REQUEST_CODE = 100

/**
 * @author : Demon
 * @version : 1.0
 * @Description : 运行时授权
 * @date : 2020/9/1
 */
@RuntimePermissions // 1.授权类
class MainActivity : AppCompatActivity() {

    private var gpsStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLocationWithPermissionCheck()
    }

    // 2.低功耗蓝牙需要 LOCATION 权限
    @NeedsPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun showLocation() {
        if (checkGpsIsOpen()) {
            gpsStatus = true
            toast("GPS已打开")
        } else {
            gpsStatus = false
            AlertDialog.Builder(this)
                .setMessage("需要打开GPS，否则不能链接锁具")
                .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("进入设置") { _, _ ->
                    // 跳转到手机原生设置页面
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivityForResult(intent, GPS_REQUEST_CODE)
                }
                .setCancelable(false)
                .show()
        }
    }

    // 3.向用户说明为什么需要这些权限（可选）
    @OnShowRationale(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun showRationaleForLocation(request: PermissionRequest) {
        AlertDialog.Builder(this)
            .setMessage("需要位置权限，否则不能链接锁具")
            .setPositiveButton("取消") { _, _ -> request.cancel() }
            .setNegativeButton("授权") { _, _ -> request.proceed() }
            .show()
    }

    // 4.用户拒绝授权回调（可选）
    @OnPermissionDenied(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun onLocationDenied() {
        toast("无法获得权限")
    }

    // 5.用户勾选了“不再提醒”时调用（可选）
    @OnNeverAskAgain(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    fun onLocationNeverAskAgain() {
        AlertDialog.Builder(this)
            .setMessage("应用权限被拒绝，为了不影响您的正常使用，请在“权限”中开启对应权限！")
            .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("进入设置") { dialog, _ ->
                // 引导用户至设置页手动授权
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", this@MainActivity.packageName, null)
                intent.data = uri
                startActivity(intent)
                dialog.dismiss()
            }
            .show()
    }

    // 6.权限回调，调用PermissionsDispatcher的回调方法
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    fun checkGpsIsOpen(): Boolean {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GPS_REQUEST_CODE -> showLocation()
        }
    }
}
