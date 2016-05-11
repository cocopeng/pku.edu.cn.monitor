package pku.edu.cn.Entity;

import java.sql.Date;

public class Node {
	private String id;
	private String name;
	private int group;
	private String shape;
	private int size;
	private int width;
	private int height;
	private String imagePath;
	private int x;
	private int y;
	private String upIpaddr;
	private String createTime;
	private String phyAddr;
	private String storeCap;
	private String serviceCap;
	
	public String getStoreCap(){
		return storeCap;
	}
	public void setStoreCap(String storeCap){
		this.storeCap = storeCap;
	}
	
	public String getServiceCap(){
		return serviceCap;
	}
	public void setServiceCap(String serviceCap){
		this.serviceCap = serviceCap;
	}
	public String getUpIpaddr() {
		return upIpaddr;
	}
	public void setUpIpaddr(String upIpaddr) {
		this.upIpaddr = upIpaddr;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPhyAddr() {
		return phyAddr;
	}
	public void setPhyAddr(String phyAddr) {
		this.phyAddr = phyAddr;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
