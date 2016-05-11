package pku.edu.cn.Entity;

import java.util.List;

public class UserSysNode {
	private String ip;
	private String type;
	private String sub;
	private List<String> interfaceList;
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
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public List<String> getInterfaceList() {
		return interfaceList;
	}
	public void setInterfaceList(List<String> interfaceList) {
		this.interfaceList = interfaceList;
	}
}
