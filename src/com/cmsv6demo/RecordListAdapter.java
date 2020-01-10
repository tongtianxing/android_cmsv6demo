package com.cmsv6demo;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.babelstar.gviewer.NetClient;


public class RecordListAdapter extends CustomBaseAdapter<RecordFile> {

	private Activity mActivity;
	private PlaybackItemClick mItemClick;
	
	public RecordListAdapter(Activity activity, PlaybackItemClick itemClick) {
		super(activity);
		mItemClick = itemClick;
		mActivity = activity;
	}

	public RecordListAdapter(Activity activity, List<RecordFile> list) {
		super(activity, list);
		mActivity = activity;
	}

	@Override
	protected View createConvertView(int position) {
		View localView = getLayoutInflater()
				.inflate(R.layout.playback_list_item, null);
		ViewHolder localViewHolder = new ViewHolder();

		localViewHolder.tvTime = (TextView) localView.findViewById(R.id.lyPlaybackItem_tvTime);
		localViewHolder.tvType = (TextView) localView.findViewById(R.id.lyPlaybackItem_tvType);
		localViewHolder.imgBtnPlay = (ImageButton) localView.findViewById(R.id.lyPlaybackItem_play);
		localViewHolder.imgSnapShot = (ImageView) localView.findViewById(R.id.lyPlaybackItem_imgSnapshot);
		
		localView.setTag(localViewHolder);
		return localView;
	}

	@Override
	protected View freshConvertView(View view, final int position) {
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		final RecordFile file = getItem(position);
		
		viewHolder.tvTime.setText(file.getFileTime());
		viewHolder.tvType.setText(file.getDevIdno() + " " + (file.getChn() + 1) + "   " + RecordFile.sGetFileTypeRsID(file.getFileType()));
		
		if (file.getFileType() == NetClient.GPS_FILE_TYPE_ALARM) {
			viewHolder.tvType.setTextColor(Color.RED);
			viewHolder.tvTime.setTextColor(Color.RED);
		} else {
			viewHolder.tvType.setTextColor(Color.BLACK);
			viewHolder.tvTime.setTextColor(Color.BLACK);
		}

		viewHolder.imgBtnPlay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (mItemClick != null) {
					mItemClick.onPlaybackItemClick(position);
				}
		    }
		});
		//view.setBackgroundResource(R.drawable.list_item_selector);
		return view;
	}

	final static class ViewHolder {
		public TextView tvLabelTime;
	    public TextView tvTime;
	    public TextView tvType;
	    public ImageButton imgBtnPlay;
	    public ImageView imgSnapShot;
	}
	
	public static interface PlaybackItemClick {
		/*
		 * 返回获取到的大小
		 */
		public abstract void onPlaybackItemClick(int position);
	}
}
