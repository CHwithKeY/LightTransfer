package com.oji.kreate.lighttransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oji.kreate.vsf.base.Base_Act;

import org.json.JSONException;

public class MainActivity extends Base_Act {

    //    private TabLayout function_tab;
    //    private ViewPager function_vp;
    //    private DrawerLayout login_dw;

    private ImageButton text_imgbtn;
    private ImageButton image_imgbtn;
    private ImageButton file_imgbtn;

    private FloatingActionButton upload_fab;
    private FloatingActionButton download_fab;
    private View upload_bg_view;
    private View upload_bg_scale;

    private boolean uploadMode;

    private ScaleAnimation shrink_sa;
    private ScaleAnimation becomeLarge_sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        viewInit();

        varInit();

        setupToolbar();

//        setupViewPager();

        setupTestBtn();

        setupUploadImgBtnEnter();

        setupPlanetPathRelative();

//        setupMoveTestBtn();

        setupUploadTextImgBtn();

        setupUserImgbtn();

    }

    private void viewInit() {
        upload_fab = findViewById(R.id.main_upload_fab);
        download_fab = findViewById(R.id.main_download_fab);
        upload_bg_scale = findViewById(R.id.main_scale_bg);
        upload_bg_view = findViewById(R.id.main_upload_bg);

        text_imgbtn = findViewById(R.id.main_text_imgbtn);
        image_imgbtn = findViewById(R.id.main_image_imgbtn);
        file_imgbtn = findViewById(R.id.main_file_imgbtn);
    }

    @Override
    public void varInit() {
        uploadMode = true;
    }

    @Override
    protected void setupToolbar() {

    }

//    private void setupViewPager() {
////        function_tab = findViewById(R.id.main_function_tab);
//        function_vp = findViewById(R.id.main_function_vp);
//// Disable clip to padding
//        function_vp.setClipToPadding(false);
//// set padding manually, the more you set the padding the more you see of prev & next page
//        function_vp.setPadding(40, 0, 40, 0);
//// sets a margin b/w individual pages to ensure that there is a gap b/w them
//        function_vp.setPageMargin(20);
//        function_vp.setOffscreenPageLimit(1);
//
//        UploadFragment upload_fm = new UploadFragment();
//        DownloadFragment download_fm = new DownloadFragment();
//
//        List<Fragment> fmList = new ArrayList<>();
//        fmList.add(upload_fm);
//        fmList.add(download_fm);
//
//        String[] titles = {"上传", "下载"};
//
//        ViewTitleAdapter titleAdapter = new ViewTitleAdapter(getSupportFragmentManager());
//        titleAdapter.setFragments(fmList);
//        titleAdapter.setTitles(titles);
//
////        function_tab.setSelectedTabIndicatorHeight(0);
//
//        function_vp.setAdapter(titleAdapter);
////        function_tab.setupWithViewPager(function_vp);
//    }

    private void setupTestBtn() {
        becomeLarge_sa = AnimationUtil.becomeLargeTenTimes(900, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                upload_bg_scale.clearAnimation();

//                if (upload_fab.getVisibility() == View.VISIBLE) {
                if (uploadMode) {
                    upload_bg_scale.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_circle_lightgray_none));
                    upload_bg_view.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_circle_main_none));
                } else {
                    upload_bg_scale.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_circle_main_none));
                    upload_bg_view.setBackgroundDrawable(getResources().getDrawable(R.drawable.dw_circle_lightgray_none));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation.AnimationListener switch_listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text_imgbtn.clearAnimation();
                text_imgbtn.startAnimation(AnimationUtil.becomeSmall(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_imgbtn.clearAnimation();

                        if (uploadMode) {
                            text_imgbtn.setBackground(getResources().getDrawable(R.drawable.dw_circle_main_none));
                        } else {
                            text_imgbtn.setBackground(getResources().getDrawable(R.drawable.dw_circle_assist_none));
                        }
                        text_imgbtn.startAnimation(AnimationUtil.becomeLarge(150, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                text_imgbtn.clearAnimation();
                                text_imgbtn.startAnimation(AnimationUtil.breathFloatVertical(900));
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        }));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                }));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        shrink_sa = AnimationUtil.overturnHorizontalShrink(100, 4, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                upload_bg_scale.startAnimation(becomeLarge_sa);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (uploadMode) {
                    upload_fab.clearAnimation();
                    showDownloadFab();
                    download_fab.startAnimation(AnimationUtil.overturnHorizontalExpand(120, 1, switch_listener));
                } else {
                    download_fab.clearAnimation();
                    showUploadFab();
                    upload_fab.startAnimation(AnimationUtil.overturnHorizontalExpand(120, 1, switch_listener));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        final Button test_btn = findViewById(R.id.test_anim_btn);
        final ImageButton switch_imgbtn = findViewById(R.id.main_switch_imgbtn);
        switch_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadMode) {
                    upload_fab.startAnimation(shrink_sa);
                } else {
                    download_fab.startAnimation(shrink_sa);
                }
            }
        });

        showUploadFab();
    }

    private void showUploadFab() {
        uploadMode = true;
        upload_fab.setVisibility(View.VISIBLE);
        download_fab.setVisibility(View.GONE);
    }

    private void showDownloadFab() {
        uploadMode = false;
        upload_fab.setVisibility(View.GONE);
        download_fab.setVisibility(View.VISIBLE);
    }

