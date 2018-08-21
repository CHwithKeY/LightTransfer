package com.oji.kreate.lighttransfer;

import android.content.Context;

import com.oji.kreate.vsf.base.Base_Frag;
import com.oji.kreate.vsf.http.HttpAction;
import com.oji.kreate.vsf.http.HttpHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/6/7.
 */

public class LoginRegAction extends BaseHttpAction {

    public LoginRegAction(Context context) {
        super(context);
    }

    public void loginReg(Base_Frag frag, String passKey, int question_num, String question_answer) {
        String[] key = {KEY_PASSKEY, KEY_QUESTION_NUM, question_answer};
        String[] value = {passKey, question_num + "", question_answer};

        HttpAction action = new HttpAction(context);
        action.setUrl(URL_LOGIN_REG);
        action.setHandler(new HttpHandler(context, frag));
        action.setMap(key, value);
        action.setDialog("", "");
        action.setTag(TAG_LOGIN_REG);
//        action.interaction();
    }

    public void handleResponse(String response) throws JSONException {
        JSONObject obj = new JSONObject(response);
        String result = obj.getString(RESULT_RESULT);

        if (result.equals("")) {

        }

    }
}
