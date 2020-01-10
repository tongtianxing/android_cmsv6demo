package net.babelstar.cmsv6demo.model.bd808;

import java.util.ArrayList;
import java.util.List;




public class VehicleInfo {
	private Integer id;
	//vehiIDNO和vehiName主要为了兼容cmsv6平台和部标平台
	//部标平台 vehiIDNO 和 vehiName是一样的，都是车牌号
	//cmsv6平台 vehiIDNO 为设备编号 , vehiName为设备名称
	//车牌号
	private String vehiIDNO;
	//车辆名称 
	private String vehiName;
	//所属公司
	private Integer companyId;
	//车牌类型  0为其他,1为黄牌,2为蓝牌,3为黑牌,4为白牌'
	private Integer plateType;
	//车辆颜色
	private String vehiColor;
	//图标类型，区分出货车或者客运车
	private Integer icon;
	//通道数目
	private Integer chnCount;
	//通道名称 以,分割
	private String chnName;
	//设备类型
	private Integer devType;
	//温度传感器数目
	private Integer tempCount;
	//温度传感器名称 以,分割
	private String tempName;
	//车辆类型			4表示人员类型
	private String vehiType;
	//电话
	private String phone;
	//警员姓名
	private String pname;
	//设备
	private List<StandardDeviceInfo> lstDevice;
	//设备地址
	private String address;
	
	//司机名称
	private String driverName;
	//司机电话
	private String driverPhone;
	
	private boolean devAdt;
	//车辆使用状态,0为正常,1为维修,2为停用,3为欠费
	private Integer status;
	
	//车辆设备厂家类型 3 1078设备
	private Integer factoryDevType;
//	//接收推送报警个数
//	private	boolean pushAlarmAttachFile;
//	//接收推送报警类型
//	private String armTypeStr;
		//终端id
	private Integer terminalId;
	
//	public boolean isPushAlarmAttachFile() {
//		return pushAlarmAttachFile;
//	}
//	public void setPushAlarmAttachFile(boolean pushAlarmAttachFile) {
//		this.pushAlarmAttachFile = pushAlarmAttachFile;
//	}
	
