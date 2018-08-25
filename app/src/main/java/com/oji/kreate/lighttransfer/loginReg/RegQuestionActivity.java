package com.oji.kreate.lighttransfer.loginReg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.vsf.base.BaseHttpActivity;
import com.oji.kreate.vsf.publicAdapter.ViewBaseAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RegQuestionActivity extends BaseHttpActivity {

    private ViewPager question_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_question);

        viewInit();

        varInit();

        setupToolbar();

        setupQuestionVPager();
    }

    public void viewInit() {
        question_vp = findViewById(R.id.reg_question_vp);
    }

    @Override
    public void varInit() {
        collapseIME(R.id.regquestion_layout);
    }

    @Override
    protected void setupToolbar() {

    }

    private void setupQuestionVPager() {
        QuestionThirdFragment fragment = new QuestionThirdFragment();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragment);

        ViewBaseAdapter adapter = new ViewBaseAdapter(getSupportFragmentManager());
        adapter.setFragments(fragmentList);

        question_vp.setAdapter(adapter);
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

    @Override
    public void handleNetDownAction() {

    }

}
