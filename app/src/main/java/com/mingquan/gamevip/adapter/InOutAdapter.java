package com.mingquan.gamevip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;
import com.mingquan.gamevip.bean.GameBean;
import com.mingquan.gamevip.bean.InOutBean;
import com.mingquan.gamevip.utils.StringUtils;
import com.mingquan.gamevip.utils.TLog;
import com.vondear.rxtool.view.RxToast;

/**
 * Created by 收支明细adapter on 2019/3/26
 */

public class InOutAdapter extends BaseRecyclerMutilAdapter<InOutBean> {
    public InOutAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(
                context,
                LayoutInflater.from(context).inflate(R.layout.layout_in_out_item, null, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        InOutBean bean = list.get(position);
        holder.getImageView(R.id.image_view).setBackgroundResource(bean.getResourceId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
