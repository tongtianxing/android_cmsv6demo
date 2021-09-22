package com.cmsv6demo;

import net.babelstar.common.play.PBDownLoad;
import net.babelstar.common.play.Playback;
import net.babelstar.common.play.VideoView;
import net.babelstar.common.util.AlbumNotifyHelper;
import net.babelstar.common.util.ElementTypeJudgeUtil;
//import net.babelstar.common.play.Playback.PlaybackListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.util.DisplayMetrics;
import android.os.Environment;
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
	private PBDownLoad pbDownLoad = null;
	private VideoView mVideoView;
	
	private Button mBtnStart;
	private Button mBtnStop;
	private Button mBtnDownLoad;
	private String mDevIdno;
	private String mVelIdno; //目前demo测试时候车牌号和终端id一样

	private boolean mIsDirect;
	private String mServer;
	private  int mPort;
	private Context mContext;
	private byte[] mFile = null;
	private int mnLength = 0;
	private int mnChannel = 0;

	private String mDatePb;
	private String mTimeExPb;
	private int mFileType = 0;
	private MyPBDownLoadListener myPBDownLoadListener = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_playback);
		mContext = this.getApplicationContext();
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
		mBtnDownLoad = (Button) findViewById(R.id.btndownload);
		PlayClickListener playClickListen = new PlayClickListener();
		mBtnStart.setOnClickListener(playClickListen);
		mBtnStop.setOnClickListener(playClickListen);
		mBtnDownLoad.setOnClickListener(playClickListen);
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
		mFile = intent.getByteArrayExtra("File");
		mnLength = intent.getIntExtra("Length", 0);
		mnChannel = intent.getIntExtra("Channel", 0);

		mDatePb = intent.getStringExtra("date");
		mTimeExPb = intent.getStringExtra("time");
		mFileType = intent.getIntExtra("fileType", 0);
		mVelIdno = mDevIdno;
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
			mPlayback.setPlayerDevIdno(mDevIdno);
			if(mIsDirect){
				mPlayback.setLanInfo(mServer, mPort);
			}
			mPlayback.StartVod(mFile, mnLength, mnChannel);
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

	protected void downLoadPlayback() {
		if(pbDownLoad == null){
			pbDownLoad = new PBDownLoad(mContext);
			if(myPBDownLoadListener == null){
				myPBDownLoadListener = new MyPBDownLoadListener();
			}
			pbDownLoad.setDownLoadListener(myPBDownLoadListener);
			pbDownLoad.setPBDownLoadDevIdno(mDevIdno);
			String timeTmp =mDatePb + "-" + mTimeExPb;
			pbDownLoad.setLanInfo(mServer, mPort);
			String DOWBLOAD_DIRECTORY = "gStorage/pbdownload/";
			String downLoadPath = Environment.getExternalStorageDirectory().getPath() + "/" + DOWBLOAD_DIRECTORY;
			String fileDownLoadPath = Environment.getExternalStorageDirectory().getPath() + "/" + DOWBLOAD_DIRECTORY;
			fileDownLoadPath += mVelIdno + "(" + mDevIdno + ")" + "/" + mDatePb;
			pbDownLoad.setPBDownLoadPathEx(downLoadPath, fileDownLoadPath, mVelIdno, timeTmp, mFileType);
			pbDownLoad.StartPBDownLoad(mFile, mnLength, mnChannel);
		}
	}


	final protected class MyPBDownLoadListener implements PBDownLoad.PBDownLoadListener  {

		@Override
		public void onBeginPlay() {
//			bIsFinsh = false;
		}

		@Override
		public void onUpdatePlay(int nDownSecond) {
//			downSecond = nDownSecond / 1000;
//			if(downSecond > max){
//				downSecond = max;
//			}
//			schedule = GpsUtil.getPBDownLoadPercent(downSecond, max);
//			schedule += "%";
//			if(mDownLoadAdapter !=null){
//				mDownLoadAdapter.notifyDataSetChanged();
//			}
		}

		@Override
		public void onEndPlay(int nDownStatus) {
			pbDownLoad.StopPBDownLoad();

		}
	}

	final class PlayClickListener implements OnClickListener {
		public void onClick(View v) {
			if (v.equals(mBtnStart)) {
				StartPlayback();
			} else if (v.equals(mBtnStop)) {
				StopPlayback();
			}else if (v.equals(mBtnDownLoad)) {
				downLoadPlayback();
			}
		}
	}
}
