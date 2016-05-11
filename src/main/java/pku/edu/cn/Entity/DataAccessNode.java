package pku.edu.cn.Entity;

import java.util.List;

public class DataAccessNode {
	private String ip;
	private String type;
	private List<String> userIpList;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getUserIpList() {
		return userIpList;
	}
	public void setUserIpList(List<String> userIpList) {
		this.userIpList = userIpList;
	}
	
}
