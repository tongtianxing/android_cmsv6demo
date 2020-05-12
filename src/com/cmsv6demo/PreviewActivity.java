package com.cmsv6demo;

import net.babelstar.common.play.RealPlay;
import net.babelstar.common.play.Talkback;
import net.babelstar.common.play.Monitor;
import net.babelstar.common.play.VideoView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.babelstar.gviewer.NetClient;

public class PreviewActivity extends Activity {
	private boolean mIsStartAV = false;
	private VideoView mVideoImage1;
	private VideoView mVideoImage2;
	private RealPlay mRealPlay1;
	private RealPlay mRealPlay2;
	private Talkback mTalkback;
	private String mServer;
	private String mDevIdno;
	
	private Monitor mMonitor;
	private Button mBtnSound1;
	private Button mBtnSound2; 
	private Button mBtnStart;
	private Button mBtnStop; 
	private Button mBtnTalkStart;
	private Button mBtnTalkStop;
	
	private Button mBtnMonitorStart;
	private Button mBtnMonitorStop;

	private boolean mIsDirect;
	private int mPort;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		
		mVideoImage1 = (VideoView) findViewById(R.id.imageView1);
		mVideoImage2 = (VideoView) findViewById(R.id.imageView2);
		mBtnSound1 = (Button) findViewById(R.id.btnSound1);
		mBtnSound2 = (Button) findViewById(R.id.btnSound2);
		mBtnTalkStart = (Button) findViewById(R.id.btnTalkStart);
		mBtnTalkStop = (Button) findViewById(R.id.btnTalkStop);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStop = (Button) findViewById(R.id.btnStop);
		
		mBtnMonitorStart =(Button) findViewById(R.id.btnMonitorStart);
		mBtnMonitorStop = (Button) findViewById(R.id.btnMonitorStop);
		PlayClickListener playClickListen = new PlayClickListener();
		
		mBtnStart.setOnClickListener(playClickListen);
		mBtnStop.setOnClickListener(playClickListen);		
		mBtnSound1.setOnClickListener(playClickListen);
		mBtnSound2.setOnClickListener(playClickListen);
		mBtnTalkStart.setOnClickListener(playClickListen);
		mBtnTalkStop.setOnClickListener(playClickListen);		
		mBtnMonitorStart.setOnClickListener(playClickListen);
		mBtnMonitorStop.setOnClickListener(playClickListen);		
		mRealPlay1 = new RealPlay(this);
		mRealPlay2 = new RealPlay(this);
		mRealPlay1.setVideoView(mVideoImage1);
		mRealPlay2.setVideoView(mVideoImage2);		
		         
		NetClient.Initialize("/mnt/sdcard/");
		Intent intent = getIntent();
		mServer = intent.getStringExtra("serverIp");
		mPort = intent.getIntExtra("port", 0);
		mDevIdno = intent.getStringExtra("devIdno");
		mIsDirect = intent.getBooleanExtra("direct", false);
		if (!updateServer()) {
			return ;
		}
		StartAV();
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		StopAV();
		NetClient.UnInitialize();
		super.onDestroy();
	}
	
	protected boolean updateServer() {
		
    	if (mServer.isEmpty() || mDevIdno.isEmpty()) {
    		Toast.makeText(getApplicationContext(), "server or devidno is empty", Toast.LENGTH_SHORT).show(); 
    		return false;
    	}
    	

		return true;
	}
	
	protected void StartAV() {
		if (!mIsStartAV) {
			
			///直连播放
			mRealPlay1.setLanInfo(mServer, mPort);
			mRealPlay2.setLanInfo(mServer, mPort);
			mRealPlay1.setViewInfo(mDevIdno, mDevIdno, 0, "CH1");
	       	mRealPlay2.setViewInfo(mDevIdno, mDevIdno, 1, "CH2");
	      //是否铺满画面 true 是  false否
	       	mRealPlay1.setVideoBmpExtend(false);
	       	mRealPlay2.setVideoBmpExtend(false);
	       	
	       	mRealPlay1.StartAV(false, true);
	       	mRealPlay2.StartAV(false, true);
	       	mIsStartAV = true;
		}
	}
	
	protected void StopAV() {

		mRealPlay1.StopAV();
		mRealPlay2.StopAV();
		mIsStartAV = false;
	}
	
	protected void onSound1() {
		mRealPlay1.playSound();
		mRealPlay2.stopSound();
	}
	
	protected void onSound2() {
		mRealPlay1.stopSound();
		mRealPlay2.playSound();
	}
	
	protected void onTalkStart() {
		if (mTalkback == null) {
			if (!updateServer()) {
				return ;
			}
			
			mTalkback = new Talkback();
			mTalkback.startTalkback(mDevIdno, 0);
		}
	}
	
	protected void onTalkStop() {
		if (mTalkback != null) {
			mTalkback.stopTalkback();
			mTalkback = null;
		}
	}
	
	protected void onMonitorStart() {
		if (mMonitor == null) {
			if (!updateServer()) {
				return ;
			}
			
			mMonitor = new Monitor();
			mMonitor.startMonitor(mDevIdno, 0);
			
		}
	}
	
	protected void onMonitorStop() {
		if (mMonitor != null) {
			mMonitor.stopMonitor();
			mMonitor = null;
		}
	}
	
	final class PlayClickListener implements OnClickListener {
		public void onClick(View v) {
			if (v.equals(mBtnStart)) {
				StartAV();
			} else if (v.equals(mBtnStop)) {
				StopAV();
			} else if (v.equals(mBtnSound1)) {
				onSound1();
			} else if (v.equals(mBtnSound2)) {
				onSound2();
			} else if (v.equals(mBtnTalkStart)) {
				onTalkStart();
			} else if (v.equals(mBtnTalkStop)) {
				onTalkStop();
			}
			else if (v.equals(mBtnMonitorStart)) {
				onMonitorStart();
			} else if (v.equals(mBtnMonitorStop)) {
				onMonitorStop();
			}
		}
	}
		
}
