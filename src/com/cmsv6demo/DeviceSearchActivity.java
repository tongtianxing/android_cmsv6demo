package com.cmsv6demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.babelstar.cmsv6demo.adapter.DeviceSearchAdapter;
import net.babelstar.cmsv6demo.adapter.DeviceSearchAdapter.SearchItemClick;
import net.babelstar.cmsv6demo.model.bd808.DeviceSearch;
import net.babelstar.cmsv6demo.model.bd808.DeviceStatusInfo;
import net.babelstar.cmsv6demo.model.bd808.StandardDeviceInfo;
import net.babelstar.cmsv6demo.model.bd808.VehicleInfo;
import net.babelstar.cmsv6demo.model.bd808.VehicleTeam;
import net.babelstar.common.view.WaitDialog;
import net.babelstar.common.view.WaitDialog.WaitCancelListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.babelstar.gviewer.NetClient;
//import com.google.code.microlog4android.config.PropertyConfigurator;

public class DeviceSearchActivity extends Activity implements SearchItemClick, WaitCancelListener {
	private static final int MESSAGE_WIFI_FAILED = 1;
	private static final int MESSAGE_WIFI_SUC = 2;
	private ImageView mIvFlash;
	
	protected RelativeLayout mListLayoutNull;
	protected ImageView mListIvNullData;
	protected TextView mListTvNullData;
	
	protected RelativeLayout mListLayoutError;
	protected ImageView mListIvError;
	protected TextView mListTvError;
	protected ReflashClickListener mReflashListener;
	protected ImageView mListIvReflash;
	protected TextView mListTvReflash;
	
	protected ProgressBar mWaittingBar;
	protected TextView mListTvLoadTip;
	protected ListView mListView;
	private WaitDialog mWaitDialog;
	private Handler mMyHandler = new Handler();
	
	private DeviceSearchAdapter mAdapter;
	private long mSearchHandle = 0;
	private SearchRunnable mSearchRunnable = new SearchRunnable();
	private List<DeviceSearch> mSearchList = new ArrayList<DeviceSearch>();
	private DeviceSearch mSelectDevice;

	private List<VehicleInfo> lstVehicle = new ArrayList<VehicleInfo>();
	private List<String> lstDevIdnos = new ArrayList<String>();
	private Map<String, VehicleInfo> mapVehiInfoWithVehiIdno = new HashMap<String, VehicleInfo>();
	private Map<String, VehicleInfo> mapVehiInfoWithDevIdno = new HashMap<String, VehicleInfo>();
	private Map<Integer, VehicleTeam> mapVehiTeam = new HashMap<Integer, VehicleTeam>();
	public Activity getActivity() {
		return DeviceSearchActivity.this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devsearch_list);		
		
//		gViewerApp = (GViewerApp)getActivity().getApplication();
		
	    //PropertyConfigurator.getConfigurator(getActivity()).configure();
//	    gViewerApp = (GViewerApp)getActivity().getApplication();
	    
		initListView(getActivity());
		
