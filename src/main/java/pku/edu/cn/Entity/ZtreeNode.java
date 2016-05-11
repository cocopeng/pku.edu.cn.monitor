package pku.edu.cn.Entity;

public class ZtreeNode {
	private String id;
	private String pId;
	private String name;
	private Boolean open;
	private String stdname;
	private String icon;
	private String address;
	private String createtime;
	private String upip;
	private String mainCenter;
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
	
	public String getMainCenter(){
		return mainCenter;
	}
	public void setMainCenter(String mainCenter){
		this.mainCenter = mainCenter;
	}
	
	public String getUpip(){
		return upip;
	}
	public void setUpip(String upip){
		this.upip = upip;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStdname() {
		return stdname;
	}
	public void setStdname(String stdname) {
		this.stdname = stdname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreateTime(String createtime) {
		this.createtime = createtime;
	}
	
	
	public ZtreeNode(String id, String pId, String name, boolean open,String stdname,String icon,String address,String createtime,String upip) {
		// TODO Auto-generated constructor stub
		this.id =id;
		this.pId = pId;
		this.name =name;
		this.open = open;
		this.stdname = stdname;
		this.icon = icon;
		this.address = address;
		this.createtime = createtime;
		this.upip = upip;
	}
	public ZtreeNode() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
