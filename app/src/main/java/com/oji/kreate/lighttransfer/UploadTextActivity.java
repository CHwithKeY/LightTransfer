package com.oji.kreate.lighttransfer;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.oji.kreate.vsf.base.Base_Act;
import com.oji.kreate.vsf.publicClass.ScreenSize;

import org.json.JSONException;

public class UploadTextActivity extends Base_Act {
    private DrawerLayout dwer;

    private Button move_btn;
    private LinearLayout nav_linear;
    private NavigationView nav_view;
    private View nav_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload_text);

        varInit();

        setupToolbar();

        setupMoveBtn();

        setupNavRelative();

    }

    @Override
    public void varInit() {
        nav_bg = findViewById(R.id.nav_bg);
    }

    @Override
    protected void setupToolbar() {

    }

    private void setupMoveBtn() {
//        move_btn = findViewById(R.id.upload_move_btn);
    }

    private void setupNavRelative() {
        dwer = findViewById(R.id.upload_text_drawer);

        dwer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset > 0.3f) {
                    nav_bg.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                nav_bg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

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
