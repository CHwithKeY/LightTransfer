package com.oji.kreate.lighttransfer;

import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Kreate on 2018/3/24.
 */

public class AnimationUtil {

    static ScaleAnimation becomeLarge() {
        ScaleAnimation large_sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        large_sa.setDuration(100);

        return large_sa;
    }

    public static ScaleAnimation becomeLarge(long duration, Animation.AnimationListener listener) {
        ScaleAnimation large_sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        large_sa.setDuration(duration);
        large_sa.setFillAfter(true);
        large_sa.setAnimationListener(listener);

        return large_sa;
    }

    public static ScaleAnimation becomeLargeTenTimes(long duration, Animation.AnimationListener listener) {
        ScaleAnimation large_tt_sa = new ScaleAnimation(0, 10f, 0, 10f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        large_tt_sa.setDuration(duration);
        large_tt_sa.setFillAfter(true);
        large_tt_sa.setAnimationListener(listener);

        return large_tt_sa;
    }

    public static ScaleAnimation scaleReverseInfinite(long duration) {
        ScaleAnimation large_sa = new ScaleAnimation(0f, 1.0f, 0f, 1.0f,
                Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_SELF);

        large_sa.setDuration(duration);
        large_sa.setFillAfter(true);
        large_sa.setRepeatMode(Animation.REVERSE);
        large_sa.setRepeatCount(Animation.INFINITE);

        return large_sa;
    }

    final static int SCALE_TYPE_LARGE = 1000;
    final static int SCALE_TYPE_SMALL = 1100;

    public static ScaleAnimation scaleCustom(int customType, long duration, float multiple_x, float multiple_y) {

        ScaleAnimation zoom_sa;

        switch (customType) {
            case SCALE_TYPE_LARGE:
                zoom_sa = new ScaleAnimation(0.0f, multiple_x, 0.0f, multiple_y,
                        Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
                break;

            case SCALE_TYPE_SMALL:
                zoom_sa = new ScaleAnimation(multiple_x, 0.0f, multiple_y, 0.0f,
                        Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
                break;

            default:
                zoom_sa = null;
                Log.e(AnimationUtil.class.getName(), "customType参数错误 -- customType parameter is incorrect");
                break;
        }

        if (zoom_sa != null) {
            zoom_sa.setDuration(duration);
        }

        return zoom_sa;
    }

    static ScaleAnimation becomeSmall() {
        ScaleAnimation small_sa = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        small_sa.setDuration(100);

        return small_sa;
    }

    public static ScaleAnimation becomeSmall(long duration) {
        ScaleAnimation small_sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        small_sa.setDuration(duration);

        return small_sa;
    }

    public static ScaleAnimation becomeSmall(Animation.AnimationListener listener) {
        ScaleAnimation switch_sa = new ScaleAnimation(
                1.0f, 0f, 1.0f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        switch_sa.setDuration(150);
        switch_sa.setFillAfter(true);
        switch_sa.setAnimationListener(listener);

        return switch_sa;
    }

    public static AlphaAnimation breathFlash(long duration) {
        AlphaAnimation breath_aa = new AlphaAnimation(0.5f, 1);
        breath_aa.setDuration(duration);
        breath_aa.setRepeatMode(Animation.REVERSE);
        breath_aa.setRepeatCount(Animation.INFINITE);

        return breath_aa;
    }

    public static AlphaAnimation strongFlash(long duration) {
        AlphaAnimation strong_aa = new AlphaAnimation(0.0f, 1);
        strong_aa.setDuration(duration);
        strong_aa.setFillAfter(true);

        return strong_aa;
    }

    public static AlphaAnimation weakFlash(long duration) {
        AlphaAnimation strong_aa = new AlphaAnimation(1f, 0.0f);
        strong_aa.setDuration(duration);
        strong_aa.setFillAfter(true);

        return strong_aa;
    }


    public static AlphaAnimation weakFlash(long duration, Animation.AnimationListener listener) {
        AlphaAnimation weak_aa = new AlphaAnimation(1f, 0.0f);
        weak_aa.setDuration(duration);
        weak_aa.setFillAfter(true);
        weak_aa.setAnimationListener(listener);

        return weak_aa;
    }

    public static TranslateAnimation lineMoveIn() {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0f);

        line_ta.setDuration(500);
        line_ta.setFillAfter(true);

        return line_ta;
    }

    public static TranslateAnimation lineMoveOut() {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f);

        line_ta.setDuration(500);
        line_ta.setFillAfter(true);

        return line_ta;
    }

    public static TranslateAnimation horizontalMoveIn(long duration) {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0f);

        line_ta.setDuration(duration);
        line_ta.setInterpolator(new OvershootInterpolator(0.5f));
        line_ta.setFillAfter(true);

        return line_ta;
    }

    public static TranslateAnimation horizontalMoveOut(long duration) {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0f);

        line_ta.setDuration(duration);
        line_ta.setFillAfter(true);
        line_ta.setInterpolator(new AccelerateInterpolator());

        return line_ta;
    }

