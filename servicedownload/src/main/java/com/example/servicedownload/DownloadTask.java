package com.example.servicedownload;

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
 * Created by mac_soong on 2017/3/3.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSE = 2;
    private static final int TYPE_CANCEL = 3;

    private boolean isCancel = false;
    private boolean isPause = false;

    private int lastProgreass;

    private DownloadListener listener;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();

        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }


    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        File file = null;
        RandomAccessFile savedFile = null;
        try {
            long downloadLength = 0;
            String downloadUrl = params[0];

            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);

            if (file.exists()) {
                downloadLength = file.length();
            }


            long contentLength = getContentLength(downloadUrl);

            if (downloadLength == contentLength) {    //文件原大小 = 已下载大小  下载成功
                return TYPE_SUCCESS;
            } else if (contentLength == 0) {            //下载失败
                return TYPE_FAILED;
            }

            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);
                byte[] bytes = new byte[1024];
                int total = 0;
                int len;

                while ((len = is.read(bytes)) != -1) {
                    if (isCancel) {
                        return TYPE_CANCEL;
                    }
                    if (isPause) {
                        return TYPE_PAUSE;
                    } else {
                        total += len;
                        savedFile.write(bytes, 0, len);
                        int progress = (int) ((total + downloadLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.close();
                return TYPE_SUCCESS;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCancel && file != null) {
                    file.delete();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgreass) {
            listener.onProgress(progress);     //接口监听
            lastProgreass = progress;
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_CANCEL:
                listener.onCancel();
                break;
            case TYPE_FAILED:
                listener.onFaild();
                break;

            case TYPE_PAUSE:
                listener.onPause();
                break;
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPause = true;
    }

    public void cancelDownload(){
        isCancel = true;
    }
}
