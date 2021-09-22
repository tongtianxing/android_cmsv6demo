package com.cmsv6demo;

import org.json.JSONException;
import org.json.JSONObject;

import net.babelstar.common.http.AbstractAsyncResponseListener;
import net.babelstar.common.http.AsyncHttpClient;
import net.babelstar.common.play.RealPlay;
import net.babelstar.common.play.Talkback;
import net.babelstar.common.play.Monitor;
import net.babelstar.common.play.VideoView;
import net.babelstar.common.util.PermissionUtils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Environment;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babelstar.gviewer.NetClient;
//import com.google.code.microlog4android.Level;

public class MainActivity extends Activity {

	public static final int NET_SUCCESS = 0;
	public static final int NET_ERR_RUNNING	= 8;	//Business process being executed

	public static final int GPS_CTRL_TYPE_FLIP_NORMAL 	= 16;	//Forward Flip
	public static final int GPS_CTRL_TYPE_FLIP_REVERSE 	= 17;	//Reverse Flip

	public static final int SEARCH_FINISHED = 99;
	public static final int SEARCH_FAILED = 100;
	public static final int SEARCH_DEFAULT_PORT = 6688;

	public static final int GPS_PTZ_MOVE_LEFT	= 0;
	public static final int GPS_PTZ_MOVE_RIGHT	= 1;
	public static final int GPS_PTZ_MOVE_TOP	= 2;
	public static final int GPS_PTZ_MOVE_BOTTOM	= 3;
	public static final int GPS_PTZ_MOVE_LEFT_TOP	= 4;
	public static final int GPS_PTZ_MOVE_RIGHT_TOP	= 5;
	public static final int GPS_PTZ_MOVE_LEFT_BOTTOM	= 6;
	public static final int GPS_PTZ_MOVE_RIGHT_BOTTOM	= 7;

	public static final int GPS_PTZ_FOCUS_DEL =	8;
	public static final int GPS_PTZ_FOCUS_ADD =	9;
	public static final int GPS_PTZ_LIGHT_DEL = 10;
	public static final int GPS_PTZ_LIGHT_ADD = 11;
	public static final int GPS_PTZ_ZOOM_DEL = 13;
	public static final int GPS_PTZ_ZOOM_ADD = 12;
	public static final int GPS_PTZ_LIGHT_OPEN	= 14;
	public static final int GPS_PTZ_LIGHT_CLOSE	= 15;
	public static final int GPS_PTZ_WIPER_OPEN	= 16;
	public static final int GPS_PTZ_WIPER_CLOSE	= 17;
	public static final int GPS_PTZ_CRUISE	= 18;
	public static final int GPS_PTZ_MOVE_STOP = 19;

	public static final int GPS_PTZ_SPEED_DEFAULT = 128;

	public static final int GPS_RGB_FORMAT_565 = 1;
	public static final int GPS_RGB_FORMAT_888 = 2;
	public static final int GPS_RGB_FORMAT_8888	= 3;
	public static final int GPS_YUV420P_FORMAT	= 4;

	//Network Type
	public static final int GPS_NET_TYPE_3G		= 0;
	public static final int GPS_NET_TYPE_WIFI	= 1;
	public static final int GPS_NET_TYPE_NET	= 2;

	public static final int GPS_FILE_LOCATION_DEVICE	= 1;		//Device
	public static final int GPS_FILE_LOCATION_STOSVR	= 2;		//Storage server
	//public static final int GPS_FILE_LOCATION_LOCAL		= 3;	//Client
	public static final int GPS_FILE_LOCATION_DOWNSVR	= 4;		//Auto download server
	//文件类型
	public static final int GPS_FILE_ATTRIBUTE_JPEG		= 1;
	public static final int GPS_FILE_ATTRIBUTE_RECORD	= 2;
	///#define GPS_FILE_ATTRIBUTE_ALL				3		//
	public static final int GPS_FILE_ATTRIBUTE_LOG		= 4;

	public static final int GPS_CHANNEL_ALL				= 99;	//All Channel

	public static final int GPS_FILE_TYPE_NORMAL		= 0;
	public static final int GPS_FILE_TYPE_ALARM			= 1;
	public static final int GPS_FILE_TYPE_ALL			= -1;

	public static final int GPS_STREAM_MODE_FILE		= 1;	//文件模式
	public static final int GPS_STREAM_MODE_REAL		= 2;	//实时模式
	public static final int GPS_STREAM_MODE_STREAM		= 3;	//流模式

