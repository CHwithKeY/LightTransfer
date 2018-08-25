package com.oji.kreate.lighttransfer.loginReg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.vsf.base.BaseHttpFragment;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/6/7.
 */

public class QuestionFirstFragment extends BaseHttpFragment {

    private RecyclerView animal_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_question_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewInit(view);
    }

    public void viewInit(View view) {
        animal_rv = view.findViewById(R.id.fgm_rqf_rv);
    }

    private void setupAnimalRecycler() {


//        animal_rv.setAdapter();
    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {

    }

    @Override
    public void onNullResponse(String s) throws JSONException {

    }
}
