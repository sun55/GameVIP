package com.mingquan.gamevip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;
import com.mingquan.gamevip.bean.GameBean;

/**
 * Created by Administrator on 2019/3/19
 */

public class HomeNewGameAdapter extends BaseRecyclerMutilAdapter<GameBean> {
    public HomeNewGameAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(
                context,
                LayoutInflater.from(context).inflate(R.layout.layout_home_game_item, null, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        GameBean bean = list.get(position);
        holder.getImageView(R.id.iv_game_icon).setBackgroundResource(bean.getIconResourceId());
        holder.getTextView(R.id.tv_game_name).setText(bean.getGameName());
        holder.getTextView(R.id.tv_game_detail).setText(bean.getDetail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
