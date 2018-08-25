package com.oji.kreate.lighttransfer;

import android.os.Bundle;
import android.view.View;

import com.oji.kreate.vsf.base.BaseHttpActivity;

import org.json.JSONException;

public class UserActivity extends BaseHttpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        varInit();

        setupToolbar();

        setupBackgroundView();
    }

    @Override
    public void varInit() {

    }

    @Override
    public void viewInit() {

    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    public void handleNetDownAction() {

    }

    public void setupBackgroundView() {
        View fst_bg_view = findViewById(R.id.user_bg_fst_view);
        fst_bg_view.startAnimation(AnimationUtil.breathFlash(1500));

        View snd_bg_view = findViewById(R.id.user_bg_snd_view);
        snd_bg_view.startAnimation(AnimationUtil.breathFlash(1250));

        View thd_bg_view = findViewById(R.id.user_bg_thd_view);
        thd_bg_view.startAnimation(AnimationUtil.breathFlash(1000));
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
