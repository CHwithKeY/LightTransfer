package com.oji.kreate.lighttransfer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oji.kreate.vsf.base.Base_Act;
import com.oji.kreate.vsf.publicAdapter.ViewTitleAdapter;
import com.oji.kreate.vsf.publicClass.ScreenSize;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Base_Act {

    //    private TabLayout function_tab;
    private ViewPager function_vp;

    private FloatingActionButton text_fab;
    private FloatingActionButton image_fab;
    private FloatingActionButton file_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varInit();

        setupToolbar();

        setupViewPager();

        setupFabInitRebound();

        setupMoveTestBtn();
    }

    @Override
    public void varInit() {
        text_fab = findViewById(R.id.main_text_fab);
        image_fab = findViewById(R.id.main_image_fab);
    }

    @Override
    protected void setupToolbar() {

    }

    private void setupViewPager() {
//        function_tab = findViewById(R.id.main_function_tab);
        function_vp = findViewById(R.id.main_function_vp);
// Disable clip to padding
        function_vp.setClipToPadding(false);
// set padding manually, the more you set the padding the more you see of prev & next page
        function_vp.setPadding(40, 0, 40, 0);
// sets a margin b/w individual pages to ensure that there is a gap b/w them
        function_vp.setPageMargin(20);
        function_vp.setOffscreenPageLimit(1);

        UploadFragment upload_fm = new UploadFragment();
        DownloadFragment download_fm = new DownloadFragment();

        List<Fragment> fmList = new ArrayList<>();
        fmList.add(upload_fm);
        fmList.add(download_fm);

        String[] titles = {"上传", "下载"};

        ViewTitleAdapter titleAdapter = new ViewTitleAdapter(getSupportFragmentManager());
        titleAdapter.setFragments(fmList);
        titleAdapter.setTitles(titles);

//        function_tab.setSelectedTabIndicatorHeight(0);

        function_vp.setAdapter(titleAdapter);
//        function_tab.setupWithViewPager(function_vp);
    }

    private void setupReboundAnim(final View view) {
        TranslateAnimation tsa1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -0.1f);

        final TranslateAnimation tsa2 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.1f);

        tsa1.setRepeatCount(1);
//        tsa2.setRepeatCount(2);

        tsa1.setDuration(50);
        tsa2.setDuration(50);

        tsa1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(tsa2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        view.startAnimation(tsa1);
    }

    private void setupTranslateBottomAnim(final View view) {
        TranslateAnimation tsa0 = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );

        tsa0.setDuration(300);

        tsa0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setupReboundAnim(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(tsa0);
    }

    private void setupMoveTestBtn() {
        final Button move_btn = findViewById(R.id.main_test_btn);
        move_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupFabInitRebound();
            }
        });
    }

    private void setupFabInitRebound() {

        setupTranslateBottomAnim(text_fab);

        image_fab.postDelayed(new Runnable() {
            @Override
            public void run() {
                setupTranslateBottomAnim(image_fab);
            }
        }, 100);

    }


//    The FAB is always on the top layer
//    private AlphaAnimation alh0 = new AlphaAnimation(1.0f, 0.0f);
//    private AlphaAnimation alh1 = new AlphaAnimation(0.0f, 1.0f);
//
//    int i = 0;
//
//    private void setupAlpha() {
//        final FloatingActionButton text_fab = findViewById(R.id.main_text_fab);
//
//        alh0.setDuration(400);
//        alh1.setDuration(400);
//
//        if (i % 2 == 0) {
//            text_fab.startAnimation(alh0);
//        }
//    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {

    }

    @Override
    public void onNullResponse(String s) throws JSONException {

    }

    @Override
    public void onPermissionAccepted(int i) {

    }

    @Override
    public void onPermissionRefused(int i) {

    }


}
