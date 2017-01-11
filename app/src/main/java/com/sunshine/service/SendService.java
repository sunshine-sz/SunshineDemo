package com.sunshine.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.RemoteInput;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.MainActivity;
import com.sunshine.R;

public class SendService extends Service {
    public SendService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 获取RemoteInput中的Result
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        //根据key拿回复的内容
        if (null!=resultsFromIntent){
            String resultString = resultsFromIntent.getString(MainActivity.RESULT_KEY);
            reply(resultString);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void reply(final String resultString) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                Logger.e(SendService.class.getSimpleName(),resultString);
                onReply();
            }
        }).start();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void onReply() {
        final NotificationManager nm = getSystemService(NotificationManager.class);
        final Handler handler = new Handler(getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 更新通知为“回复成功”
                Notification notification = new NotificationCompat.Builder(SendService.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentText("回复成功")
                        .build();
                nm.notify(MainActivity.NOTIFICATION_ID, notification);
            }
        });

        // 最后将通知取消
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nm.cancel(MainActivity.NOTIFICATION_ID);
            }
        }, 2000);
    }
}
