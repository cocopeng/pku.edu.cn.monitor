package pku.edu.cn.rest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import pku.edu.cn.Entity.ZtreeNode;
import pku.edu.cn.conn.MetaData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author jiangchao
 *
 */
public class Ztree {
	
	private static final int MAINCENTER=1;
	private static final String USERSYS="0";
	private static final String ACCESSNODE = "1";
	private static final String DATACENTER = "2";
	
	/** 
	* @Title: getZtree 
	* @Description: 获得ztree树的json数据
	* @param 
	* @return String    返回类型 
	* @throws 
	*/
	@POST
	@Path("/ztreenodes")
	@Consumes("application/json")
	@Produces("application/json")
	public String getZtree() throws IOException{
		
		MetaData meta = new MetaData();
		ResultSet rs = meta.getMetadata();
		List<ZtreeNode> list = new ArrayList<ZtreeNode>(); 
		Map<String,Integer> nodeTree = new HashMap<String,Integer>();
		try {
			while(rs.next()){
				ZtreeNode node = new ZtreeNode();
				if(rs.getString("upipAddr")!=null){
					if(USERSYS.equals(rs.getString("nodeKind"))){	
						node.setName("用户系统("+rs.getString("t_phyAddr")+")");
						node.setOpen(true);
						node.setStdname("用户系统");
						node.setIcon("./assets/img/treeicon/yhxt.PNG");
					}
					else{
						node.setName("数据访问节点("+rs.getString("t_phyAddr")+")");
						node.setOpen(true);
						node.setStdname("数据访问节点");
						node.setIcon("./assets/img/treeicon/sjfwjd.PNG");
					}
					if(nodeTree.containsKey(rs.getString("upipAddr"))){
						int tmp = (int)nodeTree.get(rs.getString("upipAddr"));					
						int tmpValue = tmp+1;
						System.out.println(tmpValue);
						nodeTree.put(rs.getString("upipAddr"), tmpValue);
					}
					else{
						nodeTree.put(rs.getString("upipAddr"),1);
					}
					node.setId(rs.getString("t_ipAddr"));
					node.setpId(rs.getString("upipAddr"));
					
					list.add(node);
				}
				else{
					
						int tmp = (int) nodeTree.get("root");
						int tmpValue = tmp+1;
						System.out.println(tmpValue);
						nodeTree.put("root", tmpValue);
					
					if(MAINCENTER==rs.getInt("maincenter")){
						node.setName("主数据中心("+rs.getString("t_phyAddr")+")");
					}
					else{
						node.setName("数据中心("+rs.getString("t_phyAddr")+")");
					}
					node.setStdname("数据中心");
					node.setOpen(true);
					node.setId(rs.getString("t_ipAddr"));
					node.setpId("root");
					node.setIcon("../assets/img/treeicon/sjzx.PNG");
					list.add(node);
				}	
			}
			Iterator iter = nodeTree.entrySet().iterator();
			
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				for(ZtreeNode tmpnode:list){
					if(key.equals(tmpnode.getId())){
						tmpnode.setName(tmpnode.getName()+"("+value+")");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonObject = JSONArray.fromObject(list);
		return jsonObject.toString();
	}
	
	/*@POST
	@Path("/childnodes")
	@Consumes("application/json")
	@Produces("application/json")
	public String getChildZtree(@QueryParam("centerIp") String cenetrIP) throws IOException{
		
		MetaData meta = new MetaData();
		List<ZtreeNode> childList = new ArrayList<ZtreeNode>();
		//get accessNode by center id
		List<ZtreeNode> accessList = meta.getAccessNodeByCenterIp(cenetrIP);
		childList.addAll(accessList);
		for(ZtreeNode node:accessList){
			String aIp = node.getId();
			List<ZtreeNode> list = meta.getSysNodeByAccessIp(aIp);
			childList.addAll(list);
		}

		JSONArray jsonObject = JSONArray.fromObject(childList);
		return jsonObject.toString();
	}*/
	
	
	@POST
	@Path("/childnodes")
	@Consumes("application/json")
	@Produces("application/json")
	public String getChildZtree(@QueryParam("centerIp") String cenetrIP) throws IOException{
		
		MetaData meta = new MetaData();
		List<ZtreeNode> childList = new ArrayList<ZtreeNode>();
		//get accessNode by center id
		List<ZtreeNode> accessList = meta.getAccessNodeByCenterIp(cenetrIP);
		//childList.addAll(accessList);
		for(ZtreeNode node:accessList){
			childList.add(node);
			String aIp = node.getId();
			List<ZtreeNode> list = meta.getSysNodeByAccessIp(aIp);
			childList.addAll(list);
		}

		JSONArray jsonObject = JSONArray.fromObject(childList);
		return jsonObject.toString();
	}
	
	/** 
	* @Title: getTreeNode 
	* @Description: 通过反射机制获得ztree节点详细信息。
	* @param name：反射对应的类名，method:反射对应的方法名。args:反射对应的参数
	* @return String    返回类型 
	* @throws 
	*/
	@POST
	@Path("/ztreenode")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String getTreeNode(@FormParam("class") String name,@FormParam("method") String method,@FormParam("args") String args){
			System.out.println(name+"cnsdkjcs");
			System.out.println(method);
			System.out.println(args);
	        if(name != null && method != null){
	            try{
	                Class cls = Class.forName(name);
	                Object[] parames = !args.equals("") ? args.split(";") : null;
	                Object obj = cls.newInstance();
	                Method[] methods = cls.getMethods();      
	                for(Method m:methods){
	                    System.out.println(m.getName());
	                    if(m.getName().equals(method)){
	                        Object result = parames != null ? m.invoke(obj,parames) : m.invoke(obj); 
	                        System.out.println(result.toString());
	                        return result.toString();
	                    }
	                }    
	            }
	            catch(Exception e){
	                e.printStackTrace();
	            }  
	        }
	        return null;
	}
	@GET
	@Path("/ztree/centernode")
//	@Consumes("application/json")
//	@Produces("application/json")
	public String getCenterNode(){
		MetaData meta = new MetaData();
		ResultSet rs = meta.getMetadata();
		Map<String,Integer> nodeTree = new HashMap<String,Integer>();
		List<ZtreeNode> list = new ArrayList<ZtreeNode>();
		
		DetectThread dt = new DetectThread();
		dt.start();
		
		try {
			while(rs.next()){
		
				if(rs.getString("upIPAddr")!=null&&ACCESSNODE.equals(rs.getString("nodeKind"))){
					if(nodeTree.get(rs.getString("upIPAddr"))!=null){
						int tmp = nodeTree.get(rs.getString("upIPAddr"));
						nodeTree.put(rs.getString("upIPAddr"), tmp+1);
					}
					else{
						nodeTree.put(rs.getString("upIPAddr"), 1);
					}
				}
				if(DATACENTER.equals(rs.getString("nodeKind"))){
					ZtreeNode node = new ZtreeNode();
					node.setId(rs.getString("t_ipAddr"));
					node.setName(rs.getString("dataCenterName"));
					node.setAddress(rs.getString("t_phyAddr"));
					node.setCreateTime(rs.getString("t_createTime"));
					node.setUpip("");
					node.setMainCenter(rs.getString("mainCenter"));
					node.setStoreCap(rs.getString("dataCenterCapacity"));
					node.setServiceCap(rs.getString("dataCenterServiceCapacity"));
					list.add(node);
				}
				
			}
			
			Iterator iter = nodeTree.entrySet().iterator();
			
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				for(ZtreeNode tmpnode:list){
					if(key.equals(tmpnode.getId())){
//						tmpnode.setName(tmpnode.getName()+"("+value+")");
						tmpnode.setName(tmpnode.getName());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return JSONArray.fromObject(list).toString();
	}
	

	int sum1=3;
	@GET
	@Path("/ztree/barchart")
	//@Consumes("application/json")
	//@Produces("application/json")
	public String getBarChart(){
		JSONArray data = new JSONArray();
		MetaData meta = new MetaData();
		int[][] result = meta.getbarinfo();
		List<String> list = meta.getdatacentername();
		data.add(list.size());
		for(int i=0;i<list.size();i++){
			data.add(list.get(i));
		}
		String data1 = "";
		for(int i=0;i<7;i++){
			data1=data1+(i+1)+"/";
		}
		data1=data1+8;
		data.add(data1);
		if(sum1<=7){
			for(int i=0;i<list.size();i++){
				String data2 = "";
				for(int j=0;j<sum1;j++){
					data2=data2+result[i][j]+"/";
				}
				data2=data2+result[i][sum1];
				data.add(data2);
			}
			sum1++;
		}
		if(sum1>7){
			//sum=sum%24;
			//System.out.println("sum="+sum+"sum%24="+sum%24);
			for(int i=0;i<list.size();i++){
				String data2 = "";
				for(int j=sum1%12;j<12;j++){
					data2=data2+result[i][j]+"/";
				}
				for(int j=0;j<sum1%12;j++){
					data2=data2+result[i][j]+"/";
				}
				int len=data2.length()-2;
				data2=data2.substring(0, len);
				data.add(data2);
			}
			sum1++;
		}
		return data.toString();
	}
	
	
	int sum=5;
	@GET
	@Path("/ztree/statechart")
	public String getSiteChart(){
		JSONArray data = new JSONArray();
		MetaData meta = new MetaData();
		int[][] result = meta.sitechartinfo();
		List<String> list = meta.getdatacentername();
		data.add(list.size());
		for(int i=0;i<list.size();i++){
			data.add(list.get(i));
		}
		String data1 = "";
		for(int i=0;i<23;i++){
			data1=data1+(i+1)+"/";
		}
		data1=data1+24;
		data.add(data1);
		if(sum<=23){
			for(int i=0;i<list.size();i++){
				String data2 = "";
				for(int j=0;j<sum;j++){
					data2=data2+result[i][j]+"/";
				}
				data2=data2+result[i][sum];
				data.add(data2);
			}
			sum++;
		}
		if(sum>23){
			//sum=sum%24;
			//System.out.println("sum="+sum+"sum%24="+sum%24);
			for(int i=0;i<list.size();i++){
				String data2 = "";
				for(int j=sum%24;j<24;j++){
					data2=data2+result[i][j]+"/";
				}
				for(int j=0;j<sum%24;j++){
					data2=data2+result[i][j]+"/";
				}
				int len=data2.length()-2;
				data2=data2.substring(0, len);
				data.add(data2);
			}
			sum++;
		}
		
		//System.out.println(sum);
		//System.out.println(data.toString());
		return data.toString();
	}
	
	@GET
	@Path("/ztree/piechart")
	public String getPieChart(){
		
		JSONArray data = new JSONArray();	
		String data1 = "元数据/业务数据/基础作战数据";
		String data2 = "30/50/20";
	
		data.add(data1);
		data.add(data2);
		
		return data.toString();
	}
	
	@GET
	@Path("/ztree/loginfo")
	//@Consumes("application/json")
	//@Produces("application/json")
	public String getLoginfo(){
		
		MetaData meta = new MetaData();
		List<String> list = meta.getlog();
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
		
	} 
	@GET
	@Path("/ztree/loginfo1")
	//@Consumes("application/json")
	//@Produces("application/json")
	public String getLoginfo1(){
		
		MetaData meta = new MetaData();
		List<String> list1 = meta.getlog1();
		JSONArray json = JSONArray.fromObject(list1);
		return json.toString();
		
	} 
	@GET
	@Path("/ztree/loginfo2")
	//@Consumes("application/json")
	//@Produces("application/json")
	public String getLoginfo2(){
		
		MetaData meta = new MetaData();
		List<String> list2 = meta.getlog1();
		JSONArray json = JSONArray.fromObject(list2);
		return json.toString();
		
	} 
	
	int count = 5;
	@GET
	@Path("/ztree/changestatechart")
	public String changeStateChart(@QueryParam("ip") String ip,@QueryParam("group") String group,@QueryParam("upip") String upip,@QueryParam("name") String name){
		JSONArray data = new JSONArray();
		MetaData meta = new MetaData();
		//System.out.println("ip="+ip+"  group="+group+"  upip="+upip+"  name="+name);
		int length=1;
		data.add(length);
		data.add(name);
		String time="1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24";
		data.add(time);
		int [] result = new int[25];
		if(group.equals("1")){
			result=meta.getsitebycenterip(ip);
		}
		else if(group.equals("2")){
			result=meta.getsitebyaccessip(ip);		
		}
		String data1="";
		if(count<=23){
			for(int i=0;i<count;i++){
				data1=data1+result[i]+"/";
			}
			data1=data1+result[count];
			count++;
		}
		if(count>23){
			for(int i=count%24;i<24;i++){
				data1=data1+result[i]+"/";
			}
			for(int i=0;i<count%24;i++){
				data1=data1+result[i]+"/";
			}
			int len=data1.length()-2;
			data1=data1.substring(0, len);
			data.add(data1);
			count++;
		}
		
		data.add(data1); 
		return data.toString();
	}
	
	int count1=3;
	@GET
	@Path("/ztree/changebar")
	public String changeBarChart(@QueryParam("ip") String ip,@QueryParam("group") String group,@QueryParam("name") String name){
		JSONArray data = new JSONArray();
		MetaData meta = new MetaData();
		//System.out.println("ip="+ip+"  group="+group+"  name="+name);
		int length=1;
		data.add(length);
		data.add(name);
		String time="1/2/3/4/5/6/7/8";
		data.add(time);
		int [] result = new int[9];
		if(group.equals("1")){
			result=meta.getbarbycenterip(ip);
		}
		else if(group.equals("2")){
			result=meta.getbarbyaccessip(ip);		
		}
		String data1="";
		if(count1<=7){
			for(int i=0;i<count1;i++){
				data1=data1+result[i]+"/";
			}
			data1=data1+result[count1];
			count1++;
		}
		if(count1>7){
			for(int i=count1%8;i<8;i++){
				data1=data1+result[i]+"/";
			}
			for(int i=0;i<count1%8;i++){
				data1=data1+result[i]+"/";
			}
			int len=data1.length()-2;
			data1=data1.substring(0, len);
			data.add(data1);
			count1++;
		}
		data.add(data1);
		return data.toString();
	}
	
	@GET
	@Path("/ztree/changelog")
	public String changeLog(@QueryParam("ip") String ip,@QueryParam("group") String group){
		MetaData meta = new MetaData();
		List<String> list = meta.getlogchange(ip,group);
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	
	@GET
	@Path("/ztree/detectthread")
//	@Consumes("application/json")
//	@Produces("application/json")
	public void monitorNode(){
		DetectThread dt = new DetectThread();
		dt.start();
		
	}
	
	public static void main(String args[]) throws IOException{
		Ztree z = new Ztree();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		System.out.println(time);
		z.monitorNode();
		//System.out.println(z.changeBarChart("100.60.1.2","1","全军信息服务中心(北京)"));
		//System.out.println(z.changeStateChart("100.60.1.2", "1", "", "全军信息服务中心(北京)"));
		
	}
}
