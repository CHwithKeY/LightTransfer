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
import android.widget.EditText;
import android.widget.TextView;

import com.oji.kreate.vsf.base.Base_Act;
import com.oji.kreate.vsf.publicClass.Methods;

import org.json.JSONException;

public class LoginActivity extends Base_Act {

    private EditText psd_et0;
    private EditText psd_et1;
    private EditText psd_et2;
    private EditText psd_et3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        varInit();

        setupToolbar();

        setupPsdEdit();


    }

    @Override
    public void varInit() {

    }

    @Override
    protected void setupToolbar() {

    }

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
