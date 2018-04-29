package com.oji.kreate.lighttransfer;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

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

    public static ScaleAnimation becomeLarge(long duration) {
        ScaleAnimation large_sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        large_sa.setDuration(duration);

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

}
