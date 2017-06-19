package com.jtvr.jtInterface;


public interface DownloadListener {


    /**
     * 通知当前的下载进度 获取已下载文件的大小 和文件大小的总数
     * @param progress
     */
    void onProgress(int progress, int total, int contentLength);

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
     * 通知下载取消事件
     */
    void onCanceled();

}
