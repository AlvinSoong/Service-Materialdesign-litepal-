package com.example.servicedownload;

/**
 * Created by mac_soong on 2017/3/3.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFaild();
    void onPause();
    void onCancel();
}
