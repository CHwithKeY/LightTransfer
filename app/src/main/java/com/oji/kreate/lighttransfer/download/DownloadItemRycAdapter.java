package com.oji.kreate.lighttransfer.download;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oji.kreate.lighttransfer.R;
import com.oji.kreate.vsf.publicAdapter.BaseRycAdapter;
import com.oji.kreate.vsf.publicClass.Methods;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/30.
 */

public class DownloadItemRycAdapter extends BaseRycAdapter {

    public DownloadItemRycAdapter(ArrayList dataList) {
        super(dataList);
    }

    @Override
    public ArrayList<DownloadItem> getDataList() {
        return Methods.cast(super.getDataList());
    }

    @Override
    public DataViewHolder onCreateDataViewHolder(ViewGroup viewGroup) {
        View view = View.inflate(viewGroup.getContext(), R.layout.recycler_base_download_item, null);
        return new DownloadItemHolder(view);
    }

    @Override
    public void onBindDataViewHolder(DataViewHolder dataViewHolder, int i) {
        DownloadItemHolder holder = (DownloadItemHolder) dataViewHolder;

        DownloadItem item = getDataList().get(i);
        holder.text_tv.setText(item.getText());
        holder.time_tv.setText(item.getTime());
    }

    @Override
    public void onLoadMore() {

    }

    public class DownloadItemHolder extends DataViewHolder {

        public TextView time_tv;
        public TextView text_tv;

        public DownloadItemHolder(View itemView) {
            super(itemView);

            time_tv = itemView.findViewById(R.id.recycler_bdi_time_tv);
            text_tv = itemView.findViewById(R.id.recycler_bdi_text_tv);
        }
    }

}
