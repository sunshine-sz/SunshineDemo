package com.fitsleep.sunshinelibrary.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitsleep.sunshinelibrary.R;


/**
 * @author Sunshine
 * @Description dialog相关工具类
 */
public class DialogUtils {
    /**
     * 加载对话框
     *
     * @param context 上下文
     * @param message 文本信息
     * @return dialog对象
     */
    public static Dialog getLoadingDialog(Context context, String message) {
        Dialog dialog = new Dialog(context, R.style.no_title);
        View view = View.inflate(context, R.layout.dialog_load, null);
        if (!TextUtils.isEmpty(message)){
            ((TextView) view.findViewById(R.id.tv_message)).setText(message);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.startAnimation(AlertAnimateUtil.getRotateAnimation());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }

    public static void cropPhoto(Context context, Uri uri,int requestCode) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

}
