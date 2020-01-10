package net.babelstar.cmsv6demo.model.bd808;

public class DeviceSearch {
	public static final int SEARCH_TYPE_NET = 1;
	public static final int SEARCH_TYPE_AP = 2;
	
	public static final int NET_TYPE_WIFI = 0;
	public static final int NET_TYPE_LAN = 1;	
	
	//szDevInfo为DevIDNO:NetType(0=Wifi, 1=Net):IP:DevType:chn
	Integer searchType;
	String devIdno;
	Integer netType;
	String netName;
	String chnName;
	String ipAddr;
	Integer devType;
	Integer channel;
	Integer webPort;
	String webUrl;
	
	public Integer getWebPort() {
		return webPort;
	}
	public void setWebPort(Integer webPort) {
		this.webPort = webPort;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public Integer getSearchType() {
		return searchType;
	}
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	public String getDevIdno() {
		return devIdno;
	}
	public void setDevIdno(String devIdno) {
		this.devIdno = devIdno;
	}
	public Integer getNetType() {
		return netType;
	}
	public void setNetType(Integer netType) {
		this.netType = netType;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public Integer getDevType() {
		return devType;
	}
	public void setDevType(Integer devType) {
		this.devType = devType;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public boolean isApMode() {
		return DeviceSearch.SEARCH_TYPE_AP == this.searchType.intValue() ? true : false;
	}
}
