package com.oji.kreate.lighttransfer;

import android.content.ClipData;
import android.content.ClipboardManager;

import com.oji.kreate.lighttransfer.sharedInfo.SharedSet;
import com.oji.kreate.vsf.sharedInfo.SharedAction;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/26.
 */

public class ClipUtil {

    public static ArrayList<String> getClipboardText(ClipboardManager clipManager, SharedAction sharedAction) {
        ArrayList<String> clipList = new ArrayList<>();

        if (clipManager != null && clipManager.hasPrimaryClip()) {
            ClipData clipData = clipManager.getPrimaryClip();

            for (int i = 0; i < clipData.getItemCount(); i++) {
                String clip_text = String.valueOf(clipData.getItemAt(i).getText());

                if (clip_text != null && !clip_text.isEmpty()) {
                    // init last num, judge if the first time use this app
                    String last_num_text = sharedAction.getInfo(SharedSet.KEY_CLIP_NUM);
                    if (last_num_text.isEmpty()) {
                        last_num_text = "0";
                    }

                    // get the last item
                    String last_flag = SharedSet.KEY_CLIP_FLAG + last_num_text;
                    String last_item = sharedAction.getInfo(last_flag);

                    if (last_item.isEmpty()) {
                        // fst time use
                        sharedAction.setInfo(SharedSet.KEY_CLIP_NUM, last_num_text);
                        sharedAction.setInfo(last_flag, clip_text);
                    } else {
                        int last_num = Integer.parseInt(last_num_text);

                        boolean isClipExist = false;
                        for (int j = 0; j <= last_num; j++) {
                            String shared_test = sharedAction.getInfo(SharedSet.KEY_CLIP_FLAG + j);
                            if (clip_text.equals(shared_test)) {
                                isClipExist = true;
                                break;
                            }
                        }

                        if (!isClipExist) {
                            last_num++;

                            sharedAction.setInfo(SharedSet.KEY_CLIP_NUM, last_num + "");
                            sharedAction.setInfo(SharedSet.KEY_CLIP_FLAG + last_num, clip_text);
                        }
                    }

                    clipList = getSharedClip(sharedAction, clipList);
                    return clipList;
                }
            }
        }

        clipList.add("Temp%暂无更新内容");
        clipList = getSharedClip(sharedAction, clipList);

        return clipList;
    }

    private static ArrayList<String> getSharedClip(SharedAction sharedAction, ArrayList<String> clipList) {
        String last_num_text = sharedAction.getInfo(SharedSet.KEY_CLIP_NUM);
        if (!last_num_text.isEmpty()) {
            int last_num = Integer.parseInt(last_num_text);
            for (int i = last_num; i >= 0; i--) {
                String shared_clip = sharedAction.getInfo(SharedSet.KEY_CLIP_FLAG + i);
                clipList.add(shared_clip);
            }
        }

        return clipList;
    }

}
