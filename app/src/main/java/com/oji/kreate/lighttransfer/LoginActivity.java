package com.oji.kreate.lighttransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oji.kreate.vsf.base.BaseHttpActivity;
import com.oji.kreate.vsf.publicClass.Methods;

import org.json.JSONException;

public class LoginActivity extends BaseHttpActivity {

    private LinearLayout welcome_ll;

    private EditText input_et;

    private TextView forget_psd_tv;
    private TextView switch_usn_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        setupWelcomeLinear();

        setupInputEdit();

        setupShowMoreText();

        setupHelpFab();

        setupNextFab();

        setupBackgroundImage();
    }

    @Override
    public void varInit() {
        collapseIME(R.id.login_layout);

//        forget_psd_tv = findViewById(R.id.login_forget_psd_tv);
//        switch_usn_tv = findViewById(R.id.login_switch_usn_tv);
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

    private void setupWelcomeLinear() {
        welcome_ll = findViewById(R.id.login_welcome_ll);

        Animation move_anim = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.anim_view_move);
        move_anim.setInterpolator(new AccelerateDecelerateInterpolator());
        welcome_ll.startAnimation(move_anim);
        move_anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                welcome_ll.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private boolean showMore = false;

    public void setupBackgroundImage() {
        ImageView bg_fst_img = findViewById(R.id.login_bg_fst_img);
        bg_fst_img.startAnimation(AnimationUtil.breathFlash(1000));

        ImageView bg_snd_img = findViewById(R.id.login_bg_snd_img);
        bg_snd_img.startAnimation(AnimationUtil.breathFlash(1250));

        ImageView bg_thd_img = findViewById(R.id.login_bg_thd_img);
        bg_thd_img.startAnimation(AnimationUtil.breathFlash(1500));
    }

    private void setupInputEdit() {
        input_et = findViewById(R.id.item_input_et);
        input_et.setHint("通行码长度在6~16位");
        input_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        input_et.setSingleLine();
        Methods.setMaxLength(input_et, 16);
    }

    public String getInputEdit() {
        return input_et.getText().toString();
    }

    private void setupHelpFab() {
        final FloatingActionButton help_fab = findViewById(R.id.login_help_fab);
        help_fab.startAnimation(AnimationUtil.breathFloatVertical(900));
        help_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showMore) {
                    hideMoreText();
                } else {
                    showMoreText();
                }
            }
        });
    }

    private void setupShowMoreText() {
        forget_psd_tv = findViewById(R.id.login_forget_psd_tv);
        switch_usn_tv = findViewById(R.id.login_switch_usn_tv);
    }

    private void showMoreText() {
        showMore = true;

        forget_psd_tv.setVisibility(View.VISIBLE);
        forget_psd_tv.startAnimation(AnimationUtil.horizontalMoveIn(200));

        switch_usn_tv.setVisibility(View.VISIBLE);
        switch_usn_tv.startAnimation(AnimationUtil.horizontalMoveIn(300));
    }

    private void hideMoreText() {
        showMore = false;

        forget_psd_tv.setVisibility(View.GONE);
        forget_psd_tv.startAnimation(AnimationUtil.horizontalMoveOut(300));

        switch_usn_tv.setVisibility(View.GONE);
        switch_usn_tv.startAnimation(AnimationUtil.horizontalMoveOut(200));
    }

    private void setupNextFab() {
        final FloatingActionButton next_fab = findViewById(R.id.item_next_fab);
        next_fab.startAnimation(AnimationUtil.breathFloatVertical(1000));
        next_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getInputEdit().isEmpty() || getInputEdit().length() < 6) {
                    input_et.startAnimation(AnimationUtil.vibrateHorizontal());
                    return;
                }

                Intent main_int = new Intent(LoginActivity.this, RegQuestionActivity.class);
                main_int.putExtra(IntentSet.KEY_PASS_KEY, getInputEdit());
                main_int.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main_int);
            }
        });
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
