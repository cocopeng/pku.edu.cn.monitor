package pku.edu.cn.Entity;

public class DataCenterNode {
	private String ip;
	private String centerType;
	private String position;
	private String capacity;
	private String serverCapacity;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getServerCapacity() {
		return serverCapacity;
	}
	public void setServerCapacity(String serverCapacity) {
		this.serverCapacity = serverCapacity;
	}
}
