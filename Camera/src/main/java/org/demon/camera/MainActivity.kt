package org.demon.camera

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import androidx.databinding.DataBindingUtil
import permissions.dispatcher.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    // 使用委托 懒加载 获取binding
    private val binding by lazy { DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.activity_main) }

    // 用于保存拍照图片的uri
    private var mCameraUri: Uri? = null

    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private var mCameraImagePath: String? = null

    // 是否是Android 10以上手机
    private var isAndroidQ = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    // 相机回调
    private val CAMERA_REQ_CODE = 0x00000012


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initBinding()
        initListener()
    }

    private fun initBinding() {
        // 管理生命周期，可感知 Activity 生命周期，保证数据可见时才会更新
        binding.lifecycleOwner = this
        binding.data = "https://game.gtimg.cn/images/lol/act/img/skin/big5005.jpg"
    }

    private fun initListener() {
        // 绑定监听
        binding.handler = EventHandler()
    }

    inner class EventHandler {
        fun onClick(v: View) {
            when (v.id) {
                R.id.button -> showCameraWithPermissionCheck()
            }
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        val hasCameraPermission = ContextCompat.checkSelfPermission(application, Manifest.permission.CAMERA)
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 有调起相机拍照
            openCamera()
        } else {
            toast("没有相机权限")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationaleForCamera(request: PermissionRequest) {
        AlertDialog.Builder(this)
            .setMessage("需要相机权限，否则不能拍照")
            .setPositiveButton("取消") { _, _ -> request.cancel() }
            .setNegativeButton("授权") { _, _ -> request.proceed() }
            .show()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
        toast("无法获得权限")
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
        AlertDialog.Builder(this)
            .setMessage("应用权限被拒绝，为了不影响您的正常使用，请在“权限”中开启“相机”权限！")
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

    // 打开相机
    fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (captureIntent.resolveActivity(packageManager) != null) {
            val photoFile: File?
            var photoUri: Uri? = null

            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri()
            } else {
                photoFile = createImageFile()

                if (photoFile != null) {
                    mCameraImagePath = photoFile.absolutePath
                    photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        // 适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        FileProvider.getUriForFile(this, "$packageName.fileProvider", photoFile)
                    } else {
                        Uri.fromFile(photoFile)
                    }
                }
            }

            mCameraUri = photoUri
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                startActivityForResult(captureIntent, CAMERA_REQ_CODE)
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private fun createImageUri(): Uri? {
        val status = Environment.getExternalStorageState()
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (status == Environment.MEDIA_MOUNTED) {
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())
        } else {
            contentResolver.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, ContentValues())
        }
    }

    /**
     * 创建保存图片的文件
     */
    private fun createImageFile(): File? {
        val imageName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir?.exists() == false) {
            storageDir.mkdir()
        }
        val tempFile = File(storageDir, imageName)
        if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            return null
        }
        return tempFile
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                if (isAndroidQ) {
                    // Android 10 使用图片uri加载
                    binding.data = mCameraUri
                } else {
                    // 使用图片路径加载
                    binding.data = mCameraImagePath
                }
            } else {
                toast("取消")
            }
        }
    }
}
