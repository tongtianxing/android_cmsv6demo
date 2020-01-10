package com.cmsv6demo;



import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CustomMenuDialog extends Dialog implements AdapterView.OnItemClickListener {
	private CustomMenuAdapter mAdapter;
	private OnListener onClickListener;

	public CustomMenuDialog(Context context, int width, int height, int[] images, String[] names) {
		super(context);
		getContext().setTheme(16973840);
		super.setContentView(R.layout.custom_menu);
		LinearLayout localLinearLayout1 = (LinearLayout)findViewById(R.id.lyCustomMenu_context_layout);
		this.mAdapter = new CustomMenuAdapter(context, images, names);
		ListView localListView = (ListView)findViewById(R.id.lyCustomMenu_context_listview);
		localListView.setAdapter(this.mAdapter);
		
		int j = 0;
		int k = 0;
		int m = this.mAdapter.getCount();
		while (true) {
			if (k >= m) {
				//int n = localListView.getDividerHeight();
				//int i1 = this.mAdapter.getCount();
				//int i3 = n * i1;
				//int i4 = j + i3;
				//int i5 = this.mAdapter.getCount() + 1;
				int i6 = width * 3 / 4;
				LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i6, j);
				localLinearLayout1.setLayoutParams(localLayoutParams);
				localListView.setOnItemClickListener(this);
				//LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131296339);
				Window localWindow = getWindow();
				localWindow.setGravity(Gravity.CENTER);
				//WindowManager.LayoutParams localLayoutParams1 = localWindow.getAttributes();
				int i7 = width;
				int i8 = height;
				localWindow.setLayout(i7, i8);
				break;
			}
			
			View localView = this.mAdapter.getView(k, null, localListView);
			localView.measure(0, 0);
			j += (localView.getMeasuredHeight() + 5);
			k++;
		}
	}

	private void superDismiss() {
		super.dismiss();
	}

	public void dismiss() {
		if (isShowing()) {
			superDismiss();
		}
	}

	public void onItemClick(AdapterView adapterView, View view, int position, long paramLong)
	{
		superDismiss();
		if (this.onClickListener != null) {
			OnListener localOnListener = this.onClickListener;
			int i = this.mAdapter.getCount();
			localOnListener.onItemClick(position, i);
		}
	}

	public void setOnClickListener(OnListener paramOnListener) {
		this.onClickListener = paramOnListener;
	}

	public void show() {
		super.show();
	}

	public abstract interface OnListener
	{
		public abstract void onItemClick(int paramInt1, int paramInt2);
	}  
}
