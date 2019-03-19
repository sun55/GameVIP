package com.mingquan.gamevip.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingquan.gamevip.R;
import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;
import com.vondear.rxtool.view.RxToast;

/**
 * Created by Administrator on 2019/3/19
 */

public class NewGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxToast.showToast("敬请期待");
            }
        });
        return new RecyclerViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
