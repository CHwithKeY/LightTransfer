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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varInit();

        setupToolbar();

        setupViewPager();

        setupAlpha();
    }

    @Override
    public void varInit() {

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

    private View text_fab_bg;
    private ScaleAnimation san0 = new ScaleAnimation(0, 50f, 0, 50f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


    private void setupAlpha() {
        text_fab_bg = findViewById(R.id.main_fab_bg);
        final Button test_btn = findViewById(R.id.main_test_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_fab_bg.startAnimation(san0);
            }
        });

        san0.setDuration(400);

    }

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
