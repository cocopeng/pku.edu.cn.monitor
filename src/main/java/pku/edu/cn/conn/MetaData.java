package pku.edu.cn.conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pku.edu.cn.Entity.ZtreeNode;

public class MetaData {

	private static final String USERSYS="0";
	private static final String ACCESSNODE = "1";
	private static final String DATACENTER = "2";
	
	public ResultSet getMetadata() {
		String sql = "select * from meta_view where 1 order by t_id";
		Connection conn = DbConnection.getConnection();
		Statement stat;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getCenterMetadata() {
		String sql = "select * from meta_view where nodeKind ='2'";
		Connection conn = DbConnection.getConnection();
		ResultSet rs = null;
		try {
			Statement stat = conn.createStatement();
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
//	public ResultSet getAccessNodeByCenterIp(String ip){
//		String sql = "select * from meta_view where upIPAddr='"+ip+"'";
//		Connection conn = DbConnection.getConnection();
//		ResultSet rs =null;
//		try {
//			Statement stat =conn.createStatement();
//			rs=stat.executeQuery(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rs;
//	}

	public List<ZtreeNode> getAccessNodeByCenterIp(String ip){
		String sql = "select * from meta_view where upIPAddr='"+ip+"'";
		Connection conn = DbConnection.getConnection();
		List<ZtreeNode> list = new ArrayList<ZtreeNode>();
		ResultSet rs =null;
		try {
			Statement stat =conn.createStatement();
			rs=stat.executeQuery(sql);
			while(rs.next()){
				ZtreeNode node = new ZtreeNode();
				node.setId(rs.getString("t_ipAddr"));
				node.setpId(ip);
				node.setName(rs.getString("accessNodeName"));
				node.setOpen(true);
				node.setStdname("访问节点");
				node.setIcon("./asset/img/treeicon/sjfwjd.PNG");
				node.setAddress(rs.getString("t_phyAddr"));
				node.setCreateTime(rs.getString("t_createTime"));
				node.setUpip(rs.getString("upIPAddr"));
				node.setStoreCap(rs.getString("accessNodeCapacity"));
				node.setServiceCap(rs.getString("accessNodeServiceCapacity"));
				list.add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<ZtreeNode> getSysNodeByAccessIp(String ip){
		String sql = "select * from meta_view where upIPAddr='"+ip+"'";
		Connection conn = DbConnection.getConnection();
		List<ZtreeNode> list = new ArrayList<ZtreeNode>();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				ZtreeNode node = new ZtreeNode();
				node.setId(rs.getString("t_ipAddr"));
				node.setpId(ip);
				node.setName(rs.getString("userSysName"));
				node.setOpen(true);
				node.setStdname("用户系统");
				node.setIcon("./asset/img/treeicon/yhxt.PNG");
				node.setAddress(rs.getString("t_phyAddr"));
				node.setCreateTime(rs.getString("t_createTime"));
				node.setUpip(rs.getString("upIPAddr"));
				list.add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getDataCenterIpList() {
		List<String> list = new ArrayList<String>();
		String sql = "select t_ipAddr from meta_view where nodeKind ='2' ";
		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt= conn.createStatement();;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getAccessUpip(String name){
		Connection conn = DbConnection.getConnection();
		String sql = "select * from t_data_center where dataCenterName='"+name+"'";
		String upip = null;
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				upip = rs.getString("ipAddr");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return upip;
	}
	public static String getUsersysUpip(String name){
		Connection conn = DbConnection.getConnection();
		String sql = "select * from t_data_access_node where accessNodeName='"+name+"'";
		String upip = null;
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				upip = rs.getString("ipAddr");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return upip;
	}

	public static String getNameByIp(String ip){
		Connection conn = DbConnection.getConnection();
		String sql = "select * from meta_view where t_ipAddr='"+ip+"'";
		String name = null;
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()){
				String nodekind = rs.getString("nodeKind");
				if(DATACENTER.equals(nodekind)){
					name = rs.getString("dataCenterName");
				}
				else if(ACCESSNODE.equals(nodekind)){
					name = rs.getString("accessNodeName");
				}
				else{
					name = rs.getString("userSysName");
				}
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public List<String> getlog(){
		List<String> list = new ArrayList<String>();
		String sql = "select * from logs where 1 order by id desc limit 0,10 ";
		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt= conn.createStatement();;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("name"));
				list.add(rs.getString("content"));
				list.add(rs.getString("createtime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getlog1(){
		List<String> list1 = new ArrayList<String>();
		String sql = "select * from logs where 1 order by id desc limit 0,1000 ";
		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt= conn.createStatement();;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list1.add(rs.getString("name"));
				list1.add(rs.getString("content"));
				list1.add(rs.getString("createtime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list1;
	}
	
	public List<String> getlogchange(String ip,String group){
		List<String> list = new ArrayList<String>();
		String sql = "select * from logs where ip='"+ip+"'"+" order by id desc limit 0,10";
		Connection conn = DbConnection.getConnection();
		try {
			Statement stmt= conn.createStatement();;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("name"));
				list.add(rs.getString("content"));
				list.add(rs.getString("createtime"));
			}
			if(group.equals("1")){
				String sql1="select * from t_metadata_node where upIPAddr='"+ip+"'";
				ResultSet rs1 = stmt.executeQuery(sql1);
				List<String> iplist = new ArrayList<String>();
				while(rs1.next()){
					iplist.add(rs1.getString("ipAddr"));
				}
				for(int i=0;i<iplist.size();i++){
					String s = "select * from logs where ip='"+iplist.get(i)+"'"+" order by id desc limit 0,10";
					ResultSet r = stmt.executeQuery(s);
					while(r.next()){
						list.add(r.getString("name"));
						list.add(r.getString("content"));
						list.add(r.getString("createtime"));
					}
				}
				
			}
			if(group.equals("2")){
				String sql2 = "select * from t_metadata_node where upIPAddr='"+ip+"'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				List<String> acclist = new ArrayList<String>();
				List<String> allip = new ArrayList<String>();
				while(rs2.next()){
					acclist.add(rs2.getString("ipAddr"));
					allip.add(rs2.getString("ipAddr"));
				}
				for(int i=0;i<acclist.size();i++){
					String s1 = "select * from t_metadata_node where upIPAddr='"+acclist.get(i)+"'";
					ResultSet r1 = stmt.executeQuery(s1);
					while(r1.next()){
						allip.add(r1.getString("ipAddr"));
					}
				}
				for(int i=0;i<allip.size();i++){
					String s2 = "select * from logs where ip='"+allip.get(i)+"'"+" order by id desc limit 0,10";
					ResultSet r2 = stmt.executeQuery(s2);
					while(r2.next()){
						list.add(r2.getString("name"));
						list.add(r2.getString("content"));
						list.add(r2.getString("createtime"));
					}
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int[][] sitechartinfo(){
		Connection conn = DbConnection.getConnection();
		List<String> datacenterlist = new ArrayList<String>();
		List<String> datacenterlist1 = new ArrayList<String>();		
		int datacenterlength = 0;
		try{
			Statement stmt=conn.createStatement();
			String sql1 = "select dataCenterName,ipAddr from t_data_center where 1 order by id";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while(rs1.next()){
				datacenterlist.add(rs1.getString("dataCenterName"));
				datacenterlist1.add(rs1.getString("ipAddr"));
			}
			datacenterlength=datacenterlist.size();
			int[][] result = new int [datacenterlength][25];
			for(int a=0;a<datacenterlength;a++){
				MetaData meta = new MetaData();
				result[a]=meta.getsitebycenterip(datacenterlist1.get(a));
			}
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			int [][] result = new int[1][25];
			for(int i=0;i<25;i++)
				result[0][i]=0;
			return result;
		}
	}
	
	/*public int[][] statechartinfo(){
		Connection conn = DbConnection.getConnection();
		List<String> datacenterlist = new ArrayList<String>();
		List<String> datacenterlist1 = new ArrayList<String>();
		
		int datacenterlength = 0;
		String currenttime = "";
	
		try{
			Statement stmt=conn.createStatement();
			ResultSet rs1 = stmt.executeQuery("select curtime()");
			while(rs1.next()){
				//currenttime=rs1.getString(1);
				currenttime="23:59:00";
			}
			//System.out.println(currenttime);
			String sql1 = "select dataCenterName,ipAddr from t_data_center where 1 order by id";
			ResultSet rs2 = stmt.executeQuery(sql1);
			while(rs2.next()){
				datacenterlist.add(rs2.getString("dataCenterName"));
				datacenterlist1.add(rs2.getString("ipAddr"));
			}
			datacenterlength=datacenterlist.size();
			int[][] result = new int [datacenterlength][25];
			for(int a=0;a<datacenterlength;a++)
				for(int b=0;b<25;b++)
					result[a][b]=0;
			for(int i=0;i<datacenterlist1.size();i++){
				//System.out.println("datacenter="+datacenterlist1.get(i));
			
					String sql3 = "SELECT createtime,finishtime,isfinish FROM logs_for_data_access_node WHERE upip='"+datacenterlist1.get(i)+"'";
					ResultSet rs4 = stmt.executeQuery(sql3);
					List<String> tmplist = new ArrayList<String>();
					while(rs4.next()){
						tmplist.add(rs4.getString("createtime"));
						tmplist.add(rs4.getString("finishtime"));
						tmplist.add(rs4.getString("isfinish"));
					}
					for(int k=0;k<tmplist.size();k=k+3){
						String begin=parsetime(tmplist.get(k));
						int start = Integer.parseInt(begin);
						int end = start;
						if(tmplist.get(k+2).equals("0")){
							String[] tmp = currenttime.split(":");
							String finish = tmp[0];
							end = Integer.parseInt(finish);
						}
						if(tmplist.get(k+2).equals("1")){
							String finish = parsetime(tmplist.get(k+1));
							end = Integer.parseInt(finish);
						}
						for(int l=start;l<=end;l++){
							result[i][l]++;
						}
					}
				
			}
			return result;
			
		}catch(SQLException e){
			e.printStackTrace();
			int [][] result = new int[1][25];
			for(int i=0;i<25;i++)
				result[0][i]=0;
			return result;
		}
	}*/
	
	public int[][] getbarinfo(){
		List<String> datacenterlist = new ArrayList<String>();
		List<String> datacenterlist1 = new ArrayList<String>();
		Connection conn = DbConnection.getConnection();
		try{
			Statement stmt=conn.createStatement();
			String sql1 = "select dataCenterName,ipAddr from t_data_center where 1 order by id";
			ResultSet rs1 = stmt.executeQuery(sql1);
			while(rs1.next()){
				datacenterlist.add(rs1.getString("dataCenterName"));
				datacenterlist1.add(rs1.getString("ipAddr"));
			}
			int datacenterlength = 0;
			datacenterlength=datacenterlist.size();
			int[][] result = new int [datacenterlength][25];
			for(int a=0;a<datacenterlength;a++)
				for(int b=0;b<25;b++)
					result[a][b]=0;
			for(int i=0;i<datacenterlist1.size();i++){
				String sql2 = "select * from logs_for_press  where nodekind='2' and ip='"+datacenterlist1.get(i)+"'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				List<String> tmplist = new ArrayList<String>();
				while(rs2.next()){
					tmplist.add(rs2.getString("time"));
					tmplist.add(rs2.getString("cpu"));
				}
				for(int k=0;k<tmplist.size();k=k+2){
					String time1=parsetime(tmplist.get(k));
					int time=Integer.parseInt(time1);
					String c1=tmplist.get(k+1);
					double c2=Double.valueOf(c1);
					int c3=(int)c2;
					result[i][time]=c3;
				}
				
			}
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			int [][] result = new int[1][25];
			for(int i=0;i<25;i++)
				result[0][i]=0;
			return result;
		}
			
	}
	
	public List<String> getdatacentername(){
		Connection conn = DbConnection.getConnection();
		List<String> list = new ArrayList<String>();
		try{
			Statement stmt=conn.createStatement();
			String sql = "select dataCenterName from t_data_center where 1 order by id";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("dataCenterName"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public int[] getsitebyaccessip(String ip){
		Connection conn = DbConnection.getConnection();
		int[] result = new int[25];
		for(int i=0;i<25;i++){
			result[i]=0;
		}
		try{
			String sql = "select createtime from logs_for_data_access_node where ip='"+ip+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("createtime"));
			}
			for(int i=0;i<list.size();i++){
				String t1 = parsetime(list.get(i));
				int time = Integer.parseInt(t1);
				result[time]++;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*public int[] getsitebyaccessip(String ip){
		Connection conn = DbConnection.getConnection();
		int[] result = new int[25];
		for(int i=0;i<25;i++){
			result[i]=0;
		}
		String currenttime="";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs1 = stmt.executeQuery("select curtime()");
			while(rs1.next()){
				//currenttime=rs1.getString(1);
				currenttime="23:59:00";
			}
			String sql = "SELECT createtime,finishtime,isfinish FROM logs_for_data_access_node WHERE ip='"+ip+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("createtime"));
				list.add(rs.getString("finishtime"));
				list.add(rs.getString("isfinish"));
			}
			for(int i=0;i<list.size();i=i+3){
				String begin = parsetime(list.get(i));
				int start = Integer.parseInt(begin);
				int end = start;
				if(list.get(i+2).equals("0")){
					String[] tmp = currenttime.split(":");
					String finish = tmp[0];
					end = Integer.parseInt(finish);
				}
				if(list.get(i+2).equals("1")){
					String finish = parsetime(list.get(i+1));
					end = Integer.parseInt(finish);
				}
				for(int l=start;l<=end;l++){
					result[l]++;
				}
						
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}*/
	
	public int[] getsitebycenterip(String ip){
		Connection conn = DbConnection.getConnection();
		int[] result = new int[25];
		for(int i=0;i<25;i++){
			result[i]=0;
		}
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from t_metadata_node where upIPAddr='"+ip+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("ipAddr"));
			}
			for(int i=0;i<list.size();i++){
				String accip = list.get(i);
				String sql1 = "select createtime from logs_for_data_access_node where ip='"+accip+"'";
				ResultSet rs1 = stmt.executeQuery(sql1);
				List<String> list1 = new ArrayList<String>();
				while(rs1.next()){
					list1.add(rs1.getString("createtime"));
				}
				for(int j=0;j<list1.size();j++){
					String t1 = parsetime(list1.get(j));
					int time = Integer.parseInt(t1);
					result[time]++;
				}							
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int[] getbarbyaccessip(String ip){
		Connection conn = DbConnection.getConnection();
		int[] result = new int[24];
		for(int i=0;i<24;i++){
			result[i]=0;
		}
		try{
			Statement stmt = conn.createStatement();
			
			String sql = "select time,cpu from logs_for_press where nodekind='1' and ip='"+ip+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("time"));
				list.add(rs.getString("cpu"));
			}
			for(int i=0;i<list.size();i=i+2){
				String time = parsetime(list.get(i));
				int num = Integer.parseInt(time);
				String c1 = list.get(i+1);
				double c2= Double.valueOf(c1);
				result[num]=(int)c2;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	public int[] getbarbycenterip(String ip){
		Connection conn = DbConnection.getConnection();
		int[] result = new int[25];
		for(int i=0;i<24;i++){
			result[i]=0;
		}
		try{
			Statement stmt = conn.createStatement();
			
			String sql = "select time,cpu from logs_for_press where nodekind='2' and ip='"+ip+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString("time"));
				list.add(rs.getString("cpu"));
			}
			for(int i=0;i<list.size();i=i+2){
				String time = parsetime(list.get(i));
				int num = Integer.parseInt(time);
				String c1 = list.get(i+1);
				double c2= Double.valueOf(c1);
				result[num]=(int)c2;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertlog(String localip,String remoteip,String content,String time){
		Connection conn = DbConnection.getConnection();
		try{
			Statement stmt = conn.createStatement();
			String sql = "insert into logs(name,content,createtime,type,ip) values('日志','"+content+"','"+time+"','2','"+localip+"')";
			System.out.println(sql);
			stmt.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void insert_data_access_node(String localip,String remoteip,String time){
		Connection conn = DbConnection.getConnection();
		try{
			Statement stmt = conn.createStatement();
			String sql = "insert into logs_for_data_access_node(ip,createtime) values('"+remoteip+"','"+time+"')";
			System.out.println(sql);
			stmt.execute(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static String parsetime(String time){
		String[] tmp=time.split(" ");
		String[] tmp1=tmp[1].split(":");
		return tmp1[0];
	}
	
	public static void main(String args[]) {

		MetaData me = new MetaData();
		int[][] a = me.sitechartinfo();
		for(int i=0;i<4;i++){
			for(int j=0;j<24;j++)
				System.out.print(a[i][j]+" / ");
			System.out.println();
		}
		
		int[] b=me.getsitebycenterip("192.170.20.179");
		for(int i=0;i<24;i++){
			System.out.print(b[i]+" / ");
		}
	}
	

}
