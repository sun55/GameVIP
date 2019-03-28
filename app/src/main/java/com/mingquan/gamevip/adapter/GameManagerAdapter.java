package com.mingquan.gamevip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;
import com.mingquan.gamevip.bean.GameManagerBean;
import com.mingquan.gamevip.bean.SocietyBean;

/**
 * Created by 游戏管家adapter on 2019/3/27
 */

public class GameManagerAdapter extends BaseRecyclerMutilAdapter<GameManagerBean> {
    public GameManagerAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(
                context,
                LayoutInflater.from(context).inflate(R.layout.layout_game_manager_item, null, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        GameManagerBean bean = list.get(position);
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
