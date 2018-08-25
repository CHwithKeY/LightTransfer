package com.oji.kreate.lighttransfer.upload;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.oji.kreate.lighttransfer.AnimationUtil;
import com.oji.kreate.lighttransfer.ClipUtil;
import com.oji.kreate.lighttransfer.FragmentArgs;
import com.oji.kreate.lighttransfer.MultiBoardFragment;
import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.lighttransfer.TagBtnDownloadRycAdapter;
import com.oji.kreate.lighttransfer.http.HttpTag;
import com.oji.kreate.vsf.base.BaseHttpActivity;
import com.oji.kreate.vsf.publicAdapter.ViewBaseAdapter;
import com.oji.kreate.vsf.publicClass.Methods;
import com.oji.kreate.vsf.publicClass.ScreenSize;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadTextActivity extends BaseHttpActivity implements HttpTag {
//    private DrawerLayout dwer;
//    private View nav_expand;

    private EditText prepare_et;
    private ViewPager multiBoard_vp;

    private RelativeLayout ut_layout;

    private View shadow_view;
    private RelativeLayout pad_rl;

    private EditText temp_et;

    private UploadAction uploadAction;

    private ArrayList<String> tagList;
    private ClipboardManager clipManager;

    //    private DisappearOtherView dov;
    private ArrayList<View> disappearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_text);

        viewInit();

        varInit();

        setupToolbar();

        setupReviewFab();

        setupTransferImg();

//        setupNavRelative();

        setupMultiBoardVPager();
    }

    public void viewInit() {
//        nav_expand = findViewById(R.id.ut_nav_expand);

        prepare_et = findViewById(R.id.ut_prepare_et);
//        prepare_et.setVerticalScrollBarEnabled(true);

        multiBoard_vp = findViewById(R.id.ut_multiboard_vp);

        shadow_view = findViewById(R.id.ut_shadow_view);
        pad_rl = findViewById(R.id.fgm_ut_multiboard_pad_rl);

        ut_layout = findViewById(R.id.upload_text_layout);
    }

    @Override
    public void varInit() {
        tagList = new ArrayList<>();
        disappearList = new ArrayList<>();

        uploadAction = new UploadAction(this);
        uploadAction.getTags();

        clipManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    public void handleNetDownAction() {

    }

    public ClipboardManager getClipManager() {
        return clipManager;
    }

    private String getPrepareEdit() {
        if (prepare_et != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(prepare_et.getText().toString());
            String text = m.replaceAll("");

            if (text.isEmpty()) {
                showSnack(0, "Temp%内容不可以为空");
            }

            return text;
        } else {
            return "";
        }
    }

    public void setupReviewFab() {
        final FloatingActionButton review_fab = findViewById(R.id.ut_review_fab);
        review_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String upload_text = getPrepareEdit();
                final String upload_text = prepare_et.getText().toString().trim();

                if (!upload_text.isEmpty()) {
                    onShadowAppear();
                    onMultiPadMoveIn();

                    final View review_dlg_view = View.inflate(UploadTextActivity.this, R.layout.dialog_ut_review, null);

                    EditText review_et = review_dlg_view.findViewById(R.id.dlg_ut_review_review_et);
                    review_et.setText(upload_text);

                    disappearList.add(review_dlg_view);
                    ut_layout.addView(review_dlg_view);

//                    final View review_dlg_view = View.inflate(UploadTextActivity.this, R.layout.dialog_ut_review, null);
//                    AlertDialog dialog = onCreateDlg("temp%预览", review_dlg_view);
//
//                    EditText review_et = review_dlg_view.findViewById(R.id.dlg_ut_review_review_et);
//                    review_et.setText(upload_text);
//
//                    dialog.show();
//
//                    int textLen = upload_text.length();
//
//                    if (textLen < 80) {
//                        onResizeDlg(dialog, false);
//                    } else if (textLen > 160) {
//                        onResizeDlg(dialog, true);
//                    }
                }
            }
        });
    }

    private void onResizeDlg(Dialog dialog, boolean toBigger) {
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        ScreenSize screenSize = new ScreenSize(this);

        if (toBigger) {
            lp.height = (int) (screenSize.getHeight() * 0.8);
        } else {
            lp.height = (int) (screenSize.getHeight() * 0.5);
        }

        dialog.getWindow().setAttributes(lp);
    }

