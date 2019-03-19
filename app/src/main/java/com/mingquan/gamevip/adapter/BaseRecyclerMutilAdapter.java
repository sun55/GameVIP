package com.mingquan.gamevip.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.mingquan.gamevip.adapter.holder.RecyclerViewHolder;

import java.util.LinkedList;
import java.util.List;

/**
 * 基本RecyclerViewAdapter
 * @param <T>
 */
public abstract class BaseRecyclerMutilAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

	protected final List<T> list;
	protected final Context context;
	protected LayoutInflater inflater;

	protected OnItemClickListener mClickListener;
	protected OnItemLongClickListener mLongClickListener;


	public BaseRecyclerMutilAdapter(Context ctx) {

		context = ctx;
		inflater = LayoutInflater.from(ctx);

		list = new LinkedList<>();
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	public void setList(List<T> listData) {
		if (listData != null) {
			this.list.clear();
			this.list.addAll(listData);
			this.notifyDataSetChanged();
		}
	}

	public void appendList(List<T> listData) {
		if (listData != null) {
			this.list.addAll(listData);
			this.notifyDataSetChanged();
		}
	}

	public void addItem(int pos, T item) {
		list.add(pos, item);
		notifyItemInserted(pos);
	}

	public void removeItem(int pos) {
		if (list.size() >pos && pos >= 0) {
			list.remove(pos);
			notifyItemRemoved(pos);
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		mClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		mLongClickListener = listener;
	}


    public interface OnItemClickListener {
		void onItemClick(View view, int pos);
	}

	public interface OnItemLongClickListener {
		void onItemLongClick(View view, int pos);
	}


}
