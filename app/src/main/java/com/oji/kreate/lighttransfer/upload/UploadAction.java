package com.oji.kreate.lighttransfer.upload;


import android.content.Context;

import com.oji.kreate.lighttransfer.http.HttpKey;
import com.oji.kreate.lighttransfer.http.HttpResult;
import com.oji.kreate.lighttransfer.http.HttpTag;
import com.oji.kreate.lighttransfer.http.HttpURL;
import com.oji.kreate.vsf.base.BaseHttpAction;
import com.oji.kreate.vsf.http.HttpAction;
import com.oji.kreate.vsf.http.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/26.
 */

public class UploadAction extends BaseHttpAction implements HttpKey, HttpResult, HttpTag, HttpURL {

    public UploadAction(Context context) {
        super(context);
    }

    public void getTags() {
        String[] key = {KEY_USER_ID};
        String[] value = {""};

        HttpAction action = new HttpAction(context);
        action.setUrl(URL_GET_TAGS);
        action.setMap(key, value);
        action.setHandler(new HttpHandler(context));
        action.setTag(TAG_GET_TAGS);
//        action.interaction();
    }

    public void uploadText(String tags, String text) {
        String[] key = {KEY_USER_ID, KEY_TAG, KEY_TEXT};
        String[] value = {"", tags, text};

        HttpAction action = new HttpAction(context);
        action.setUrl(URL_UPLOAD_TEXT);
        action.setMap(key, value);
        action.setHandler(new HttpHandler(context));
        action.setTag(TAG_UPLOAD_TEXT);
        action.setDialog("temp%上传", "temp%上传中，请稍后……");
//        action.interaction();
    }

    public ArrayList<String> handleTagResponse(String response) throws JSONException {
        ArrayList<String> tagList = new ArrayList<>();

        JSONObject obj = new JSONObject(response);
        JSONArray array = obj.getJSONArray(RESULT_RESULT);

        if (array.length() != 0) {
            for (int i = 0; i < array.length(); i++) {
                tagList.add((String) array.get(i));
            }
        }

        return tagList;
    }

    public void handleTextResponse(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        showSnack(object.getString(RESULT_RESULT));
    }

}



