package com.oji.kreate.lighttransfer.loginReg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.oji.kreate.lighttransfer.AnimationUtil;
import com.oji.kreate.lighttransfer.MainActivity;
import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.lighttransfer.loginReg.LoginRegAction;
import com.oji.kreate.vsf.base.BaseHttpActivity;
import com.oji.kreate.vsf.base.BaseHttpFragment;
import com.oji.kreate.vsf.publicClass.Methods;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/6/7.
 */

public class QuestionThirdFragment extends BaseHttpFragment implements View.OnClickListener {

    private LoginRegAction loginRegAction;

    private EditText answer_et;

    private int question_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_question_third, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        varInit();

        viewInit(view);

        setupTitleText(view);

        setupBackgroundImage(view);

        setupQuestionRadio(view);

        initAnswerEdit();

        setupPrevFab(view);

        setupNextFab(view);
    }

    public void varInit() {
        loginRegAction = new LoginRegAction(getContext());

        Methods.collapseIME(getContext());
        ((BaseHttpActivity) getActivity()).collapseIME(R.id.fgm_rqt_layout);
    }

    public void viewInit(View view) {
        answer_et = view.findViewById(R.id.fgm_rqt_answer_et);
    }

    private void setupTitleText(View layout) {
        final TextView title_tv = layout.findViewById(R.id.item_title_tv);
        title_tv.setText("密保问题");
    }

    private void setupBackgroundImage(View layout) {
        final View bg_fst_view = layout.findViewById(R.id.fgm_rqt_bg_fst_view);
        bg_fst_view.startAnimation(AnimationUtil.rotateInfinite(2000));

        final View bg_snd_view = layout.findViewById(R.id.fgm_rqt_bg_snd_view);
        bg_snd_view.startAnimation(AnimationUtil.rotateInfinite(1500));
    }

    private void setupQuestionRadio(View layout) {
        final RadioButton fst_rb = layout.findViewById(R.id.fgm_rqt_question_fst_rb);
        final RadioButton snd_rb = layout.findViewById(R.id.fgm_rqt_question_snd_rb);
        final RadioButton thd_rb = layout.findViewById(R.id.fgm_rqt_question_thd_rb);

        fst_rb.setOnClickListener(this);
        snd_rb.setOnClickListener(this);
        thd_rb.setOnClickListener(this);

        fst_rb.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        setupAnswerEdit(v);
    }

    private void initAnswerEdit() {
        answer_et.setSingleLine();
        Methods.setMaxLength(answer_et, 10);
    }

    private void setupAnswerEdit(View radio_view) {
        answer_et.setText("");

        switch (radio_view.getId()) {
            case R.id.fgm_rqt_question_fst_rb:
                answer_et.setHint("请输入明星的名字");
                question_num = 1;
                break;

            case R.id.fgm_rqt_question_snd_rb:
                answer_et.setHint("请输入电影名字");
                question_num = 2;
                break;

            case R.id.fgm_rqt_question_thd_rb:
                answer_et.setHint("请输入能力的名字");
                question_num = 3;
                break;
        }
    }

    private String getAnswerEdit() {
        return answer_et.getText().toString();
    }

    private void setupPrevFab(View view) {
        final FloatingActionButton prev_fab = view.findViewById(R.id.item_prev_fab);
        prev_fab.startAnimation(AnimationUtil.breathFloatVertical(1000));
        prev_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().moveTaskToBack(true);
            }
        });
    }

    private void setupNextFab(View view) {
        final FloatingActionButton next_fab = view.findViewById(R.id.item_next_fab);
        next_fab.startAnimation(AnimationUtil.breathFloatVertical(900));
        next_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAnswerEdit().isEmpty()) {
                    answer_et.startAnimation(AnimationUtil.vibrateHorizontal());
                    return;
                }

//                loginRegAction.loginReg(QuestionThirdFragment.this, "", question_num, getAnswerEdit());

                Intent main_int = new Intent(getContext(), MainActivity.class);
                startActivity(main_int);
            }
        });
    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {
        switch (s) {
        }
    }

    @Override
    public void onNullResponse(String s) throws JSONException {

    }

}