	//0：音视频， 1：音频， 2：视频 3;视频或者音视频
	public static final int GPS_MEDIA_TYPE_AUDIO_VIDEO		= 0;
	public static final int GPS_MEDIA_TYPE_AUDIO			= 1;
	public static final int GPS_MEDIA_TYPE_VIDEO			= 2;
	public static final int GPS_MEDIA_TYPE_AUDIO_OR_VIDEO 	= 3;

	//-1:主码流或者子码流,0：主码流,1：子码流
	public static final int GPS_STREAM_TYPE_MAIN_SUB		= -1;
	public static final int GPS_STREAM_TYPE_MAIN			= 0;
	public static final int GPS_STREAM_TYPE_SUB				= 1;

	//0:主存储器或者灾备存储器, 1：主存储器， 2：灾备存储器
	public static final int GPS_MEMORY_TYPE_MAIN_SUB		= 0;
	public static final int GPS_MEMORY_TYPE_MAIN			= 1;
	public static final int GPS_MEMORY_TYPE_SUB				= 2;

	private boolean mIsStartAV = false;
	private VideoView mVideoImage1;
	private VideoView mVideoImage2;
	private RealPlay mRealPlay1;
	private RealPlay mRealPlay2;
	private Talkback mTalkback;
	private String mServer;
	private String mDevIdno;
	
	private Monitor mMonitor;
	private EditText mEtServer;
	private EditText mEtDevIdno;
	private Button mBtnSound1;
	private Button mBtnSound2; 
	private Button mBtnStart;
	private Button mBtnStop; 
	private Button mBtnRecord; 
	private Button mBtnTalkStart;
	private Button mBtnTalkStop;
	
	private Button mBtnMonitorStart;
	private Button mBtnMonitorStop;
	
	private SharedPreferences mPreferences;
	private boolean m_Login;
	private String mSession;
	private Button mBtnDirectSearch;
	private NetClient mNetClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mVideoImage1 = (VideoView) findViewById(R.id.imageView1);
		mVideoImage2 = (VideoView) findViewById(R.id.imageView2);
		mBtnSound1 = (Button) findViewById(R.id.btnSound1);
		mBtnSound2 = (Button) findViewById(R.id.btnSound2);
		mBtnTalkStart = (Button) findViewById(R.id.btnTalkStart);
		mBtnTalkStop = (Button) findViewById(R.id.btnTalkStop);
		mEtDevIdno = (EditText) findViewById(R.id.ed_idno);
		mEtServer = (EditText) findViewById(R.id.ed_server);
		mBtnStart = (Button) findViewById(R.id.btnStart);
		mBtnStop = (Button) findViewById(R.id.btnStop);
		mBtnRecord = (Button) findViewById(R.id.btnRecord);
		mBtnMonitorStart =(Button) findViewById(R.id.btnMonitorStart);
		mBtnMonitorStop = (Button) findViewById(R.id.btnMonitorStop);
		mBtnDirectSearch = (Button) findViewById(R.id.btnDirectSearch);
		PlayClickListener playClickListen = new PlayClickListener();
		mBtnStart.setOnClickListener(playClickListen);
		mBtnStop.setOnClickListener(playClickListen);
		mBtnRecord.setOnClickListener(playClickListen);
		mBtnSound1.setOnClickListener(playClickListen);
		mBtnSound2.setOnClickListener(playClickListen);
		mBtnTalkStart.setOnClickListener(playClickListen);
		mBtnTalkStop.setOnClickListener(playClickListen);
		
		mBtnMonitorStart.setOnClickListener(playClickListen);
		mBtnMonitorStop.setOnClickListener(playClickListen);
		mBtnDirectSearch.setOnClickListener(playClickListen);
		
