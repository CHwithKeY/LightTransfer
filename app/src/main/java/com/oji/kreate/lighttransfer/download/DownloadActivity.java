package com.oji.kreate.lighttransfer.download;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.oji.kreate.lighttransfer.AnimationUtil;
import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.lighttransfer.TagBtnDownloadRycAdapter;
import com.oji.kreate.vsf.base.BaseHttpActivity;

import org.json.JSONException;

import java.util.ArrayList;

public class DownloadActivity extends BaseHttpActivity {

    private RelativeLayout dt_layout;
    private RecyclerView tag_list_rv;
    private RecyclerView download_item_rv;

    private ImageButton tag_list_imgbtn;
    private CardView tag_list_cv;

    private View shadow_view;

    private ArrayList<View> disappearList;
//    private CardView pad_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        setupTagListImgBtn();

        setupTagListRecycler();

        setupDownloadItemRecycler();

    }

    @Override
    public void varInit() {
        disappearList = new ArrayList<>();
    }

    @Override
    public void viewInit() {
        dt_layout = findViewById(R.id.download_text_layout);
        tag_list_rv = findViewById(R.id.dt_tag_list_rv);
        download_item_rv = findViewById(R.id.dt_download_item_rv);

//        pad_cv = findViewById(R.id.dt_multipad_cv);

        tag_list_imgbtn = findViewById(R.id.dt_tag_list_imgbtn);
        tag_list_cv = findViewById(R.id.dt_tag_list_cv);

        shadow_view = findViewById(R.id.dt_shadow_view);
    }

    @Override
    protected void setupToolbar() {

    }

    private void setupTagListImgBtn() {
        tag_list_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShadowAppear();
                onTagListAppear();
//                onMultiPadMoveIn();
            }
        });
    }

    private void setupTagListRecycler() {
        ArrayList<String> tagList = new ArrayList<>();

        tagList.add("重要");
        tagList.add("会议记录");
        tagList.add("其他");
        tagList.add("工作组");
        tagList.add("云智问题");
        tagList.add("学习");

        TagBtnDownloadRycAdapter adapter = new TagBtnDownloadRycAdapter(tagList) {
            @Override
            public void onClickTag(View view) {
                Button button = (Button) view;
            }
        };

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        tag_list_rv.setLayoutManager(manager);
        tag_list_rv.setAdapter(adapter);
    }

    private void onTagListAppear() {
        try {
            dt_layout.addView(tag_list_cv);
        } catch (Exception e) {

        }
        tag_list_cv.startAnimation(AnimationUtil.verticalMoveIn(500, false, null));
        tag_list_cv.setVisibility(View.VISIBLE);
    }

    private void onTagListDisappear() {
        tag_list_cv.startAnimation(AnimationUtil.verticalMoveOut(500, false, null));
        tag_list_cv.setVisibility(View.GONE);
        dt_layout.removeView(tag_list_cv);
    }

    private void setupDownloadItemRecycler() {
        ArrayList<DownloadItem> itemList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            DownloadItem item = new DownloadItem();
            item.setText("打飞机啊大师克劳福德沙卡洛夫啥的克里夫迪斯科飞机ask两地分居安德森开了房间阿三");
            item.setTime("2018-06-30 00:52:49");
            itemList.add(item);
        }

        DownloadItemRycAdapter adapter = new DownloadItemRycAdapter(itemList);

        download_item_rv.setLayoutManager(new LinearLayoutManager(this));
        download_item_rv.setAdapter(adapter);
    }

    private void onShadowAppear() {
        shadow_view.setVisibility(View.VISIBLE);
        shadow_view.startAnimation(AnimationUtil.strongFlash(500));
        shadow_view.setClickable(true);
        shadow_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onMultiPadMoveOut();
                onShadowDisappear();
                onTagListDisappear();

                for (int i = 0; i < disappearList.size(); i++) {
                    View view = disappearList.get(i);
                    view.clearAnimation();
                    view.startAnimation(AnimationUtil.weakFlash(500));
                    view.setVisibility(View.GONE);
                    dt_layout.removeView(view);
                    disappearList.remove(i);
                }
            }
        });
    }

    private void onShadowDisappear() {
        shadow_view.clearAnimation();
        shadow_view.startAnimation(AnimationUtil.weakFlash(500));
        shadow_view.setVisibility(View.GONE);
        shadow_view.setClickable(false);
    }

//    private void onMultiPadMoveIn() {
//        pad_cv.setVisibility(View.VISIBLE);
//        pad_cv.startAnimation(AnimationUtil.verticalMoveIn(500, false, null));
//    }
//
//    private void onMultiPadMoveOut() {
//        pad_cv.clearAnimation();
//        pad_cv.startAnimation(AnimationUtil.verticalMoveOut(500, false, null));
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

    @Override
    public void handleNetDownAction() {

    }
}
