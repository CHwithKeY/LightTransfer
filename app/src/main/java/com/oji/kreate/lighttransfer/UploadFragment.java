package com.oji.kreate.lighttransfer;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oji.kreate.vsf.base.Base_Frag;

import org.json.JSONException;

/**
 * Created by Kreate on 2018/3/9.
 */

public class UploadFragment extends Base_Frag {

    private FloatingActionButton upload_fab;
    private FloatingActionButton download_fab;
    private View bg_scale;
    private View bg_view;

    private TextView switch_tv;

    private ScaleAnimation sato0 = new ScaleAnimation(1, 0, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.5f);

    private ScaleAnimation sato1 = new ScaleAnimation(0, 1, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.5f);

    private ScaleAnimation san0 = new ScaleAnimation(0, 10f, 0, 10f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


    //
    private ScaleAnimation san3 = new ScaleAnimation(1, 1, 1, 0,
            Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.5f);

    private ScaleAnimation san4 = new ScaleAnimation(1, 1, 0, 1,
            Animation.RELATIVE_TO_PARENT, 0.1f, Animation.RELATIVE_TO_PARENT, 0.5f);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_upload, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        FloatingActionButton upload_fab = view.findViewById(R.id.main_upload_fab);
//
//        Animation animation = new RotateAnimation(0, 359);
//        animation.setDuration(500);
//        animation.setRepeatCount(4);//动画的重复次数
//        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
//        upload_fab.startAnimation(animation);//开始动画

        initView(view);

        final Button test_btn = view.findViewById(R.id.test_anim_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upload_fab.getVisibility() == View.VISIBLE) {
                    upload_fab.startAnimation(sato0);
                } else {
                    download_fab.startAnimation(sato0);
                }
            }
        });
    }

    private void showUploadFab() {
        upload_fab.setVisibility(View.VISIBLE);
        download_fab.setVisibility(View.GONE);
    }

    private void showDownloadFab() {
        upload_fab.setVisibility(View.GONE);
        download_fab.setVisibility(View.VISIBLE);
    }

    private void setupSwitchText() {

    }

    private void initView(View view) {
        upload_fab = view.findViewById(R.id.main_upload_fab);
        download_fab = view.findViewById(R.id.main_download_fab);
        bg_view = view.findViewById(R.id.main_upload_bg);

        showUploadFab();
        sato0.setDuration(100);
        sato1.setDuration(120);

        sato0.setRepeatCount(4);
        sato1.setRepeatCount(1);

        bg_scale = view.findViewById(R.id.main_scale_bg);
        san0.setDuration(900);
        san0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bg_scale.clearAnimation();

                if (upload_fab.getVisibility() == View.VISIBLE) {
                    bg_scale.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_assist_circle));
                    bg_view.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_land_circle));
                } else {
                    bg_scale.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_land_circle));
                    bg_view.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_assist_circle));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        sato0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bg_scale.startAnimation(san0);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (upload_fab.getVisibility() == View.VISIBLE) {
                    upload_fab.clearAnimation();
                    showDownloadFab();
                    download_fab.startAnimation(sato1);
                } else {
                    download_fab.clearAnimation();
                    showUploadFab();
                    upload_fab.startAnimation(sato1);
                }


//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    bg_scale.postOnAnimationDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                bg_scale.startAnimation(san0);
//                        }
//                    }, 500);


//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {

    }

    @Override
    public void onNullResponse(String s) throws JSONException {

    }
}
