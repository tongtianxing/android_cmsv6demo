package net.babelstar.cmsv6demo.adapter;

import java.util.List;





import net.babelstar.cmsv6demo.model.bd808.DeviceSearch;

import com.cmsv6demo.CustomBaseAdapter;
import com.cmsv6demo.R;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DeviceSearchAdapter extends CustomBaseAdapter<DeviceSearch> {

	private SearchItemClick mSearchItemClick;
	
	public DeviceSearchAdapter(Context context, SearchItemClick itemClick) {
		super(context);
		mSearchItemClick = itemClick;
	}

	public DeviceSearchAdapter(Context context, List<DeviceSearch> list) {
		super(context, list);
	}

	@Override
	protected View createConvertView(int position) {
		View localView = getLayoutInflater()
				.inflate(R.layout.devsearch_list_item, null);
		ViewHolder localViewHolder = new ViewHolder();

		localViewHolder.tvLabelIdno = (TextView) localView.findViewById(R.id.lySearchItem_tvLabelIdno);
		localViewHolder.tvDevIdno = (TextView) localView.findViewById(R.id.lySearchItem_tvIdno);
		localViewHolder.tvNetwork = (TextView) localView.findViewById(R.id.lySearchItem_tvNetName);
		localViewHolder.imgBtnAdd = (ImageButton) localView.findViewById(R.id.lySearchItem_add);
		localViewHolder.imgSnapShot = (ImageView) localView.findViewById(R.id.lySearchItem_imgSnapshot);
		
		localView.setTag(localViewHolder);
		return localView;
	}

	@Override
	protected View freshConvertView(View view, final int position) {
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		final DeviceSearch search = getItem(position);
		
		viewHolder.tvLabelIdno.setVisibility(View.VISIBLE);
		viewHolder.tvDevIdno.setText(search.getDevIdno());
		if (search.getNetType().intValue() == 0) {
			viewHolder.tvNetwork.setText("3G");
		} else if (search.getNetType().intValue() == 1) {
			viewHolder.tvNetwork.setText("Wifi(" + search.getNetName() + ")  " + search.getIpAddr());
		} else {
			viewHolder.tvNetwork.setText(search.getIpAddr());
		}
		
		viewHolder.imgBtnAdd.setVisibility(View.VISIBLE);
		
		viewHolder.imgBtnAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (mSearchItemClick != null) {
					mSearchItemClick.onSearchItemClick(position);
				}
		    }
		});
		return view;
	}

	final static class ViewHolder {
		public TextView tvLabelIdno;
	    public TextView tvDevIdno;
	    public TextView tvNetwork;
	    public ImageButton imgBtnAdd;
	    public ImageView imgSnapShot;
	}
	
	public static interface SearchItemClick {
		/*
		 * 返回获取到的大小
		 */
		public abstract void onSearchItemClick(int position);
	}
}
