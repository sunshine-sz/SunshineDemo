package com.fitsleep.sunshinelibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * @author Sunshine
 * @Description Toast相关工具类
 */
public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Context context;

    private static Object synObj = new Object();

    private static Toast toast;

    public static void init(Context context) {
        context = context.getApplicationContext();
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
    }

    /**
     * 带图片的Toast
     *
     * @param context 上下文
     * @param string  提示文字
     */
    public static void ToastImage(Context context, String string, int ris) {
        //先定义一个Toast
        toast.setText(string);
        //定义一个ImageView
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(ris);

        //获得Toast的View
        View toastView = toast.getView();

        //定义一个Layout，这里是Layout
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //将ImageView和ToastView合并到Layout中
        linearLayout.addView(imageView);
        linearLayout.addView(toastView);

        //替换掉原有的ToastView
        toast.setView(linearLayout);
        toast.show();
    }

    /**
     * 吐司显示，字符串。
     *
     * @param msg String文本
     */
    public static void showMessage(final String msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 吐司显示，资源文本。
     *
     * @param msg 资源文本
     */
    public static void showMessage(final int msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    public static void showMessage(final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(context, msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    public static void showMessage(final int msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(len);
                            } else {
                                toast = Toast.makeText(context, msg, len);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }
}
