package com.oji.kreate.lighttransfer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oji.kreate.vsf.publicAdapter.BaseRycAdapter;
import com.oji.kreate.vsf.publicClass.Methods;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/7.
 */

public abstract class TagBtnDownloadRycAdapter extends BaseRycAdapter {

    public TagBtnDownloadRycAdapter(ArrayList dataList) {
        super(dataList);
    }

    @Override
    public ArrayList<String> getDataList() {
        return Methods.cast(super.getDataList());
    }

    @Override
    public DataViewHolder onCreateDataViewHolder(ViewGroup viewGroup) {
        View view = View.inflate(viewGroup.getContext(), R.layout.recycler_base_download_tag, null);
        return new TagButtonHolder(view);
    }

    @Override
    public void onBindDataViewHolder(DataViewHolder dataViewHolder, int i) {
        TagButtonHolder holder = (TagButtonHolder) dataViewHolder;

        ViewGroup.LayoutParams params = holder.tag_btn.getLayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

        holder.tag_btn.setText(getDataList().get(i));
        holder.tag_btn.setLayoutParams(params);
        holder.tag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTag(v);
            }
        });
    }

    @Override
    public void onLoadMore() {

    }

    public class TagButtonHolder extends DataViewHolder {
        public Button tag_btn;

        public TagButtonHolder(View itemView) {
            super(itemView);

            tag_btn = itemView.findViewById(R.id.recycler_bdt_tag_btn);

        }
    }

    public abstract void onClickTag(View view);

}
