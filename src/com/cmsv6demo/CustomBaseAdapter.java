package com.cmsv6demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CustomBaseAdapter<T> extends BaseAdapter {
	private List<T> mData;
	private LayoutInflater mLayoutInflater;
	protected Context mContext;

	protected CustomBaseAdapter(Context context) {
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mContext = context;
	}

	protected CustomBaseAdapter(Context context, List<T> paramList) {
		this(context);
		this.mData = paramList;
	}

	public void clear() {
		if (this.mData == null)
			return;
		this.mData.clear();
	}

	protected abstract View createConvertView(int position);

	protected abstract View freshConvertView(View view, int position);

	public int getCount() {
		if (this.mData == null)
			return 0;
		return this.mData.size();
	}

	public List<T> getData() {
		return this.mData;
	}

	public T getItem(int location) {
		if (this.mData == null)
			return null;

		return this.mData.get(location);
	}

	public long getItemId(int position) {
		return position;
	}

	protected LayoutInflater getLayoutInflater() {
		return this.mLayoutInflater;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		if ((position >= getData().size()) || (getData().size() == 0))
			return null;

		if (view == null) {
			view = createConvertView(position);
		}

		View localView = freshConvertView(view, position);
		return localView;
	}

	public void release() {
	}

	public void remove(T object) {
		if (this.mData == null)
			return;
		this.mData.remove(object);
	}

	public void setData(List<T> list) {
		this.mData = list;
	}
}
