package com.cmsv6demo;

import com.babelstar.gviewer.NetClient;

public class RecordFile {
	//szFileInfo:	szFile[256]:nYear:nMonth:nDay:uiBegintime:uiEndtime:szDevIDNO:nChn:nFileLen:nFileType:nLocation:nSvrID
	byte[]  orginalFileInfo = new byte[1024];
	int	orginalFileLen = 0;
	
	String devIdno;
	String fileInfo;
	String name;
	Integer year;
	Integer month;
	Integer day;
	Integer beginTime;
	Integer endTime;
	Integer chn;
	Integer fileLength;
	Integer fileType;
	Integer location;
	Integer svrId;
	Boolean isPlaying;
	
	public String getDevIdno() {
		return devIdno;
	}
	public void setDevIdno(String devIdno) {
		this.devIdno = devIdno;
	}
	public Boolean getIsPlaying() {
		return isPlaying;
	}
	public void setIsPlaying(Boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Integer beginTime) {
		this.beginTime = beginTime;
	}
	public Integer getEndTime() {
		return endTime;
	}
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	public Integer getChn() {
		return chn;
	}
	public void setChn(Integer chn) {
		this.chn = chn;
	}
	public Integer getFileLength() {
		return fileLength;
	}
	public void setFileLength(Integer fileLength) {
		this.fileLength = fileLength;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public Integer getSvrId() {
		return svrId;
	}
	public void setSvrId(Integer svrId) {
		this.svrId = svrId;
	}
	
	public void setOrginalFileInfo(byte[] info, int length) {
		System.arraycopy(info, 0, orginalFileInfo, 0, length);
		orginalFileLen = length;
	}
	
	public byte[] getOrginalFile() {
		return orginalFileInfo;
	}
	
	public int getOrginalLen() {
		return orginalFileLen;
	}
	
	protected String formatSecond2Time(Integer time) {
		return String.format("%02d:%02d:%02d", time.intValue()/3600, time.intValue()%3600/60, time.intValue()%60);
	}
	
	public String getFileTime() {
		return String.format("%04d-%02d-%02d    %s - %s", getYear(), getMonth(), getDay()
				, formatSecond2Time(beginTime), formatSecond2Time(endTime));
	}
	
	static public String sGetFileTypeRsID(int fileType) {
		if (fileType == NetClient.GPS_FILE_TYPE_ALL) {
			return "All";
		} else if (fileType == NetClient.GPS_FILE_TYPE_NORMAL) {
			return "Normal";
		} else {
			return "Alarm";
		}
	}
}
