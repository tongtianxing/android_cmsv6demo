package com.cmsv6demo;
import android.app.TabActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


//import android.os.Environment;
//import com.google.code.microlog4android.Level;
public class MainDirectActivity extends TabActivity {

	private Intent[] mTabIntent;
	private static Context mContext = null;
	private TabHost mTabHost;
	private TabWidget mTabWidget;
	private static String TOOLBAR_VIDEO = "video";
	private static String TOOLBAR_PLAYBACK = "playback";
	private static int TAB_INDEX_PREVIEW = 0;
	private static int TAB_INDEX_PLAYBACK = 1;
	private String devId;
	private String serverIp;
	private int nPort = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mTabHost = getTabHost();
		mTabWidget = getTabWidget();
		mTabHost.setup();
		LinearLayout layout = (LinearLayout)mTabHost.getChildAt(0);
		TabWidget tw = (TabWidget)layout.getChildAt(1);

		Intent intent = getIntent();
		serverIp = intent.getStringExtra("serverIp");
		devId = intent.getStringExtra("devIdno");
		nPort = intent.getIntExtra("port", 6688);
		mTabIntent = new Intent[2];
		int index = 0;

		mTabIntent[index] = new Intent(this, PreviewActivity.class);
		mTabIntent[index].putExtra("devIdno",devId);
		mTabIntent[index].putExtra("serverIp",serverIp);
		mTabIntent[index].putExtra("port",nPort);
		mTabIntent[index].putExtra("direct", true);	//是否直连
		addTab(tw, R.string.toolbar_video, R.drawable.toolbar_select_video, TOOLBAR_VIDEO, mTabIntent[index]);
		index ++;

		mTabIntent[index] = new Intent(this, RecordActivity.class);
		mTabIntent[index].putExtra("devIdno",devId);
		mTabIntent[index].putExtra("serverIp",serverIp);
		mTabIntent[index].putExtra("port",nPort);
		mTabIntent[index].putExtra("direct", true);	//是否直连
		addTab(tw, R.string.toolbar_playback, R.drawable.toolbar_select_playback, TOOLBAR_PLAYBACK, mTabIntent[index]);
		index ++;
		mTabHost.setOnTabChangedListener(new TabHostChangeListener());
		mTabHost.setCurrentTab(TAB_INDEX_PREVIEW);


	}


	/**
	 * 初始化选项卡
	 *
	 * */
	public void addTab(TabWidget tw, int text, int image, String tag, Intent intent){
		RelativeLayout tabLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.tab_indicator, tw, false);
		TextView tvTab = (TextView)tabLayout.getChildAt(1);
		tvTab.setText(text);
		tvTab.setTextColor(getResources().getColor(R.color.gray1));
		ImageView ivTab = (ImageView)tabLayout.getChildAt(0);
		ivTab.setBackgroundResource(image);

		TabHost.TabSpec tSpecWall = mTabHost.newTabSpec(tag);
		tSpecWall.setIndicator(tabLayout);
		tSpecWall.setContent(intent);
		mTabHost.addTab(tSpecWall);
	}
	final class TabHostChangeListener implements TabHost.OnTabChangeListener {

		public void onTabChanged(String tabId) {
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
	}
	

}