		mAdapter = new DeviceSearchAdapter(DeviceSearchActivity.this, DeviceSearchActivity.this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
				onSearchItemClick(position);
			}
		});
		mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				onSearchItemClick(position);
				return true;
			}
		});
		mListView.setCacheColorHint(Color.TRANSPARENT);
        mListView.setAlwaysDrawnWithCacheEnabled(true);
        
        mIvFlash = (ImageView)findViewById(R.id.devsearch_flash);
        mIvFlash.setOnClickListener(mReflashListener);
		
        TitleTouchListener mTitleTouchListener = new TitleTouchListener();
        mIvFlash.setOnTouchListener(mTitleTouchListener);
		
		startSearch();
		onListBeginLoad("搜索中, 请等待");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopSearch();
		if(lstVehicle != null){
			lstVehicle.clear();
		}
		if(lstDevIdnos != null){
			lstDevIdnos.clear();
		}

		if(mapVehiInfoWithVehiIdno != null){
			mapVehiInfoWithVehiIdno.clear();
		}
		if(mapVehiInfoWithDevIdno != null){
			mapVehiInfoWithDevIdno.clear();
		}
		if(mapVehiTeam != null){
			mapVehiTeam.clear();
		}
	}
	
	final class TitleTouchListener implements OnTouchListener{
	    @Override
	    public boolean onTouch(View view, MotionEvent event) {
			if(event.getAction()==MotionEvent.ACTION_DOWN) {
				view.setBackgroundColor(Color.argb(255,119,197,225)); //0xFF77C5E1
			} else if(event.getAction()==MotionEvent.ACTION_UP){
				view.setBackgroundColor(Color.TRANSPARENT);
			}
	        return false;
	    }
	}
	
	/*
	 * 初始化List
	 */
	protected void initListView(Activity view) {
		mWaittingBar = (ProgressBar) view.findViewById(R.id.lyListContent_waitting);
		mListTvLoadTip = (TextView) view.findViewById(R.id.lyListContent_tvLoadTip);
		mListView = (ListView) view.findViewById(R.id.lyListContent_listView);
		
		mListLayoutNull = (RelativeLayout) view.findViewById(R.id.lyListContent_lyNull);
		mListIvNullData = (ImageView) view.findViewById(R.id.lyListContent_ivNull);
		mListTvNullData = (TextView) view.findViewById(R.id.lyListContent_tvNull);
		
		mListLayoutError = (RelativeLayout) view.findViewById(R.id.lyListContent_lyError);
		mListIvError = (ImageView) view.findViewById(R.id.lyListContent_ivError);
		mListTvError = (TextView) view.findViewById(R.id.lyListContent_tvError);
		mListIvReflash = (ImageView) view.findViewById(R.id.lyListContent_ivReflash);
		mListTvReflash = (TextView) view.findViewById(R.id.lyListContent_tvReflash);
		mReflashListener = new ReflashClickListener();
		mListIvReflash.setOnClickListener(mReflashListener);
		mListTvReflash.setOnClickListener(mReflashListener);
	}
	
	/*
	 * 开始加载
	 */
	@SuppressLint("NewApi")
	protected void onListBeginLoad(String loadTip) {
		if (loadTip.isEmpty()) {
			mListTvLoadTip.setVisibility(View.GONE);
		} else {
			mListTvLoadTip.setVisibility(View.VISIBLE);
			mListTvLoadTip.setText(loadTip);
		}
		mWaittingBar.setVisibility(View.VISIBLE);
		mListLayoutError.setVisibility(View.GONE);
		mListLayoutNull.setVisibility(View.GONE);
		mListView.setVisibility(View.GONE);
	}
	
	/*
	 * 设置列表为空时显示字样
	 */
	protected void setListNullTip(String str) {
		mListTvNullData.setText(str);
	}
	
	/*
	 * 设置列表为空时显示字样
	 */
	protected void setListNullTip(int resId) {
		mListTvNullData.setText(resId);
	}
	
	protected void hideListWaittingBar() {
		mWaittingBar.setVisibility(View.GONE);
	}
	
	protected void onListLoadFailed() {
		hideListWaittingBar();
		mListLayoutError.setVisibility(View.VISIBLE);
		mListLayoutNull.setVisibility(View.GONE);
		mListView.setVisibility(View.GONE);
		mListTvLoadTip.setVisibility(View.GONE);
	}

	protected void onListLoadSuccess(boolean isEmpty) {
		hideListWaittingBar();
		mListTvLoadTip.setVisibility(View.GONE);
		mListLayoutError.setVisibility(View.GONE);
		if (!isEmpty) {
			mListLayoutNull.setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
		} else {
			mListLayoutNull.setVisibility(View.VISIBLE);
			mListView.setVisibility(View.GONE);
		}
		mAdapter.setData(mSearchList);
		mAdapter.notifyDataSetChanged();
	}
	
	protected void showLoading() {
		if (mWaitDialog == null) {
			mWaitDialog = new WaitDialog(getActivity(), this);
		}
		mWaitDialog.show();
	}
	
	protected void hideWaitDialog() {
		if (mWaitDialog != null && mWaitDialog.isShowing()) {
			mWaitDialog.dismiss();
		}
	}
	
	/*
	 * 返回true表示不需要关闭，返回false表示需要关闭
	 */
	public boolean onWaitCancel() {
		cancelSearch();
		return false;
	}
	
	protected void cancelSearch() {
		stopSearch();
		hideWaitDialog();
	}
	
	final class ReflashClickListener implements OnClickListener {
		public void onClick(View v) {
			stopSearch();
			startSearch();
			onListBeginLoad("搜索中, 请等待");
		}
	}
	
	/*
	 * 返回获取到的大小
	 */
	public void onSearchItemClick(int position) {
		
		mSelectDevice = mSearchList.get(position);
		previewDeviceVideo();
	}
	
	protected void startSearch() {
		if (0 == mSearchHandle) {
			mSearchHandle = NetClient.SDOpenSearch();
			mSearchList.clear();
			NetClient.SDStartSearch(mSearchHandle, "", NetClient.SEARCH_DEFAULT_PORT);
			mMyHandler.postDelayed(mSearchRunnable, 2000);
		}
	}
	
	protected void stopSearch() {
		if (0 != mSearchHandle) {
			NetClient.SDStopSearch(mSearchHandle);
			NetClient.SDCloseSearch(mSearchHandle);
			mMyHandler.removeCallbacks(mSearchRunnable);
			mSearchHandle = 0;

		}
	}
	
	final class SearchRunnable implements Runnable {
		public void run() {
			boolean isFinished = false;
			if (0 != mSearchHandle) {
				while (true) {
					byte[] result = new byte[1024];
					java.util.Arrays.fill(result, (byte)0);
					int ret = NetClient.SDGetSearchResult(mSearchHandle, result, 1024);
					if (ret == NetClient.NET_SUCCESS) {
						int i = 0;
						for (i = 0; i < result.length; ++ i) {
							if (result[i] == 0) {
								break;
							}
						}
						byte[] temp = new byte[i];
						System.arraycopy(result, 0, temp, 0, i);
						//szDevInfo为DevIDNO:NetType(0=3G, 1=Wifi, 2=Net):NetName:IP:DevType:chn
						String devInfo = new String(temp);
						devInfo += " ";
						String[] info = devInfo.split(":");
						
						DeviceSearch search = new DeviceSearch();
						search.setSearchType(DeviceSearch.SEARCH_TYPE_NET);
						search.setDevIdno(info[0]);
						search.setNetType(Integer.parseInt(info[1]));
						search.setNetName(info[2]);
						search.setIpAddr(info[3]);
						search.setDevType(Integer.parseInt(info[4]));
						search.setChannel(Integer.parseInt(info[5]));
						search.setWebPort(Integer.parseInt(info[6]));
						if (search.getWebPort() == 0) {
							search.setWebPort(80);
						}
						search.setWebUrl(info[7].trim());
						if (info.length > 8 && info[8].trim().length() > 0) {
							search.setChnName(info[8].trim());
						}
						mSearchList.add(search);
						continue;
					}
					else if (ret == NetClient.SEARCH_FINISHED) {
						isFinished = true;
						if (mSearchList.size() > 0) {
							onListLoadSuccess(false);
						} else {
							setListNullTip("搜索不到设备");
							onListLoadSuccess(true);
						}
						updateVehicleInfo();
					} 
					break;
				}
			}
			
			if (!isFinished) {
				mMyHandler.postDelayed(mSearchRunnable, 100);
			}
		}
	}
	
	/*
	 * 刷新车辆数据
	 */
	protected void updateVehicleInfo() {
		int i = 0;

		VehicleTeam team = new VehicleTeam();
		team.setCompanyId(0);
		team.setLevel(1);
		team.setName("All");
		team.setId(1);
		team.setParentId(0);
		mapVehiTeam.put(team.getId(), team);
		

		for (i = 0; i < mSearchList.size(); ++ i) {
			DeviceSearch device = mSearchList.get(i);
			VehicleInfo vehicleInfo = new VehicleInfo();
			vehicleInfo.setDevType(1);
			vehicleInfo.setVehiIDNO(device.getDevIdno());
			vehicleInfo.setVehiName(device.getDevIdno());
			vehicleInfo.setCompanyId(0);
			vehicleInfo.setIcon(1);
			vehicleInfo.setChnCount(device.getChannel());
			if (device.getChnName() != null) {
				vehicleInfo.setChnName(device.getChnName().replace(';', ','));
			}
			
			StandardDeviceInfo deviceInfo = new StandardDeviceInfo();
			deviceInfo.setDevIDNO(device.getDevIdno());
			deviceInfo.setModule(255);
			deviceInfo.setLanIp(device.getIpAddr());
			deviceInfo.setLanPort(6688);
			lstDevIdnos.add(deviceInfo.getDevIDNO());
			
			DeviceStatusInfo status = new DeviceStatusInfo();
			status.setDevIdno(device.getDevIdno());
			status.setOnline(1);
			deviceInfo.setStatus(status);
			List<StandardDeviceInfo> lstDevice = new ArrayList<StandardDeviceInfo>();
			lstDevice.add(deviceInfo);
			vehicleInfo.setLstDevice(lstDevice);
			mapVehiInfoWithDevIdno.put(deviceInfo.getDevIDNO(), vehicleInfo);
			mapVehiInfoWithVehiIdno.put(vehicleInfo.getVehiIDNO(), vehicleInfo);
			lstVehicle.add(vehicleInfo);
		}
		
		String[] devIndos = new String[lstDevIdnos.size()];
		for (i = 0; i < lstDevIdnos.size(); ++ i) {
			devIndos[i] = lstDevIdnos.get(i);
		}
		/*gViewerApp.setMapVehiTeam(mapVehiTeam);
		gViewerApp.setMapVehiInfoWithVehiIdno(mapVehiInfoWithVehiIdno);
		gViewerApp.setMapVehiInfoWithDevIdno(mapVehiInfoWithDevIdno);
		gViewerApp.setVehiList(lstVehicle);
		gViewerApp.setHasVehicle(true);
		gViewerApp.setDevIdnos(devIndos);*/
	}
	

	
	class DeviceMenuItemClickListener implements CustomMenuDialog.OnListener
	{
		public void onItemClick(int position, int index) {
			switch(position) {
			case 0:	//实时视频
				{
					previewDeviceVideo();
				}
				break;
			case 1:	//参数设置
				{
					//deviceParamSetting();
				}
				break;
			default :
				break;
			}
		}
	}
	
	protected void previewDeviceVideo() {
		/*Intent localIntent = new Intent();
		gViewerApp.setDirectMode(true);
		gViewerApp.setDirectDevIdno(mSelectDevice.getDevIdno());
		gViewerApp.setDirectUrl("http://" + mSelectDevice.getIpAddr() + ":" + mSelectDevice.getWebPort() + "/" + mSelectDevice.getWebUrl());
		localIntent.setClass(DeviceSearchActivity.this, MainActivity.class);
		startActivityForResult(localIntent, 1);*/
		VehicleInfo vehicleInfo = mapVehiInfoWithDevIdno.get(mSelectDevice.getDevIdno());
		if(vehicleInfo != null){
			Intent localIntent = new Intent();
			localIntent.putExtra("serverIp", vehicleInfo.getVideoLanIp());
			localIntent.putExtra("port", vehicleInfo.getVideoLanPort());
			localIntent.putExtra("devIdno", mSelectDevice.getDevIdno());
			localIntent.setClass(DeviceSearchActivity.this, MainDirectActivity.class);
			startActivityForResult(localIntent, 2);
		}

	}
	
	/*protected void deviceParamSetting() {
		Intent localIntent = new Intent();
		localIntent.putExtra("title", getString(R.string.device_setting) + " - " + mSelectDevice.getDevIdno());
		localIntent.putExtra("url", "http://" + mSelectDevice.getIpAddr() + ":" + mSelectDevice.getWebPort() + "/" + mSelectDevice.getWebUrl());
		localIntent.setClass(DeviceSearchActivity.this, WebViewActivity.class);
		startActivityForResult(localIntent, 1);
	}*/
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	}
}
