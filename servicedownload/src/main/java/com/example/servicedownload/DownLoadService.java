package com.example.servicedownload;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

/**
  Created by mac_soong on 2017/2/27.
 */

public class DownLoadService extends Service {

    private DownloadTask downloadTask;
    private DownLoadBinder mDownloadBinder = new DownLoadBinder();
    private String downloadUrl;
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("正在下载...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载完成", -1));
            Toast.makeText(DownLoadService.this, "下载完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFaild() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("下载失败", -1));
            Toast.makeText(DownLoadService.this, "下载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownLoadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownLoadService.this, "已取消下载", Toast.LENGTH_SHORT).show();
        }
    };

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);  //第三个参数 不使用模糊进度条
        }
        return builder.build();
    }

    class DownLoadBinder extends Binder {

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);

                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("正在下载",0));
                Toast.makeText(DownLoadService.this, "开始下载", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null){
                downloadTask.cancelDownload();
            }else{
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DownLoadService.this, "下载取消", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBinder;
    }

}
