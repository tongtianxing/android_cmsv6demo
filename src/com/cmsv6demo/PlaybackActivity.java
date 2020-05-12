package com.cmsv6demo;

import net.babelstar.common.play.Playback;
import net.babelstar.common.play.VideoView;
//import net.babelstar.common.play.Playback.PlaybackListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.DisplayMetrics;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PlaybackActivity extends Activity {
	private boolean mIsPlaying = false;
	private Playback mPlayback;
	private VideoView mVideoView;
	
	private Button mBtnStart;
	private Button mBtnStop; 
	private String mDevIdno;

	private boolean mIsDirect;
	private String mServer;
	private  int mPort;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_playback);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int picHeight = screenWidth / 4 * 3;
		mVideoView = (VideoView) findViewById(R.id.imageView1);
		LayoutParams para = mVideoView.getLayoutParams();
		para.width = screenWidth;
		para.height = picHeight;
		mVideoView.setLayoutParams(para);
//		LayoutParams para = mVideoView.getLayoutParams();
//		para.width = screenWidth;
//		para.height = picHeight;
//		mVideoView.setLayoutParams(para);
		
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStop = (Button) findViewById(R.id.btnStop);
		PlayClickListener playClickListen = new PlayClickListener();
		mBtnStart.setOnClickListener(playClickListen);
		mBtnStop.setOnClickListener(playClickListen);
		
		mPlayback = new Playback(this);
		mPlayback.setVideoView(mVideoView);
		Intent intent = getIntent();
		if (intent.hasExtra("DevIDNO")) {
			mDevIdno = intent.getStringExtra("DevIDNO");
		}
		mIsDirect = intent.getBooleanExtra("direct", false);
		if(mIsDirect){
			mServer = intent.getStringExtra("serverIp");
			mPort = intent.getIntExtra("port", 0);
			mDevIdno = intent.getStringExtra("devIdno");
		}
		StartPlayback();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		StopPlayback();
		super.onDestroy();
	}
	
	protected void StartPlayback() {
		if (!mIsPlaying) {
			Intent intent = getIntent();
			byte[] file = intent.getByteArrayExtra("File");
			int nLength = intent.getIntExtra("Length", 0);
			int nChannel = intent.getIntExtra("Channel", 0);
			mPlayback.setPlayerDevIdno(mDevIdno);
			if(mIsDirect){
				mPlayback.setLanInfo(mServer, mPort);
			}
			mPlayback.StartVod(file, nLength, nChannel);
			//mIsPlaying = false;
			mIsPlaying = true;
		}
	}
	
	protected void StopPlayback() {
		if (mIsPlaying) {
			mPlayback.StopVod();
			mIsPlaying = false;
		}
	}
	
	final class PlayClickListener implements OnClickListener {
		public void onClick(View v) {
			if (v.equals(mBtnStart)) {
				StartPlayback();
			} else if (v.equals(mBtnStop)) {
				StopPlayback();
			}
		}
	}
}
