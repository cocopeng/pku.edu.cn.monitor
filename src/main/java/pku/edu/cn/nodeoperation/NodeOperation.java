package pku.edu.cn.nodeoperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import pku.edu.cn.Entity.DataAccessNode;
import pku.edu.cn.Entity.DataCenterNode;
import pku.edu.cn.Entity.UserSysNode;
import pku.edu.cn.conn.DbConnection;
import pku.edu.cn.conn.MetaData;

public class NodeOperation {
	public String getNodeInfo(String id, String group) {
		if(group.equals("0")){
			UserSysNode node = getUserSysNode(id);
			JSONObject jsonObject = JSONObject.fromObject(node); 
			return jsonObject.toString();
		}
		else if(group.equals("1")){
			DataAccessNode node = getAccessNode(id);
			JSONObject jsonObject = JSONObject.fromObject(node); ; 
			return jsonObject.toString();
		}
		else{
			DataCenterNode node = getDataCenetrNode(id);
			JSONObject jsonObject = JSONObject.fromObject(node); 
			return jsonObject.toString();
		}
	}

	private static UserSysNode getUserSysNode(String id) {
		// TODO Auto-generated method stub
		String sql ="select * from t_user_system where ipAddr='"+id+"'";
		Connection conn = DbConnection.getConnection();
		UserSysNode node =new UserSysNode();
		Statement stat;
		ResultSet rs;
		try {
			stat = conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next()){
				node.setIp(id);
				node.setSub(rs.getString("organization"));
				node.setType(rs.getString("sysType"));
			}
			List<String> interfaceList = new ArrayList<String>();
			node.setInterfaceList(interfaceList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return node;
	}

	private static DataAccessNode getAccessNode(String id) {
		String sql ="select * from t_data_access_node where ipAddr='"+id+"'";
		String sqlChild = "select * from t_metadata_node where upIpAddr='"+id+"'";
		Connection conn = DbConnection.getConnection();
		DataAccessNode node = new DataAccessNode();
		Statement stat;
		ResultSet rs;
		try {
			stat = conn.createStatement();
			rs= stat.executeQuery(sql);
			if(rs.next()){
				node.setIp(id);
				if("0".equals(rs.getString("accessNodekind"))){
					node.setType("情报信息库");
				}
				else{
					node.setType("地理信息库");
				}		
			}
			
			List<String> list = new ArrayList<String>();
			rs=stat.executeQuery(sqlChild);
			while(rs.next()){
				list.add(rs.getString("ipAddr"));
			}
			node.setUserIpList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return node;
	}
	
	public static void main(String args[]){
		DataAccessNode node = getAccessNode("10.10.10.3");
		System.out.println("ip"+node.getIp());
	}
	private static DataCenterNode getDataCenetrNode(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from  t_data_center where ipAddr='"+id+"'";
		Connection conn = DbConnection.getConnection();
		DataCenterNode node = new DataCenterNode();
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			MetaData meta = new MetaData();
			List<String> iplist = meta.getDataCenterIpList();
			if(rs.next()){
				if(rs.getString("ipAddr").equals("")){
					node.setCenterType("主");
				}
				else{
					node.setCenterType("从");
				}
				node.setIp(id);
				node.setCapacity(rs.getString("dataCenterCapacity"));	
				node.setServerCapacity(rs.getString("dataCenterServiceCapacity"));
				node.setPosition(rs.getString("phyAddr"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}
}