		mRealPlay1 = new RealPlay(this);
		mRealPlay2 = new RealPlay(this);
		mRealPlay1.setVideoView(mVideoImage1);
		mRealPlay2.setVideoView(mVideoImage2);
		// 如果服务器做了限制 必须要用户登录才能看视频 调用一下接口
//		String url = "http://192.168.1.96:80/LoginAction_loginMobile.action?update=gViewerAndroid&server=login&userAccount=admin&password=admin";
//		AsyncHttpClient.sendRequest(this, url, null, new LoginResponseListener());
		mPreferences = getSharedPreferences("com.cmsv6demo", 0);
		//demo测试录像回放时候手动apk打开音频，存储，读写权限
		String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
				Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO};
		PermissionUtils.getInstance().chekPermissions(MainActivity.this, permissions, permissionsResult);

        String server = mPreferences.getString("Server", "192.168.1.230");
        server = "192.168.1.96";
        //server = "103.237.144.141";
        mEtServer.setText(server);
        String devIdno = mPreferences.getString("DevIDNO", "50003");
        //devIdno = "100012";
        devIdno = "1234566618";
       	mEtDevIdno.setText(devIdno);
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
//		NetClient.Initialize("/mnt/sdcard/");
		mNetClient = new NetClient();
		mNetClient.Initialize(sdPath);
		mNetClient.SetJniEnv();
		if (!updateServer()) {
			return ;
		}
		StartAV();
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
		StopAV();
		mNetClient.UnInitialize();
		super.onDestroy();
	}
	
	protected boolean updateServer() {
		mServer = this.mEtServer.getText().toString().trim();
    	mDevIdno = this.mEtDevIdno.getText().toString().trim();
    	
    	if (mServer.isEmpty() || mDevIdno.isEmpty()) {
    		Toast.makeText(getApplicationContext(), "server or devidno is empty", Toast.LENGTH_SHORT).show(); 
    		return false;
    	}
    	
    	SharedPreferences.Editor localEditor = mPreferences.edit();
		localEditor.putString("Server", mServer);
		localEditor.putString("DevIDNO", mDevIdno);
		localEditor.commit();

		mNetClient.SetDirSvr(mServer, mServer, 6605, 0);
		m_Login = true;
		return true;
	}
	
	protected void StartAV() {
		if (!mIsStartAV) {
			
			///直连播放
//			mRealPlay1.setLanInfo(mServer, 6688);
//			mRealPlay2.setLanInfo(mServer, 6688);
			mRealPlay1.setViewInfo(mDevIdno, mDevIdno, 1, "CH2");
	       	mRealPlay2.setViewInfo(mDevIdno, mDevIdno, 2, "CH3");
	      //是否垂直铺满画面 true 是  false否
//	       	mRealPlay1.setVideoBmpExtend(false);
//	       	mRealPlay2.setVideoBmpExtend(false);
//	       	是否水平铺满画面 true 是  false否 备注:一般针对横屏设置
//	       	mRealPlay1.setHorizontalExtend(true);
//	       	mRealPlay1.setHorizontalExtend(true);
			//子码流
//			mRealPlay1.setVideoType(1);
	       	mRealPlay1.StartAV(false, true);
	       	
//	       	mRealPlay1.startRecord();
//			mRealPlay2.setVideoType(1);
	       	mRealPlay2.StartAV(false, true);
	       	mIsStartAV = true;
		}
	}
	
	protected void StopAV() {
//		if (mIsStartAV) {
//			mRealPlay1.StopAV();
//			mRealPlay2.StopAV();
//			mIsStartAV = false;
//		}
		mRealPlay1.StopAV();
		mRealPlay2.StopAV();
		mRealPlay1.stopRecord();
		
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
			} else if (v.equals(mBtnRecord)) {
				if(m_Login){
					Intent intent = new Intent(); 
					String devIdno = mEtDevIdno.getText().toString().trim();
					intent.putExtra("DevIDNO", devIdno);

					intent.setClass(MainActivity.this, RecordActivity.class);
					startActivityForResult(intent, 0);
				}
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
			}else if(v.equals(mBtnDirectSearch)){
				{
					Intent intent = new Intent(); 										
					intent.setClass(MainActivity.this, DeviceSearchActivity.class);
					startActivityForResult(intent, 1);
				}
				StopAV();
			}
		}
	}
	
	final class LoginResponseListener extends AbstractAsyncResponseListener {
		@Override
		protected void onFailure(Throwable e) {
			if (!MainActivity.this.isFinishing()) {
//				hideWaitDialog();
//				ToastUtil.showToast(R.string.login_server_error);
			}
		}

		@Override
		protected void onSuccess(JSONObject jsonObject) {
			if (!MainActivity.this.isFinishing()) {
				int result = -1;							
				try {
					result = jsonObject.getInt("result");
					mSession = jsonObject.getString("JSESSIONID");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(result == 0){
					m_Login = true;
					mNetClient.SetSession(mSession);
					Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
				}

				
			} else {
				//logger.log(Level.INFO,"LoginActivity.LoginResponseListener.onFailure() LoginActivity.this.isFinishing()");
			}
		}
	}
	//创建监听权限的接口对象
	PermissionUtils.IPermissionsResult permissionsResult = new PermissionUtils.IPermissionsResult(){

		//@Override
		public void passPermissons() {
			// TODO Auto-generated method stub
			//Toast.makeText(getActivity(), R.string.toast_permission_go_through, Toast.LENGTH_SHORT).show();
		}

		//@Override
		public void forbitPermissons() {
			// TODO Auto-generated method stub
			//Toast.makeText(LoginActivity.this, R.string.toast_permission_no_through, Toast.LENGTH_SHORT).show();
		}


	};

}
