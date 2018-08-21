package com.oji.kreate.lighttransfer;

import android.content.Context;

import com.oji.kreate.lighttransfer.http.HttpKey;
import com.oji.kreate.lighttransfer.http.HttpResult;
import com.oji.kreate.lighttransfer.http.HttpTag;
import com.oji.kreate.lighttransfer.http.HttpURL;
import com.oji.kreate.vsf.base.BaseAction;

/**
 * Created by Administrator on 2018/5/26.
 */

public abstract class BaseHttpAction extends BaseAction implements HttpKey, HttpResult, HttpTag, HttpURL {
    public BaseHttpAction(Context context) {
        super(context);
    }
}
