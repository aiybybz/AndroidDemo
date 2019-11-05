package com.demon.service.download;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Demon
 * @version 1.0
 * @Description : 下载任务 <String, Integer, Integer>：指定任务内容，进度单位，执行结果
 * @DATE 2019/10/25 14:09
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    // AsyncTask<Params, Progress, Result>
    //   Params：在执行AsyncTask时需要传人的参数，可用于在后台任务中使用。
    //   Progress：后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
    //   Result：当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。

    /**
     * 下载成功
     */
    public static final int TYPE_SUCCESS = 0;
    /**
     * 下载失败
     */
    public static final int TYPE_FAILED = 1;
    /**
     * 暂停下载
     */
    public static final int TYPE_PAUSED = 2;
    /**
     * 取消下载
     */
    public static final int TYPE_CANCELED = 3;


    private DownloadListener listener;

    private int lastProgress;

    private boolean isPaused = false;

    private boolean isCanceled = false;


    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    /**
     * 这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
     * 任务一旦完成就可以通过return语句来将任务的执行结果返回，如果AsyncTask的第三个泛型参数指定的是Void,就可以不返回任务执行结果。
     * 注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress(Progress... )方法来完成。
     * @param params String[]
     * @return Integer
     */
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            // 记录已下载的文件长度
            long downloadedLength = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                // 已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                // 跳过已下载的字节
                savedFile.seek(downloadedLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已下载的百分比
                        int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                        // 通知更新UI
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    /**
     * 当在后台任务中调用了publishProgress(Progress...)方法后，
     * onProgressUpdate(Progress...)方法就会很快被调用，该方法中携带的参数就是在后台任务中传递过来的。
     * 在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元索进行相应的更新。
     * @param values 进度
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    /**
     * 当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用。
     * 返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，比如说提醒任务执行的结果，以及关闭掉进度条对话框等。
     * @param status 状态值
     */
    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }

}