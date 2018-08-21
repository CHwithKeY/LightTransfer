package com.oji.kreate.lighttransfer.sharedInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.oji.kreate.vsf.publicClass.Methods;

import java.util.ArrayList;

/**
 * Created by spilkaka on 2018/4/19.
 */

public class SharedInfoAction {
    private SharedPreferences sp;

    public SharedInfoAction(Context context) {
        this.sp = context.getSharedPreferences("sp_file", 0);
    }

    public void setShared(SharedPreferences sp) {
        this.sp = sp;
    }

    public void clearLastIdInfo() {
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putInt("last_id", 0);
        editor.apply();
    }

    public void clearMap(String key) {
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putString(key, "");
        editor.apply();
    }

    public void setMap(String key, String value) {
        SharedPreferences.Editor editor = this.sp.edit();
        editor.putString(key, String.valueOf(value));
        editor.apply();
    }

    public String getMap(String key) {
        return sp.getString(key, "");
    }

}
