package com.mingquan.gamevip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;
import com.mingquan.gamevip.bean.GameBean;
import com.mingquan.gamevip.utils.StringUtils;
import com.mingquan.gamevip.utils.TLog;
import com.vondear.rxtool.view.RxToast;

/**
 * Created by Administrator on 2019/3/19
 */

public class GameAdapter extends BaseRecyclerMutilAdapter<GameBean> {
    public GameAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(
                context,
                LayoutInflater.from(context).inflate(R.layout.layout_game_item, null, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        GameBean gameBean = list.get(position);
        TLog.info("game bean %s", gameBean.toString());
        holder.getImageView(R.id.iv_game_icon).setBackgroundResource(gameBean.getIconResourceId());
        holder.getTextView(R.id.tv_game_name).setText(gameBean.getGameName());
        holder.getTextView(R.id.tv_game_detail).setText(gameBean.getDetail());
        holder.getTextView(R.id.tv_game_discount).setText(gameBean.getDiscount());
        if (StringUtils.isEmpty(gameBean.getTag())) {
            holder.getTextView(R.id.tv_game_tag).setVisibility(View.GONE);
        } else {
            holder.getTextView(R.id.tv_game_tag).setText(gameBean.getTag());
            holder.getTextView(R.id.tv_game_tag).setVisibility(View.VISIBLE);
        }
        holder.getView(R.id.ll_game_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxToast.showToast("敬请期待");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
