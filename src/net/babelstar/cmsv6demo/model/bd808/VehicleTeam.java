package net.babelstar.cmsv6demo.model.bd808;

import java.util.ArrayList;
import java.util.List;

public class VehicleTeam {
	public static final int TEAM_TYPE_COMPANY = 1;
	public static final int TEAM_TYPE_GROUP = 2;
	
	private Integer id;			//数据库生成的序号
	private Integer parentId;	//父类编号
	private String name;		//名称
	private Integer companyId;	//公司ID
	private Integer level;     //类别 1为公司 2为车队
	private Integer vehiCount = 0;	//分组下面车辆的数目
	private List<VehicleTeam> lstChildTeam = new ArrayList<VehicleTeam>();
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVehiCount() {
		return vehiCount;
	}
	public void setVehiCount(Integer vehiCount) {
		this.vehiCount = vehiCount;
	}
	
	public void addVehiCount() {
		++ this.vehiCount;
	}
	
	public void addChildTeam(VehicleTeam team) {
		this.lstChildTeam.add(team);
	}
	public List<VehicleTeam> getLstChildTeam() {
		return lstChildTeam;
	}
	public void setLstChildTeam(List<VehicleTeam> lstChildTeam) {
		this.lstChildTeam = lstChildTeam;
	}
}
