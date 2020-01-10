package net.babelstar.cmsv6demo.model.bd808;


public class StandardDeviceInfo {
//	private Integer id;
	//设备编号
	private String devIDNO;
	//通道属性,用,分隔 如：1,2表示通道1,通道2
//	private String chnAttr;
	//表示车辆外设参数属性，用位表示，每位表示一种外设, 第一位为支持视频,第二位为油路控制,第三位为电路控制,第四位为tts语音,
	//第五位为数字对讲,第六位为支持抓拍,第七位为支持监听,第八位为油量传感器 第10位客流统计  第14位水位传感器
	private Integer module;
	//主设备  1主设备
//	private Integer mainDev;
	//局域网IP和端口
	private String lanIp;
	private Integer lanPort;
	private Integer tempCount;	//温度传感器数目
	private String tempName;	//温度传感器名称

	//设备状态
	private DeviceStatusInfo status;
	
	public DeviceStatusInfo getStatus() {
		return status;
	}
	public void setStatus(DeviceStatusInfo status) {
		this.status = status;
	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	public String getDevIDNO() {
		return devIDNO;
	}
	public void setDevIDNO(String devIDNO) {
		this.devIDNO = devIDNO;
	}
//	public String getChnAttr() {
//		return chnAttr;
//	}
//	public void setChnAttr(String chnAttr) {
//		this.chnAttr = chnAttr;
//	}
	public Integer getModule() {
		return module;
	}
	public void setModule(Integer module) {
		this.module = module;
	}
//	public Integer getMainDev() {
//		return mainDev;
//	}
//	public void setMainDev(Integer mainDev) {
//		this.mainDev = mainDev;
//	}
	
	public String getLanIp() {
		return lanIp;
	}
	public void setLanIp(String lanIp) {
		this.lanIp = lanIp;
	}
	public Integer getLanPort() {
		return lanPort;
	}
	public void setLanPort(Integer lanPort) {
		this.lanPort = lanPort;
	}
	
	public Integer getTempCount() {
		return tempCount;
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
	
	public boolean isOnline() {
		if (status != null) {
			return (status.getOnline() != null && status.getOnline().intValue() > 0) ? true : false;
		} else {
			return false;
		}
	}
	
	public boolean isGpsValid() {
		if (status != null) {
			return status.isGpsValid();
		} else {
			return false;
		}
	}
	
	/*
	 * 用于模糊查询
	 */
	public boolean matchKey(String find) {
		if (devIDNO.indexOf(find) != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * 是否允许视频
	 */
	public boolean enableVideo() {
		return (module.intValue()&0x1) > 0 ? true : false;
	}
	
	/*
	 * 是否允许油量
	 */
	public boolean enableOil() {
		return (((module.intValue())>>7)&0x1) > 0 ? true : false;
	}
	
	/*
	 * 是否允许胎压
	 */
	public boolean enableTpms() {
		return (((module.intValue())>>11)&0x1) > 0 ? true : false;
	}
	
	/*
	 * 是否允许水位传感器
	 */
	public boolean enableWaterSensor() {
		return (((module.intValue())>>14)&0x1) > 0 ? true : false;
	}
}
