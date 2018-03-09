package com.oji.kreate.lighttransfer;

import android.support.v4.app.FragmentManager;

import com.oji.kreate.vsf.publicAdapter.ViewTitleAdapter;

/**
 * Created by Kreate on 2018/3/9.
 */

public class MultiPageAdapter extends ViewTitleAdapter {
    public MultiPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.99f;
    }
}