//    private void onResizeDlgCustom(Dialog dialog) {
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//
//        ScreenSize screenSize = new ScreenSize(this);
//        lp.height = (int) (screenSize.getHeight() * 0.3);
//
//        dialog.getWindow().setAttributes(lp);
//    }

    public void setupTransferImg() {
        final ImageView transfer_img = findViewById(R.id.ut_transfer_img);
        transfer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String upload_text = getPrepareEdit();

                if (!upload_text.isEmpty()) {
                    onShadowAppear();
                    onMultiPadMoveIn();
                    inflateTransferView();
                }
            }
        });
    }

    private void inflateTransferView() {
        final View transfer_view = View.inflate(UploadTextActivity.this, R.layout.dialog_upload_tag, null);

        setupTransferView(transfer_view);
        disappearList.add(transfer_view);
        ut_layout.addView(transfer_view);
    }

    private void setupTransferView(View transfer_view) {
        //setup the switch function, private and public
//        final TextView dlg_tag_private_shadow_tv = transfer_view.findViewById(R.id.dlg_upload_tag_private_shadow_tv);
//        final TextView dlg_tag_private_tv = transfer_view.findViewById(R.id.dlg_upload_tag_private_tv);
//
//        final TextView dlg_tag_public_shadow_tv = transfer_view.findViewById(R.id.dlg_upload_tag_public_shadow_tv);
//        final TextView dlg_tag_public_tv = transfer_view.findViewById(R.id.dlg_upload_tag_public_tv);
//
//        dlg_tag_private_shadow_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dlg_tag_public_tv.startAnimation(AnimationUtil.weakFlash(200));
//                dlg_tag_public_tv.setClickable(false);
//                dlg_tag_public_tv.setVisibility(View.GONE);
//
//                dlg_tag_private_tv.startAnimation(AnimationUtil.strongFlash(300));
//                dlg_tag_private_tv.setClickable(true);
//                dlg_tag_private_tv.setVisibility(View.VISIBLE);
//            }
//        });
//
//        dlg_tag_public_shadow_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dlg_tag_private_tv.startAnimation(AnimationUtil.weakFlash(200));
//                dlg_tag_private_tv.setClickable(false);
//                dlg_tag_private_tv.setVisibility(View.GONE);
//
//                dlg_tag_public_tv.startAnimation(AnimationUtil.strongFlash(300));
//                dlg_tag_public_tv.setClickable(true);
//                dlg_tag_public_tv.setVisibility(View.VISIBLE);
//            }
//        });

        // setup tag recycler view
        RecyclerView dlg_tag_list_rv = transfer_view.findViewById(R.id.dlg_upload_tag_list_rv);

        GridLayoutManager manager = new GridLayoutManager(UploadTextActivity.this, 3);
        dlg_tag_list_rv.setLayoutManager(manager);
        dlg_tag_list_rv.setHasFixedSize(true);

//        final LinearLayout tag_chosen_ll = transfer_view.findViewById(R.id.page_upload_tag_chosen_ll);
//        final Button tag_chosen_btn = transfer_view.findViewById(R.id.page_upload_tag_chosen_btn);

        ArrayList<String> tagList = new ArrayList<>();

        tagList.add("工作");
        tagList.add("学习");
        tagList.add("其他");
        tagList.add("重要");
        tagList.add("每周会议");
        tagList.add("操作流程");
        tagList.add("账户密码");
        tagList.add("临时点");
        tagList.add("刘总处");

        final HashSet<String> tagSet = new HashSet<>();
//        TagButtonRycAdapter adapter = new TagButtonRycAdapter(tagList) {
//            @Override
//            void onClickTag(View view) {
//                String click_text = String.valueOf(((Button) view).getText());
//
//                int prev_size = tagSet.size();
//                tagSet.add(click_text);
//                if (prev_size == tagSet.size()) {
//                    showSnack(0, "已经选择该标签了");
//                    return;
//                }
//
//                final Button btn = createTagBtn(tag_chosen_btn, click_text);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tag_chosen_ll.removeView(btn);
//                        tagSet.remove(btn.getText().toString());
//                    }
//                });
//                tag_chosen_ll.addView(btn);
//            }
//        };


        TagBtnDownloadRycAdapter adapter = new TagBtnDownloadRycAdapter(tagList) {
            @Override
            public void onClickTag(View view) {

            }
        };


        dlg_tag_list_rv.setAdapter(adapter);

        //
        final ImageButton dlg_tag_confirm_imgbtn = transfer_view.findViewById(R.id.dlg_upload_tag_confirm_imgbtn);
        final EditText dlg_tag_et = transfer_view.findViewById(R.id.dlg_upload_tag_edit_tag_et);
        dlg_tag_et.setHint("标签");
        Methods.setMaxLength(dlg_tag_et, 4);

        dlg_tag_confirm_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String tag_text = dlg_tag_et.getText().toString();
//                final Button btn = createTagBtn(tag_chosen_btn, tag_text);
//
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tag_chosen_ll.removeView(btn);
//                        tagSet.remove(btn.getText().toString());
//                    }
//                });
//
//                int prev_size = tagSet.size();
//                tagSet.add(tag_text);
//                if (prev_size == tagSet.size()) {
//                    showSnack(0, "已经选择该标签了");
//                    return;
//                }
//                tag_chosen_ll.addView(btn);

                dlg_tag_et.setText("");
            }
        });

        dlg_tag_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    dlg_tag_confirm_imgbtn.startAnimation(AnimationUtil.weakFlash(300));
                    dlg_tag_confirm_imgbtn.setVisibility(View.GONE);
                } else {
                    if (dlg_tag_confirm_imgbtn.getVisibility() != View.VISIBLE) {
                        dlg_tag_confirm_imgbtn.startAnimation(AnimationUtil.strongFlash(300));
                        dlg_tag_confirm_imgbtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public Button createTagBtn(Button tag_chosen_btn, String click_text) {
        Button btn = new Button(UploadTextActivity.this);
        btn.setWidth(tag_chosen_btn.getWidth());
        btn.setHeight(tag_chosen_btn.getHeight());
        btn.setGravity(tag_chosen_btn.getGravity());
        btn.setTextColor(tag_chosen_btn.getTextColors());
        btn.setText(click_text);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        return btn;
    }

//    private void setupNavRelative() {
//        dwer = findViewById(R.id.ut_drawer);
//
//        dwer.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                if (slideOffset > 0.3f) {
//                    nav_expand.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                nav_expand.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
//    }

    public void setupMultiBoardVPager() {
        ViewBaseAdapter adapter = new ViewBaseAdapter(getSupportFragmentManager());

        List<Fragment> fragmentList = new ArrayList<>();

        ArrayList<String> clipList = ClipUtil.getClipboardText(clipManager, sharedAction);
        for (int i = 0; i < clipList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString(FragmentArgs.BUNDLE_KEY_TEXT, clipList.get(i));

            MultiBoardFragment fragment = new MultiBoardFragment();
            fragment.setArguments(bundle);

            fragmentList.add(fragment);

            if (i == 0) {
                prepare_et.setText(clipList.get(i));
                prepare_et.setSelection(prepare_et.getText().length());
            }

            adapter.setFragments(fragmentList);
        }

        multiBoard_vp.setAdapter(adapter);

        // create match pointer_view
        final int pointer_size = clipList.size();
        View sample_pointer_view = findViewById(R.id.ut_multiboard_pointer_view);
        final LinearLayout pointer_ll = findViewById(R.id.ut_multiboard_pointer_ll);

        for (int i = 0; i < pointer_size; i++) {
            View pointer_view = new View(UploadTextActivity.this);
            pointer_view.setLayoutParams(sample_pointer_view.getLayoutParams());
            pointer_view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            pointer_ll.addView(pointer_view);
        }

        pointer_ll.getChildAt(1).setBackgroundColor(getResources().getColor(R.color.colorBackground));

        multiBoard_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Log.i("Result", "position is : " + position);
                View view = pointer_ll.getChildAt(position + 1);
                view.setBackgroundColor(getResources().getColor(R.color.colorBackground));

                if (position > 0) {
                    View prev_view = pointer_ll.getChildAt(position);
                    prev_view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }

                if ((position + 2) <= pointer_size) {
                    View next_view = pointer_ll.getChildAt(position + 2);
                    next_view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    private ArrayList<String> getClipboardText() {
//        ArrayList<String> clipList = new ArrayList<>();
//
//        if (clipManager != null && clipManager.hasPrimaryClip()) {
//            ClipData clipData = clipManager.getPrimaryClip();
//
//            for (int i = 0; i < clipData.getItemCount(); i++) {
//                String clip_text = String.valueOf(clipData.getItemAt(i).getText());
//
//                if (clip_text != null && !clip_text.isEmpty()) {
//                    String last_num_text = sharedInfoAction.getMap(SharedSet.KEY_CLIP_NUM);
//                    if (last_num_text.isEmpty()) {
//                        last_num_text = "0";
//                    }
//
//                    String last_flag = SharedSet.KEY_CLIP_FLAG + last_num_text;
//                    String last_item = sharedInfoAction.getMap(last_flag);
//
//                    if (last_item.isEmpty()) {
//                        sharedInfoAction.setMap(SharedSet.KEY_CLIP_NUM, last_num_text);
//                        sharedInfoAction.setMap(last_flag, clip_text);
//                    } else {
//                        int last_num = Integer.parseInt(last_num_text);
//
//                        boolean isClipExist = false;
//                        for (int j = 0; j < last_num; j++) {
//                            String shared_test = sharedInfoAction.getMap(SharedSet.KEY_CLIP_FLAG + j);
//                            if (clip_text.equals(shared_test)) {
//                                isClipExist = true;
//                                break;
//                            }
//                        }
//
//                        if (!isClipExist) {
//                            last_num++;
//
//                            sharedInfoAction.setMap(SharedSet.KEY_CLIP_FLAG + last_num, clip_text);
//                            sharedInfoAction.setMap(SharedSet.KEY_CLIP_NUM, last_num + "");
//                        }
//                    }
//                } else {
//                    clip_text = "Temp%暂无更新内容";
//                    clipList.add(clip_text);
//                }
//            }
//
//            int last_num = Integer.parseInt(sharedInfoAction.getMap(SharedSet.KEY_CLIP_NUM));
//            if (last_num == 0) {
//                clipList.add(sharedInfoAction.getMap(SharedSet.KEY_CLIP_FLAG + "0"));
//            } else {
//                for (int i = last_num; i >= 0; i--) {
//                    String shared_clip = sharedInfoAction.getMap(SharedSet.KEY_CLIP_FLAG + i);
//                    clipList.add(shared_clip);
//                }
//            }
//        }
//
//        return clipList;
//    }

    public void setupMultiBoardPad(final TextView fgm_text_view) {
        onShadowAppear();
        onMultiPadMoveIn();
        createBoardEdit(fgm_text_view.getText().toString());
    }

    private void onMultiPadMoveIn() {
        pad_rl.setVisibility(View.VISIBLE);
        pad_rl.startAnimation(AnimationUtil.lineMoveIn());
    }

    private void onMultiPadMoveOut() {
        pad_rl.clearAnimation();
        pad_rl.startAnimation(AnimationUtil.lineMoveOut());
    }

    private void onShadowAppear() {
//        setupDov();

        shadow_view.setVisibility(View.VISIBLE);
        shadow_view.startAnimation(AnimationUtil.strongFlash(500));
        shadow_view.setClickable(true);
        shadow_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMultiPadMoveOut();
                onShadowDisappear();

                for (int i = 0; i < disappearList.size(); i++) {
                    View view = disappearList.get(i);
                    view.clearAnimation();
                    view.startAnimation(AnimationUtil.weakFlash(500));
                    view.setVisibility(View.GONE);
                    ut_layout.removeView(view);
                    disappearList.remove(i);
                }
//                dov.disappear();
            }
        });
    }

    private void onShadowDisappear() {
        shadow_view.clearAnimation();
        shadow_view.startAnimation(AnimationUtil.weakFlash(500));
        shadow_view.setVisibility(View.GONE);
        shadow_view.setClickable(false);
    }

//    private void setupDov() {
//        dov = new DisappearOtherView() {
//            @Override
//            public void disappear() {
//                onMultiPadMoveOut();
//                removeBoardEdit();
//            }
//        };
//    }

//    public interface DisappearOtherView {
//        void disappear();
//    }

    private void createBoardEdit(String board_text) {
        // Create the same view, including the width, height, padding and margin(location), especially the location.
        // temp_et is the global var.
        if (temp_et != null) {
            temp_et.setVisibility(View.VISIBLE);
            temp_et.startAnimation(AnimationUtil.strongFlash(500));
        } else {
            temp_et = new EditText(UploadTextActivity.this);

            ScrollView prepare_sv = findViewById(R.id.ut_prepare_sv);

            int h = prepare_sv.getHeight();
            int w = prepare_sv.getWidth();

            temp_et.setWidth(w);
            temp_et.setHeight(h);

            ScreenSize screenSize = new ScreenSize(UploadTextActivity.this);
            int dp = screenSize.getDPI() / 160;

            final LinearLayout drawer_ll = findViewById(R.id.ut_drawer_ll);
            temp_et.setX((drawer_ll.getWidth() - prepare_sv.getWidth()) / 2);
            temp_et.setY(drawer_ll.getTop());

            temp_et.setTextSize(prepare_et.getTextSize() / dp);

            int padding_divider = (int) getResources().getDimension(R.dimen.item_double_divider);
            temp_et.setPadding(padding_divider, padding_divider, padding_divider, padding_divider);

            temp_et.setBackground(getResources().getDrawable(android.R.color.white));
            temp_et.setGravity(Gravity.START | Gravity.TOP);
        }

        temp_et.setText(board_text);
        temp_et.setFocusable(true);
        temp_et.setKeyListener(null);

        ut_layout.addView(temp_et);
        disappearList.add(temp_et);
    }

//    private void removeBoardEdit() {
//        // you should clear the old animation, or it won't start the new animation.
//        temp_et.clearAnimation();
//
//        temp_et.startAnimation(AnimationUtil.weakFlash(500));
//        temp_et.setVisibility(View.GONE);
//
//        // besides, you still need to remove it from the root view/parent view), or it will still here
//        // and you can't click/focus the bottom view( the view under it).
//        ut_layout.removeView(temp_et);
//    }

//    private boolean isTouchInView(View view, MotionEvent ev) {
//        int[] location = new int[2];
//        view.getLocationOnScreen(location);
//        int x = location[0];
//        int y = location[1];
//        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
//            return false;
//        }
//        return true;
//    }

    //    private View.OnTouchListener shopCarSettleTouch = new View.OnTouchListener() {
//        int lastX, lastY;
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            nav_linear_width = nav_linear.getWidth();
//
//            int ea = event.getAction();
//            DisplayMetrics dm = getResources().getDisplayMetrics();
//            int screenWidth = dm.widthPixels;
////            int screenHeight = dm.heightPixels - 100;//需要减掉图片的高度
//            int screenHeight = dm.heightPixels;//需要减掉图片的高度
//
//            switch (ea) {
//                case MotionEvent.ACTION_DOWN:
//                    lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
//                    lastY = (int) event.getRawY();
//                case MotionEvent.ACTION_MOVE:
//                    //event.getRawX();获得移动的位置
//                    int dx = (int) event.getRawX() - lastX;
//                    int dy = (int) event.getRawY() - lastY;
//
//                    int l = v.getLeft() + dx;
//                    int b = v.getBottom() + dy;
//                    int r = v.getRight() + dx;
//                    int t = v.getTop() + dy;
//
////                    Log.i("Result", "nav linear width is : " + nav_linear_width);
//
//                    //下面判断移动是否超出屏幕
////                    if (l < (screenWidth - v.getWidth() + v.getWidth() / 10 * 5) && dy > 0) {
////                        l = screenWidth - v.getWidth();
////                        r = screenWidth;
////                    }
////
////                    if (dy <= 0 && r > (screenWidth + v.getWidth() / 10 * 3)) {
////                        r = screenWidth + v.getWidth() / 10 * 8;
////                        l = r - v.getWidth();
////                    }
//
//                    if (l < (screenWidth - v.getWidth())) {
//                        l = screenWidth - v.getWidth();
//                        r = screenWidth;
//                    }
//
//                    if (t < 0) {
//                        t = 0;
//                        b = t + v.getHeight();
//                    }
//
//                    if (r > screenWidth) {
//                        r = screenWidth;
//                        l = r - v.getWidth();
//                    }
//
//                    if (b > screenHeight - getStatusBarHeight()) {
//                        b = screenHeight;
//                        t = b - v.getHeight() - getStatusBarHeight();
//                    }
//
//                    v.layout(l, t, r, b);
////                    Log.i("Result", "onTouch: " + l + "==" + t + "==" + r + "==" + b);
//                    lastX = (int) event.getRawX();
//                    lastY = (int) event.getRawY();
//
//                    v.postInvalidate();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    break;
//            }
//            return true;
//        }
//    };

    /**
     * 获取状态栏高度——方法1
     */
    private int getStatusBarHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        return statusBarHeight;
    }

    @Override
    public void onMultiHandleResponse(String s, String s1) throws JSONException {
        switch (s) {
            case TAG_GET_TAGS:
                tagList = uploadAction.handleTagResponse(s1);
                break;

            case TAG_UPLOAD_TEXT:
                uploadAction.handleTextResponse(s1);
                break;
        }
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
