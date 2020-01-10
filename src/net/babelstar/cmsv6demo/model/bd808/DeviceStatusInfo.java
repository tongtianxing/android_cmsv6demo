package net.babelstar.cmsv6demo.model.bd808;

import java.util.Date;

import net.babelstar.common.util.DateUtil;

public class DeviceStatusInfo {
	//标识属性
	private Integer id;
	//设备编号
	private String devIdno;
	//在线状态
	private Integer online;
	//状态
	private Integer status1;
	//状态
	private Integer status2;
	//状态
	private Integer status3;
	//状态
	private Integer status4;
	//温度传感器
	private Integer tempSensor1;
	private Integer tempSensor2;
	private Integer tempSensor3;
	private Integer tempSensor4;
	//速度
	private Integer speed;
	//方向
	private Integer huangXiang;
	//经度
	private Integer jingDu;
	//纬度
	private Integer weiDu;
	//高度
	private Integer gaoDu;
	//停车时长
	private Integer parkTime;
	//油量
	private Integer youLiang;
	//里程
	private Integer liCheng;
	//GPS时间
	private String gpsTime;
	//经度	地图上使用的
	private String mapJingDu;
	//纬度
	private String mapWeiDu;
	//厂家和协议类型
	private Integer protocol;
	private Integer factoryType;
	private boolean isMaturity;
	
	//附加信息标志位:3 苏标状态解析
	private Integer extraFlag;
	
	//苏标状态
	private Integer sensor1;
	private Integer sensor2;
	private Integer sensor3;
	private Integer sensor4;
	private Integer sensor5;
	private Integer sensor6;
	private Integer sensor7;
	private Integer sensor8;
	private Integer sensor9;
	private Integer sensor10;
	//判断是否视频部标类型 3
	private Integer factoryDevType;
	
		
	private Integer status;//车辆使用状态,0为正常,1为维修,2为停用,3为欠费
	//新增状态解析
	private Integer lGps;//为2时表示长GPS    2时有效
	private String  recvGps;//接收GPS的服务器时间
	private Integer longGps;//长GPS的状态位
	private Integer carTemp;//车厢温度(0x06)
	private Integer ioStatus;//IO状态位(0x2A)
	private Integer expandStatus;//扩展车辆信号状态位(0x25)
	private Integer analogQuantity;//模拟量(0x2B)
	private Integer adas1;//主动安全adas报警状态位Lv1
	private Integer adas2;//主动安全adas报警状态位Lv2
	private Integer dsm1;//主动安全dsm报警状态位Lv1
	private Integer dsm2;//主动安全dsm报警状态位Lv2
	private Integer bsd1;//主动安全bsd报警状态位Lv1
	private Integer bsd2;//主动安全bsd报警状态位Lv2
	private Integer frontSpeed;//前车车速
	private Integer frontDistance;//前车/行人距离
	private Integer roadSign;//道路标志识别数据
	private Integer discernType;//识别类型
	private Integer roadSignType;//道路标志识别类型'
	private Integer fatigueLevel;//疲劳程度
	private Integer yawnNum;//打哈欠次数
	private Integer closedEyesTime;//闭眼持续时长
	private Integer continuousBlik;//连续眨眼次数
	private String tirePressure;//胎压数据,格式化为hex
	private String geographicLocation;//经纬度的地理位置
	
