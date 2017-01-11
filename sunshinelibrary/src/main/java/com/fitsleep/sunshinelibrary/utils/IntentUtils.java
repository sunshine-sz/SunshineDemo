package com.fitsleep.sunshinelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.fitsleep.sunshinelibrary.R;

/**
 * @author Sunshine
 * @Description Intent相关工具类
 */
public class IntentUtils {

    private IntentUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * 放大动画跳转activity，并携带参数
     *
     * @param context   上下文
     * @param descClass 类名
     * @param bundle    携带的参数
     */
    public static void switchZoomActivity(Context context, Class<?> descClass, Bundle bundle) {
        Class<?> mClass = context.getClass();
        if (mClass == descClass) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClass(context, descClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ((Activity) context).startActivityForResult(intent, 0);
            ((Activity) context).overridePendingTransition(R.anim.zoom_in, 0);
        } catch (Exception e) {
        }
    }

    /**
     * 缩小动画关闭Activity
     *
     * @param activity 要关闭的
     */
    public static void finishZoomOut(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(0, R.anim.zoom_out);
    }

    /**
     * 关闭当前activity
     *
     * @param context 上下文
     */
    public static void finish(Activity context) {
        try {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager.isActive()) {
                if (context.getCurrentFocus() != null) {
                    manager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.finish();
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 关闭当前activity,动画向下滑出
     *
     * @param cxt 要关闭的
     */
    public static void finishOut(Activity cxt) {
        cxt.finish();
        cxt.overridePendingTransition(0, R.anim.push_top_out);
    }

    /**
     * 关闭当前activity,并携带返回code
     *
     * @param context    上下文
     * @param intent     携带参数的intent
     * @param resultCode 请求返回的code
     */
    public static void finish(Activity context, Intent intent, int resultCode) {
            context.setResult(resultCode, intent);
            context.finish();
            context.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    /**
     * 跳转页面
     *
     * @param context 上下文
     * @param cls     跳转的页面
     */
    public static void startActivity(Activity context, Class<?> cls) {
        startActivity(context, cls, null);
    }

    /**
     * 跳转activity并携带参数
     *
     * @param context 上下文
     * @param bundle  bundle对象
     * @param action  action
     * @param cls     类名
     */
    public static void startActivity(Activity context, Class<?> cls, String action, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!TextUtils.isEmpty(action)) {
            intent.setAction(action);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 跳转activity并携带参数
     *
     * @param context 上下文
     * @param bundle  bundle对象
     * @param cls     类名
     */
    public static void startActivity(Activity context, Class<?> cls, Bundle bundle) {
        startActivity(context, cls, null, bundle);
    }

    /**
     * 跳转activity，由下向上动画
     *
     * @param context 上下文
     * @param bundle  bundle对象
     * @param cls     类名
     */
    public static void startActivityIn(Activity context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_bow_in, R.anim.push_top_out);
    }

    /**
     * 跳转activity,携带请求参数
     *
     * @param context 上下文
     * @param cls     类名
     * @param code    状态码
     * @param bundle  携带的参数
     */
    public static void startActivityForResult(Activity context, Class<?> cls, int code, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivityForResult(intent, code);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 跳转activity,携带请求参数
     *
     * @param context 上下文
     * @param cls     类名
     * @param code    状态码
     */
    public static void startActivityForResult(Activity context, Class<?> cls, int code) {
        startActivityForResult(context, cls, code, null);
    }

    /**
     * 跳转activity,并关闭当前activity
     *
     * @param context
     * @param cls
     */
    public static void startActivityAndFinish(Activity context, Class<?> cls) {
        startActivityAndFinish(context, cls, null);
    }

    /**
     * 携带参数跳转activity,并关闭当前activity
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivityAndFinish(Activity context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        context.finish();
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 延迟跳转activity
     *
     * @param context
     * @param cls
     * @param delaytime 延迟执行的时间毫�?
     */
    public static void startActivityForDelay(final Activity context, final Class<?> cls, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(context,cls);
            }
        }.start();
    }

    /**
     * 延迟跳转activity，并关闭当前activity
     *
     * @param context
     * @param cls
     * @param delaytime 延迟执行的时间毫�?
     */
    public static void startActivityForDelayAndFinish(final Activity context, final Class<?> cls, final long delaytime) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(delaytime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivityAndFinish(context,cls);
            }

            ;
        }.start();
    }

}
