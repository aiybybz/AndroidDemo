package com.demon.service.download;

/**
 * @author Demon
 * @version 1.0
 * @Description 下载监听
 * @DATE 2019/10/25 10
 */
public interface DownloadListener {

    /**
     * 通知下载进度
     * @param progress 进度
     */
    void onProgress(int progress);

    /**
     * 通知下载成功
     */
    void onSuccess();

    /**
     * 通知下载失败
     */
    void onFailed();

    /**
     * 通知下载暂停
     */
    void onPaused();

    /**
     * 通知下载取消
     */
    void onCanceled();

}
