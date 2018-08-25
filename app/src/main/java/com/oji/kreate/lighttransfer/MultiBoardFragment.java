package com.oji.kreate.lighttransfer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oji.kreate.vsf.base.BaseHttpFragment;

import org.json.JSONException;

/**
 * Created by Administrator on 2018/6/1.
 */

public class MultiBoardFragment extends BaseHttpFragment {

    private ClipboardManager clipManager;
    private TextView multiBoard_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ut_multiboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        varInit();

        setupMultiBoardText(view);

        setupMultiBoardImgBtn(view);

//        setupClipBoard();
//
//        setupDraftBoard();
    }

    private void varInit() {
        clipManager = getParentActivity().getClipManager();
    }

    public void setupMultiBoardText(View layout) {
        multiBoard_tv = layout.findViewById(R.id.fgm_ut_multiboard_tv);
        multiBoard_tv.setText(getArguments().getString(FragmentArgs.BUNDLE_KEY_TEXT, ""));

        multiBoard_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getMultiBoardText().isEmpty()) {
                    return;
                }
                getParentActivity().setupMultiBoardPad((TextView) v);
            }
        });
    }

    public String getMultiBoardText() {
        return multiBoard_tv.getText().toString();
    }

    public void setupMultiBoardImgBtn(View layout) {
        final ImageButton multiBoard_imgbtn = layout.findViewById(R.id.fgm_ut_multiboard_imgbtn);
        multiBoard_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("newClipData", getMultiBoardText());
                clipManager.setPrimaryClip(clipData);
                getParentActivity().showSnack(0, "内容复制成功");
            }
        });
    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {

    }

    @Override
    public void onNullResponse(String s) throws JSONException {

    }

    public UploadTextActivity getParentActivity() {
        return ((UploadTextActivity) getActivity());
    }

}
