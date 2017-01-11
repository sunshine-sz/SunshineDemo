package com.sunshine;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sunshine.service.SendService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String RESULT_KEY = "remote_input";
    public static final int NOTIFICATION_ID = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }


    private void initWidget() {
        findViewById(R.id.bt_send).setOnClickListener(this);
    }

    private Notification buildNotification() {
        //创建RemoteInput
        RemoteInput remoteInput = new RemoteInput.Builder(RESULT_KEY).setLabel("回复通知").build();
        // 创建pendingintent, 当发送时调用什么
        Intent intent = new Intent(this, SendService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        // 创建快捷回复 Action
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,"回复",pendingIntent).addRemoteInput(remoteInput).build();
        // 创建notification，使用设置优先级的方式创建悬浮通知，则会自动消失
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("请问需要银行贷款吗?")
                .setContentText("您好，我是XX银行的XX经理， 请问你需要办理银行贷款吗？")
                .setColor(Color.CYAN)
                .setPriority(Notification.PRIORITY_MAX) // 设置优先级为Max，则为悬浮通知
                .addAction(action) // 设置回复action
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL) // 想要悬浮出来， 这里必须要设置
                .setCategory(Notification.CATEGORY_MESSAGE);

        return builder.build();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        NotificationManager nm = getSystemService(NotificationManager.class);
        Notification notification = buildNotification();
        nm.notify(NOTIFICATION_ID,notification);
    }
}
