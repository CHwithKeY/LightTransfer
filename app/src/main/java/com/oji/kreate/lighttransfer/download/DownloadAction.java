package com.oji.kreate.lighttransfer.download;

import android.content.Context;

import com.oji.kreate.lighttransfer.http.HttpKey;
import com.oji.kreate.lighttransfer.http.HttpResult;
import com.oji.kreate.lighttransfer.http.HttpTag;
import com.oji.kreate.lighttransfer.http.HttpURL;
import com.oji.kreate.vsf.base.BaseHttpAction;
import com.oji.kreate.vsf.http.HttpAction;
import com.oji.kreate.vsf.http.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/5/26.
 */

public class DownloadAction extends BaseHttpAction implements HttpKey, HttpResult, HttpTag, HttpURL {

    public DownloadAction(Context context) {
        super(context);
    }

    public void downloadText(String user_id) {
        String[] key = {};
        String[] value = {user_id};

        HttpAction action = new HttpAction(context);
        action.setUrl(URL_DOWNLOAD_TEXT);
        action.setMap(key, value);
        action.setHandler(new HttpHandler(context));
        action.setDialog("", "");
        action.setTag(TAG_DOWNLOAD_TEXT);
//        action.interaction();
    }

    public void handleTextResponse(String result) throws JSONException {
        JSONObject object = new JSONObject(result);


    }
}
