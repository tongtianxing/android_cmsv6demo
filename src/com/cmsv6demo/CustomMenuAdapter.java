package com.cmsv6demo;



import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomMenuAdapter extends BaseAdapter {
	private Context context;
	private int[] imgs;
	private LayoutInflater inflater;
	private String[] ss;

	public CustomMenuAdapter(Context context, int[] images, String[] names){
		this.context = context;
		LayoutInflater localLayoutInflater = LayoutInflater.from(context);
		this.inflater = localLayoutInflater;
		Resources localResources = context.getResources();
		imgs = new int[images.length];
		ss = new String[images.length];
		for (int i = 0; i < images.length; ++ i) {
			imgs[i] = images[i];
			ss[i] = names[i];
		}
		/*imgs[0] = R.drawable.camera_live;
		imgs[1] = R.drawable.camera_record;
		imgs[2] = R.drawable.camera_detail;
		imgs[3] = R.drawable.camera_detail;
		imgs[4] = R.drawable.camera_record;
		imgs[5] = R.drawable.camera_delete;
		ss = new String[6];
		ss[0] = localResources.getString(R.string.cam_real_video);
		ss[1] = localResources.getString(R.string.cam_view_record);
		//ss[2] = localResources.getString(R.string.check_video);
		ss[2] = localResources.getString(R.string.cam_view_detail);
		ss[3] = localResources.getString(R.string.cam_parameter);
		ss[4] = localResources.getString(R.string.cam_storage_plan);
		ss[5] = localResources.getString(R.string.cam_delete);*/
	}

	public int getCount()
	{
		return this.ss.length;
	}

	public Object getItem(int paramInt)
	{
		return Integer.valueOf(paramInt);
	}

	public long getItemId(int paramInt)
	{
		return paramInt;
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		ViewGroup localViewGroup = null;
		ViewHolder localViewHolder;
		if (view == null) {
			view = this.inflater.inflate(R.layout.custom_menu_item, localViewGroup);
			localViewHolder = new ViewHolder();
			localViewHolder.img = (ImageView)view.findViewById(R.id.lyCustomMenu_img);
			localViewHolder.tv = (TextView)view.findViewById(R.id.lyCustomMenu_text);
			view.setTag(localViewHolder);
		} else {
			localViewHolder = (ViewHolder) view.getTag();
		}
		
		localViewHolder.img.setBackgroundResource(imgs[position]);
		localViewHolder.tv.setText(ss[position]);
		return view;
	}

	class ViewHolder {
		ImageView img;
		TextView tv;
	}
}
