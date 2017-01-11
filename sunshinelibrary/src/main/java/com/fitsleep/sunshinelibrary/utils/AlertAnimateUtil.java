package com.fitsleep.sunshinelibrary.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.fitsleep.sunshinelibrary.R;


/**
 * @author Sunshine
 * @Description  动画相关工具类
 */
public class AlertAnimateUtil {
    private static final int INVALID = -1;

    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.fade_in_center : R.anim.fade_out_center;
        }
        return INVALID;
    }

    /**
     * 给View加点击动画，利用开源库nineoldandroids设置动画
     *
     * @param view
     */
    public static void addAnimation(View view) {
        float[] vaules = new float[]{0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.1f, 1.2f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f, 1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules),
                ObjectAnimator.ofFloat(view, "scaleY", vaules));
        set.setDuration(150);
        set.start();
    }

    /**
     * 获取旋转动画
     *
     * @return
     */
    public static RotateAnimation getRotateAnimation() {
        RotateAnimation ra = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        ra.setInterpolator(linearInterpolator);
        // 永远不停止 一致运行
        ra.setRepeatCount(Animation.INFINITE);
        return ra;
    }

    /**
     * 隐藏和显示动画
     *
     * @return
     */
    public static TranslateAnimation getVisible() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        return mShowAction;
    }

    /**
     * 旋转一圈动画
     *
     * @return
     */
    public static RotateAnimation upRtAnimation(float fromDegrees, float toDegrees, long duration, boolean fillAfter) {
        RotateAnimation upRt = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upRt.setDuration(duration);
        upRt.setFillAfter(fillAfter);
        return upRt;

    }
}