    public static TranslateAnimation verticalMoveIn(long duration, boolean ifShake, Animation.AnimationListener listener) {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );

        line_ta.setDuration(duration);
        if (ifShake) {
            line_ta.setInterpolator(new OvershootInterpolator(1f));
        }
        line_ta.setFillAfter(true);

        line_ta.setAnimationListener(listener);

        return line_ta;
    }

    public static TranslateAnimation verticalMoveOut(long duration, boolean ifShake, Animation.AnimationListener listener) {
        TranslateAnimation line_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f
        );

        line_ta.setDuration(duration);
        if (ifShake) {
            line_ta.setInterpolator(new OvershootInterpolator(1f));
        }
        line_ta.setFillAfter(true);

        line_ta.setAnimationListener(listener);

        return line_ta;
    }

    public static TranslateAnimation shiftDown(long duration) {
        TranslateAnimation shift_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.5f, Animation.RELATIVE_TO_PARENT, 0.0f);

        shift_ta.setDuration(duration);
        shift_ta.setFillAfter(true);

        return shift_ta;
    }

    public static TranslateAnimation breathFloatVertical(long duration) {
        TranslateAnimation breath_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.1f, Animation.RELATIVE_TO_SELF, 0.1f);

        breath_ta.setDuration(duration);
        breath_ta.setRepeatCount(Animation.INFINITE);
        breath_ta.setRepeatMode(Animation.REVERSE);
        breath_ta.setFillAfter(true);

        return breath_ta;
    }

    public static TranslateAnimation vibrateHorizontal() {
        TranslateAnimation vibrate_ta = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -0.005f, Animation.RELATIVE_TO_SELF, 0.005f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);

        vibrate_ta.setRepeatMode(Animation.REVERSE);
        vibrate_ta.setRepeatCount(10);
        vibrate_ta.setDuration(15);

        return vibrate_ta;
    }

    public static ScaleAnimation overturnVerticalInfinite(final View view) {
        final ScaleAnimation shrink_sa = new ScaleAnimation(1, 1, 1, 0,
                Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink_sa.setDuration(250);
        shrink_sa.setFillAfter(true);

        final ScaleAnimation expand_sa = new ScaleAnimation(1, 1, 0, -1,
                Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand_sa.setDuration(250);
        expand_sa.setFillAfter(true);

        shrink_sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(expand_sa);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        expand_sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(shrink_sa);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        return shrink_sa;
    }

    public static ScaleAnimation overturnHorizontalShrink(long duration, int count, Animation.AnimationListener listener) {
        ScaleAnimation shrink_sa = new ScaleAnimation(1, 0, 1, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 1f);

        shrink_sa.setDuration(duration);
        shrink_sa.setRepeatCount(count);
        shrink_sa.setAnimationListener(listener);

        return shrink_sa;
    }

    public static ScaleAnimation overturnHorizontalExpand(long duration, int count, Animation.AnimationListener listener) {
        ScaleAnimation expand_sa = new ScaleAnimation(0, 1, 1, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_PARENT, 1f);

        expand_sa.setDuration(duration);
        expand_sa.setRepeatCount(count);
        expand_sa.setAnimationListener(listener);

        return expand_sa;
    }

    public static RotateAnimation rotateInfinite(long duration) {
        RotateAnimation rotate = new RotateAnimation(
                0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(duration);
        rotate.setRepeatCount(Animation.INFINITE);
        return rotate;
    }

}