//    private void setupMoveTestBtn() {
//        final Button move_btn = findViewById(R.id.main_test_btn);
//        move_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setupUploadImgBtnEnter();
//            }
//        });
//    }

    private void setupPlanetPathRelative() {
        final RelativeLayout path_a_rl = findViewById(R.id.main_planet_a_path_rl);
        path_a_rl.startAnimation(AnimationUtil.rotateInfinite(5000));
    }

    private void setupUploadImgBtnEnter() {
        text_imgbtn.postDelayed(new Runnable() {
            @Override
            public void run() {
                text_imgbtn.setVisibility(View.VISIBLE);
                image_imgbtn.setVisibility(View.VISIBLE);
                file_imgbtn.setVisibility(View.VISIBLE);

                text_imgbtn.startAnimation(AnimationUtil.verticalMoveIn(400, true, null));
                image_imgbtn.startAnimation(AnimationUtil.verticalMoveIn(500, true, null));
                file_imgbtn.startAnimation(AnimationUtil.verticalMoveIn(600, true, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        setupUploadDescriptionText();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                }));
            }
        }, 300);
    }

    private void setupUploadDescriptionText() {
        final TextView text_tv = findViewById(R.id.main_text_tv);
        text_tv.setVisibility(View.VISIBLE);
        text_tv.startAnimation(AnimationUtil.shiftDown(500));

        final TextView image_tv = findViewById(R.id.main_image_tv);
        image_tv.setVisibility(View.VISIBLE);
        image_tv.startAnimation(AnimationUtil.shiftDown(600));

        final TextView file_tv = findViewById(R.id.main_file_tv);
        file_tv.setVisibility(View.VISIBLE);
        file_tv.startAnimation(AnimationUtil.shiftDown(700));

        setupUploadImgBtnFloat();
    }

    private void setupUploadImgBtnFloat() {
        text_imgbtn.startAnimation(AnimationUtil.breathFloatVertical(900));
        image_imgbtn.startAnimation(AnimationUtil.breathFloatVertical(1300));
        file_imgbtn.startAnimation(AnimationUtil.breathFloatVertical(1100));
    }

    private void setupUploadTextImgBtn() {
        text_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent text_int = new Intent();
                if (upload_fab.getVisibility() == View.VISIBLE) {
                    text_int.setClass(MainActivity.this, UploadTextActivity.class);
                } else {
                    text_int.setClass(MainActivity.this, DownloadActivity.class);
                }
                startActivity(text_int);
            }
        });
    }

    private void setupUserImgbtn() {
//        final ImageButton user_imgbtn = findViewById(R.id.main_user_imgbtn);
//        user_imgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent user_int = new Intent(MainActivity.this, UserActivity.class);
//                startActivity(user_int);
//            }
//        });
    }


//    The FAB is always on the top layer
//    private AlphaAnimation alh0 = new AlphaAnimation(1.0f, 0.0f);
//    private AlphaAnimation alh1 = new AlphaAnimation(0.0f, 1.0f);
//
//    int i = 0;
//
//    private void setupAlpha() {
//        final FloatingActionButton text_imgbtn = findViewById(R.id.main_text_imgbtn);
//
//        alh0.setDuration(400);
//        alh1.setDuration(400);
//
//        if (i % 2 == 0) {
//            text_imgbtn.startAnimation(alh0);
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
