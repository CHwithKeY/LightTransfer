package com.oji.kreate.lighttransfer;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oji.kreate.lighttransfer.http.HttpKey;
import com.oji.kreate.lighttransfer.http.HttpResult;
import com.oji.kreate.lighttransfer.http.HttpTag;
import com.oji.kreate.lighttransfer.http.HttpURL;
import com.oji.kreate.lighttransfer.sharedInfo.SharedInfoAction;
import com.oji.kreate.vsf.base.Base_Act;
import com.oji.kreate.vsf.publicClass.Methods;

/**
 * Created by Administrator on 2018/6/4.
 */

public abstract class BaseActivity extends Base_Act implements HttpURL, HttpKey, HttpResult, HttpTag {

    protected SharedInfoAction sharedInfoAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }

        sharedInfoAction = new SharedInfoAction(this);
    }

    protected void collapseIME(int viewResId) {
        View view = findViewById(viewResId);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods.collapseIME(BaseActivity.this);
            }
        });
    }

    protected AlertDialog onCreateDlg(String title, View child_view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dlg_empty_view = View.inflate(this, R.layout.page_empty_dialog, null);

        if (title != null) {
            final TextView dlg_title = dlg_empty_view.findViewById(R.id.item_dt_title_tv);
            dlg_title.setText(title);
        }

        if (child_view != null) {
//            View child_view = View.inflate(this, layoutResId, null);
            final LinearLayout dlg_view_ll = dlg_empty_view.findViewById(R.id.page_ed_ll);
            dlg_view_ll.addView(child_view);
        }

        builder.setView(dlg_empty_view);

        final AlertDialog dialog = builder.create();

        final ImageButton close_img = dlg_empty_view.findViewById(R.id.item_dt_close_img);
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public void setMaxLength(EditText et, int length) {
        try {
            et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
        } catch (Exception e) {
            Log.e(getLocalClassName(), "传入的 EditText 是空的");
        }
    }

}