	public Integer getlGps() {
		return lGps;
	}
	public void setlGps(Integer lGps) {
		this.lGps = lGps;
	}
	public String getRecvGps() {
		return recvGps;
	}
	public void setRecvGps(String recvGps) {
		this.recvGps = recvGps;
	}
	public Integer getLongGps() {
		return longGps;
	}
	public void setLongGps(Integer longGps) {
		this.longGps = longGps;
	}
	public Integer getCarTemp() {
		return carTemp;
	}
	public void setCarTemp(Integer carTemp) {
		this.carTemp = carTemp;
	}
	public Integer getIoStatus() {
		return ioStatus;
	}
	public void setIoStatus(Integer ioStatus) {
		this.ioStatus = ioStatus;
	}
	public Integer getExpandStatus() {
		return expandStatus;
	}
	public void setExpandStatus(Integer expandStatus) {
		this.expandStatus = expandStatus;
	}
	public Integer getAnalogQuantity() {
		return analogQuantity;
	}
	public void setAnalogQuantity(Integer analogQuantity) {
		this.analogQuantity = analogQuantity;
	}
	public Integer getAdas1() {
		return adas1;
	}
	public void setAdas1(Integer adas1) {
		this.adas1 = adas1;
	}
	public Integer getAdas2() {
		return adas2;
	}
	public void setAdas2(Integer adas2) {
		this.adas2 = adas2;
	}
	public Integer getDsm1() {
		return dsm1;
	}
	public void setDsm1(Integer dsm1) {
		this.dsm1 = dsm1;
	}
	public Integer getDsm2() {
		return dsm2;
	}
	public void setDsm2(Integer dsm2) {
		this.dsm2 = dsm2;
	}
	public Integer getBsd1() {
		return bsd1;
	}
	public void setBsd1(Integer bsd1) {
		this.bsd1 = bsd1;
	}
	public Integer getBsd2() {
		return bsd2;
	}
	public void setBsd2(Integer bsd2) {
		this.bsd2 = bsd2;
	}
	public Integer getFrontSpeed() {
		return frontSpeed;
	}
	public void setFrontSpeed(Integer frontSpeed) {
		this.frontSpeed = frontSpeed;
	}
	public Integer getFrontDistance() {
		return frontDistance;
	}
	public void setFrontDistance(Integer frontDistance) {
		this.frontDistance = frontDistance;
	}
	public Integer getRoadSign() {
		return roadSign;
	}
	public void setRoadSign(Integer roadSign) {
		this.roadSign = roadSign;
	}
	public Integer getDiscernType() {
		return discernType;
	}
	public void setDiscernType(Integer discernType) {
		this.discernType = discernType;
	}
	public Integer getRoadSignType() {
		return roadSignType;
	}
	public void setRoadSignType(Integer roadSignType) {
		this.roadSignType = roadSignType;
	}
	public Integer getFatigueLevel() {
		return fatigueLevel;
	}
	public void setFatigueLevel(Integer fatigueLevel) {
		this.fatigueLevel = fatigueLevel;
	}
	public Integer getYawnNum() {
		return yawnNum;
	}
	public void setYawnNum(Integer yawnNum) {
		this.yawnNum = yawnNum;
	}
	public Integer getClosedEyesTime() {
		return closedEyesTime;
	}
	public void setClosedEyesTime(Integer closedEyesTime) {
		this.closedEyesTime = closedEyesTime;
	}
	public Integer getContinuousBlik() {
		return continuousBlik;
	}
	public void setContinuousBlik(Integer continuousBlik) {
		this.continuousBlik = continuousBlik;
	}
	public String getTirePressure() {
		return tirePressure;
	}
	public void setTirePressure(String tirePressure) {
		this.tirePressure = tirePressure;
	}
	public String getGeographicLocation() {
		return geographicLocation;
	}
	public void setGeographicLocation(String geographicLocation) {
		this.geographicLocation = geographicLocation;
	}
	public void setMaturity(boolean isMaturity) {
		this.isMaturity = isMaturity;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDevIdno() {
		return devIdno;
	}
	public void setDevIdno(String devIdno) {
		this.devIdno = devIdno;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus1() {
		return status1;
	}
	public void setStatus1(Integer status1) {
		this.status1 = status1;
	}
	public Integer getStatus2() {
		return status2;
	}
	public void setStatus2(Integer status2) {
		this.status2 = status2;
	}
	public Integer getStatus3() {
		return status3;
	}
	public void setStatus3(Integer status3) {
		this.status3 = status3;
	}
	public Integer getStatus4() {
		return status4;
	}
	public void setStatus4(Integer status4) {
		this.status4 = status4;
	}
	public Integer getTempSensor1() {
		return tempSensor1;
	}
	public void setTempSensor1(Integer tempSensor1) {
		this.tempSensor1 = tempSensor1;
	}
	public Integer getTempSensor2() {
		return tempSensor2;
	}
	public void setTempSensor2(Integer tempSensor2) {
		this.tempSensor2 = tempSensor2;
	}
	public Integer getTempSensor3() {
		return tempSensor3;
	}
	public void setTempSensor3(Integer tempSensor3) {
		this.tempSensor3 = tempSensor3;
	}
	public Integer getTempSensor4() {
		return tempSensor4;
	}
	public void setTempSensor4(Integer tempSensor4) {
		this.tempSensor4 = tempSensor4;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getHuangXiang() {
		return huangXiang;
	}
	public void setHuangXiang(Integer huangXiang) {
		this.huangXiang = huangXiang;
	}
	public Integer getJingDu() {
		return jingDu;
	}
	public void setJingDu(Integer jingDu) {
		this.jingDu = jingDu;
	}
	public Integer getWeiDu() {
		return weiDu;
	}
	public void setWeiDu(Integer weiDu) {
		this.weiDu = weiDu;
	}
	public Integer getGaoDu() {
		return gaoDu;
	}
	public void setGaoDu(Integer gaoDu) {
		this.gaoDu = gaoDu;
	}
	public Integer getParkTime() {
		return parkTime;
	}
	public void setParkTime(Integer parkTime) {
		this.parkTime = parkTime;
	}
	public Integer getLiCheng() {
		return liCheng;
	}
	public void setLiCheng(Integer liCheng) {
		this.liCheng = liCheng;
	}
	public String getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}
	
	public String getMapJingDu() {
		return mapJingDu;
	}
	public void setMapJingDu(String mapJingDu) {
		this.mapJingDu = mapJingDu;
	}
	public String getMapWeiDu() {
		return mapWeiDu;
	}
	public void setMapWeiDu(String mapWeiDu) {
		this.mapWeiDu = mapWeiDu;
	}
	
	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public Integer getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(Integer factoryType) {
		this.factoryType = factoryType;
	}
	
	public Integer getYouLiang() {
		return youLiang;
	}
	public void setYouLiang(Integer youLiang) {
		this.youLiang = youLiang;
	}
	
	public boolean getIsMaturity() {
		return isMaturity;
	}
	public void setIsMaturity(boolean isMaturity) {
		this.isMaturity = isMaturity;
	}
	
	public Integer getExtraFlag() {
		return extraFlag;
	}
	public void setExtraFlag(Integer extraflag) {
		this.extraFlag = extraflag;
	}
	
	public Integer getSensor1() {
		return sensor1;
	}
	public void setSensor1(Integer sensor1) {
		this.sensor1 = sensor1;
	}
	public Integer getSensor2() {
		return sensor2;
	}
	public void setSensor2(Integer sensor2) {
		this.sensor2 = sensor2;
	}
	public Integer getSensor3() {
		return sensor3;
	}
	public void setSensor3(Integer sensor3) {
		this.sensor3 = sensor3;
	}
	
	public Integer getSensor4() {
		return sensor4;
	}
	public void setSensor4(Integer sensor4) {
		this.sensor4 = sensor4;
	}
	
	public Integer getSensor5() {
		return sensor5;
	}
	public void setSensor5(Integer sensor5) {
		this.sensor5 = sensor5;
	}
	
	
	public Integer getSensor6() {
		return sensor6;
	}
	public void setSensor6(Integer sensor6) {
		this.sensor6 = sensor6;
	}
	
	public Integer getSensor7() {
		return sensor7;
	}
	public void setSensor7(Integer sensor7) {
		this.sensor7 = sensor7;
	}
	public Integer getSensor8() {
		return sensor8;
	}
	public void setSensor8(Integer sensor8) {
		this.sensor8 = sensor8;
	}
	public Integer getSensor9() {
		return sensor9;
	}
	public void setSensor9(Integer sensor9) {
		this.sensor9 = sensor9;
	}
	public Integer getSensor10() {
		return sensor10;
	}
	public void setSensor10(Integer sensor10) {
		this.sensor10 = sensor10;
	}
	
	
	public Integer getFactoryDevType() {
		return factoryDevType;
	}

	public void setFactoryDevType(Integer factoryDevType) {
		this.factoryDevType = factoryDevType;
	}
	
	public boolean isGpsDevice() {
		int protocol = 0;
		if (this.protocol != null) {
			protocol = this.protocol;
		}
	    if (protocol == 3 || protocol == 5 || protocol == 10 || protocol == 11) {
	        return true;
	    } else {
	    	int factory = 0;
	    	if (this.protocol != null) {
	    		factory = this.factoryType;
	    	}
	        //部标设备，厂家类型为我们对接过的类型
	        boolean bRet = false;
	        if (protocol == 6) {
	            bRet = true;
	            if (factory == 6 || factory == 8 || factory == 21 || factory == 21 || factory == 23
	                || factory == 23 || factory == 27 || factory == 28) {
	                bRet = false;
	            }
	        }
	        return bRet;
	    }
	}
	
	//判断GPS是否有效
	public boolean isGpsValid() {
		if (status1 != null) {
			int valid = (status1&0x01);
			if (valid == 1) {
				return true;
			}
		}
		return false;
	}
	
	//格式化下GPSTime
	public void formatGpsTime() {
		if (gpsTime != null && !gpsTime.isEmpty()) {
			Date date = DateUtil.StrLongTime2Date(gpsTime);	//如果时间格式是不对的
			if (date == null) {
				date = new Date();
				date.setTime(Long.parseLong(gpsTime));
				gpsTime = DateUtil.dateSwitchString(date);
			}
		}
	}	
}
