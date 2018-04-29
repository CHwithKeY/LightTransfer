package com.oji.kreate.lighttransfer;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oji.kreate.vsf.base.Base_Act;
import com.oji.kreate.vsf.publicClass.Methods;

import org.json.JSONException;

public class LoginActivity extends Base_Act {

    private EditText psd_et0;
    private EditText psd_et1;
    private EditText psd_et2;
    private EditText psd_et3;

    private LinearLayout welcome_ll;

    private TextView forget_psd_tv;
    private TextView switch_usn_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        varInit();

        setupToolbar();

        setupPsdEdit();

        setupWelcomeLinear();

        setupMoreImgBtn();

        setupCollapseIME();
    }

    @Override
    public void varInit() {

        forget_psd_tv = findViewById(R.id.login_forget_psd_tv);
        switch_usn_tv = findViewById(R.id.login_switch_usn_tv);

    }

    @Override
    protected void setupToolbar() {    }


    private void setupPsdEdit() {
        psd_et0 = findViewById(R.id.login_psd_et0);

        psd_et1 = findViewById(R.id.login_psd_et1);
        psd_et2 = findViewById(R.id.login_psd_et2);
        psd_et3 = findViewById(R.id.login_psd_et3);

        Methods.expandIME(psd_et0);

        autoNextEdit(null, psd_et0, psd_et1);
        autoNextEdit(psd_et0, psd_et1, psd_et2);
        autoNextEdit(psd_et1, psd_et2, psd_et3);

        psd_et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s_str = s.toString();

                if (!s_str.equals("")) {

                    Methods.collapseIME(LoginActivity.this);

                    String psd0 = psd_et0.getText().toString();
                    String psd1 = psd_et1.getText().toString();
                    String psd2 = psd_et2.getText().toString();
                    String psd3 = psd_et3.getText().toString();

                    String psd_full = psd0 + psd1 + psd2 + psd3;

                    if (psd_full.equals("1234")) {
                        Intent login_int = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(login_int);
                    } else {
                        showSnack(0, "密码错误");
                    }
                } else {
                    psd_et2.findFocus();
                    psd_et2.setFocusable(true);
                    psd_et2.requestFocus();
                }
            }
        });
    }

    private void autoNextEdit(final EditText before_edit, final EditText now_edit, final EditText next_edit) {

        now_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String s_str = s.toString();

                if (s_str.equals("")) {
                    if (before_edit != null) {
                        before_edit.findFocus();
                        before_edit.setFocusable(true);
                        before_edit.requestFocus();
                    } else {
                        now_edit.findFocus();
                        now_edit.setFocusable(true);
                        now_edit.setFocusable(true);
                    }
                } else {
                    next_edit.findFocus();
                    next_edit.setFocusable(true);
                    next_edit.requestFocus();
                }
            }
        });
    }

//    private TranslateAnimation tsa0 = new TranslateAnimation(
//            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
//            Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//
//    private TranslateAnimation tsa1 = new TranslateAnimation(
//            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
//            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -2.0f);

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

//        tsa0.setDuration(1000);
//        tsa1.setDuration(300);
//
//        tsa0.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                welcome_ll.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        welcome_ll.startAnimation(tsa1);
//                    }
//                }, 1500);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        tsa1.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                welcome_ll.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//
//        welcome_ll.startAnimation(tsa0);
    }

    private boolean showMore = false;

    private void setupMoreImgBtn() {
        final ImageButton more_imgbtn = findViewById(R.id.login_more_imgbtn);
        more_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showMore) {
                    showMoreText();
                } else {
                    hideMoreText();
                }
            }
        });
    }

    private void showMoreText() {
        showMore = true;

        forget_psd_tv.setVisibility(View.VISIBLE);
        forget_psd_tv.startAnimation(AnimationUtil.becomeLarge());

        switch_usn_tv.setVisibility(View.VISIBLE);
        switch_usn_tv.startAnimation(AnimationUtil.becomeLarge());
    }

    private void hideMoreText() {
        showMore = false;

        forget_psd_tv.setVisibility(View.GONE);
        forget_psd_tv.startAnimation(AnimationUtil.becomeSmall());

        switch_usn_tv.setVisibility(View.GONE);
        switch_usn_tv.startAnimation(AnimationUtil.becomeSmall());
    }

    private void setupCollapseIME() {
        final RelativeLayout login_rl = findViewById(R.id.login_layout);
        login_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods.collapseIME(LoginActivity.this);
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