	//io数目
	private Integer ioCount;
	//io名称 以,分割
	private String ioName;
		
//	//接收推送报警时间
//	private String armTime;	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVehiIDNO() {
		return vehiIDNO;
	}
	public void setVehiIDNO(String vehiIDNO) {
		this.vehiIDNO = vehiIDNO;
	}
	public String getVehiName() {
		return vehiName;
	}
	public void setVehiName(String vehiName) {
		this.vehiName = vehiName;
	}
	public String getVehiType() {
		return vehiType;
	}
	public void setVehiType(String vehiType) {
		this.vehiType = vehiType;
	}
	public List<StandardDeviceInfo> getLstDevice() {
		return lstDevice;
	}
	public void setLstDevice(List<StandardDeviceInfo> lstDevice) {
		this.lstDevice = lstDevice;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getPlateType() {
		return plateType;
	}
	public void setPlateType(Integer plateType) {
		this.plateType = plateType;
	}
	public String getVehiColor() {
		return vehiColor;
	}
	public void setVehiColor(String vehiColor) {
		this.vehiColor = vehiColor;
	}
	public Integer getIcon() {
		return icon;
	}
	public void setIcon(Integer icon) {
		this.icon = icon;
	}
	public Integer getChnCount() {
		return chnCount;
	}
	public void setChnCount(Integer chnCount) {
		this.chnCount = chnCount;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public Integer getDevType() {
		return devType;
	}
	public void setDevType(Integer devType) {
		this.devType = devType;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getTempCount() {
		return tempCount;
	}
	public Integer getIoCount() {
		return ioCount;
	}
	public void setIoCount(Integer ioCount) {
		this.ioCount = ioCount;
	}
	public String getIoName() {
		return ioName;
	}
	public void setIoName(String ioName) {
		this.ioName = ioName;
	}
	public void setTempCount(Integer tempCount) {
		this.tempCount = tempCount;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public Integer getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Integer terminalid) {
		this.terminalId = terminalid;
	}
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	
	public Integer getStatusVel() {
		return status;
	}
	public void setStatusVel(Integer status) {
		this.status = status;
	}
	
	public int getDeviceSize() {
		if (lstDevice != null) {
			return lstDevice.size();
		} else {
			return 0;
		}
	}
	
	public boolean getDevAdt() {
		return devAdt;
	}
	public void setDevAdt(boolean devadt) {				
		this.devAdt = devadt;
	}
	
	public Integer getFactoryDevType() {
		return factoryDevType;
	}
	public void setFactoryDevType(Integer factorydevType) {
		this.factoryDevType = factorydevType;
	}
	
//	public Integer getPushAlarmNum() {
//		return pushAlarmNum;
//	}
//	public void setPushAlarmNum() {			
//		this.pushAlarmNum ++;
//	}
	
//	public String getArmTypeStr() {
//		return armTypeStr;
//	}
//	public void setArmTypeStr(String armTypeStr) {
//		this.armTypeStr = armTypeStr;
//	}
	
//	public String getArmTime() {
//		return armTime;
//	}
//	public void setArmTime(String armTime) {
//		this.armTime = armTime;
//	}
		
	public void	setDeviceStatus(DeviceStatusInfo status) {				
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.getDevIDNO().equals(status.getDevIdno())) {
				device.setStatus(status);
				break;
			}
		}
	}
	
	/*
	 * 优先获取gps有效的状态
	 */
	public DeviceStatusInfo getStatus() {
		int size = getDeviceSize();
		if (size == 0) {
			return null;
		} else if (size == 1) {
			return lstDevice.get(0).getStatus();
		} else {
			//如果有两个状态，则需要将两个状态合并
			boolean initGps = false;
			DeviceStatusInfo newStatus = new DeviceStatusInfo();
			for (int i = 0; i < size; ++ i) {
				StandardDeviceInfo device = lstDevice.get(i);
				DeviceStatusInfo status = device.getStatus();
				//如果有一个在线的，则认为是在线
				if (device.isOnline()) {
					newStatus.setOnline(1);
				}
				
				if (!initGps && device.isGpsValid()) {
					newStatus.setDevIdno(status.getDevIdno());
					newStatus.setGpsTime(status.getGpsTime());
					newStatus.setJingDu(status.getJingDu());
					newStatus.setWeiDu(status.getWeiDu());
					newStatus.setMapJingDu(status.getMapJingDu());
					newStatus.setMapWeiDu(status.getMapWeiDu());
					newStatus.setHuangXiang(status.getHuangXiang());
					newStatus.setLiCheng(status.getLiCheng());
					newStatus.setStatus1(status.getStatus1());
					newStatus.setStatus2(status.getStatus2());
					newStatus.setStatus3(status.getStatus3());
					newStatus.setStatus4(status.getStatus4());
					newStatus.setTempSensor1(status.getTempSensor1());
					newStatus.setTempSensor2(status.getTempSensor2());
					newStatus.setTempSensor3(status.getTempSensor3());
					newStatus.setTempSensor4(status.getTempSensor4());
					newStatus.setParkTime(status.getParkTime());
					newStatus.setSpeed(status.getSpeed());
					newStatus.setYouLiang(status.getYouLiang());
					initGps = true;
				}
			}
			return newStatus;
		}
	}
	
	/*
	 * 获取所有的设备状态
	 */
	public List<DeviceStatusInfo> getAllStatus() {
		int size = getDeviceSize();
		if (size > 0) {
			List<DeviceStatusInfo> status = new ArrayList<DeviceStatusInfo>();
			for (int i = 0; i < size; ++ i) {
				StandardDeviceInfo device = lstDevice.get(i);
				status.add(device.getStatus());
			}
			return status;
		}
		
		return null;
	}
	
	/*
	 * 加入设备信息
	 */
	public void addDeviceInfo(StandardDeviceInfo device) {
		if (lstDevice == null) {
			lstDevice = new ArrayList<StandardDeviceInfo>();
		}
		lstDevice.add(device);
	}
	
	/*
	 * 判断是否存在此设备
	 */
	public boolean hasDevice(String devIdno) {
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.getDevIDNO().equalsIgnoreCase(devIdno)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 查找设备
	 */
	public StandardDeviceInfo findDevice(String devIdno) {
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.getDevIDNO().equalsIgnoreCase(devIdno)) {
				return device;
			}
		}
		return null;
	}
	
	/*
	 * 判断是否在线
	 */
	public boolean isOnline() {
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.isOnline()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 判断GPS是否有效
	 */
	/*
	 * 判断是否在线
	 */
	public boolean isGpsValid() {
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.isGpsValid()) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 用于模糊查询
	 */
	public boolean matchKey(String find) {
		if (getVehiName().indexOf(find) != -1
			|| getVehiIDNO().indexOf(find) != -1) {
			return true;
		}
				
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.matchKey(find)) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 获取所有设备编号
	 */
	public String getDevIdnos() {
		String idnos = "";
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			idnos += device.getDevIDNO();
			if (i != (size - 1)) {
				idnos += ",";
			}
		}
		return idnos;
	}
	
	/*
	 * 获取视频的设备编号
	 */
	public String getVideoDevIdno() {
		String idnos = "";
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableVideo()) {
				idnos = device.getDevIDNO();
				break;
			}
		}
		return idnos;
	}
		
	/*
	 * 获取局域网IP
	 */
	public String getVideoLanIp() {
		String lanIp = "";
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableVideo()) {
				lanIp = device.getLanIp();
				break;
			}
		}
		return lanIp;
	}
	
	/*
	 * 获取局域网端口
	 */
	public Integer getVideoLanPort() {
		Integer lanPort = 0;
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableVideo()) {
				lanPort = device.getLanPort();
				break;
			}
		}
		return lanPort;
	}
	
	/*
	 * 获取通道名称
	 */
	public String getChannelName(String devIdno, int channel) {
		//StandardDeviceInfo device = findDevice(devIdno);
		//不考虑同时有两个视频设备的情况
		String[] chn = chnName.split(",");
		if (channel < chn.length) {
			return chn[channel];
		} else {
			return "CH" + (channel + 1);
		}
	}
	
	/*
	 * 获取通道名称
	 */
	public String getChannelName(int channel) {
		if (chnName == null) {
			return "CH" + (channel + 1);
		} else {
			//不考虑同时有两个视频设备的情况
			String[] chn = chnName.split(",");
			if (channel < chn.length) {
				return chn[channel];
			} else {
				return "CH" + (channel + 1);
			}
		}
	}

	/*
	 * 
	 */
	public String getTemperatureName(int index) {
		if (tempName == null) {
			return "TEMP" + (index + 1);
		} else {
			//不考虑同时有两个视频设备的情况
			String[] chn = tempName.split(",");
			if (index < chn.length) {
				return chn[index];
			} else {
				return "TEMP" + (index + 1);
			}
		}
	}
	
	/*
	 * 获取油量
	 */
	public boolean hasOilSensor() {
		boolean ret = false;
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableOil()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	/*
	 * 是否有胎压属性
	 */
	public boolean hasTpms() {
		boolean ret = false;
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableTpms()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	/*
	 * 判断是否为人员类型
	 */
	public boolean isPerson() {
		return (this.vehiType != null && this.vehiType.equals("4") || this.devType == 2) ? true : false;
	}
	
	/*
	 * 是否有水位传感
	 */
	public boolean hasWaterSensor() {
		boolean ret = false;
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableWaterSensor()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	/*
	 * 是否视频设备
	 */
	public boolean isVideoDev() {
		boolean ret = false;
		int size = getDeviceSize();
		for (int i = 0; i < size; ++ i) {
			StandardDeviceInfo device = lstDevice.get(i);
			if (device.enableVideo()) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
