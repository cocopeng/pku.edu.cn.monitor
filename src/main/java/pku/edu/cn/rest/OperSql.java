package pku.edu.cn.rest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pku.edu.cn.conn.DbConnection;

public class OperSql {

	public boolean update(String ip){
		Connection conn = DbConnection.getConnection();
		String sql1 = "select * from t_metadata_node where ipAddr='"+ip+"'";
		try{
			Statement stat1 = conn.createStatement();
			ResultSet rs1=stat1.executeQuery(sql1);
			while(rs1.next()){
				if(rs1.getString("status").equals("1")){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String sql = "update t_metadata_node set status = '1' where ipAddr = '"+ip+"'";;
		System.out.println(sql);
		java.sql.Statement stat;
		try{
			stat = conn.createStatement();
			stat.execute(sql);
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	
	public static List<String> getIPList(){
		List<String> list = new ArrayList<>();
		Connection conn = DbConnection.getConnection();	
		String sql = "select * from t_metadata_node";
		try{
			Statement stat = conn.createStatement();	
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				if(!rs.getString("nodeKind").equals("0"))
					list.add(rs.getString("ipAddr"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public static String parsetime(String time){
		String[] tmp=time.split(" ");
		String[] tmp1=tmp[1].split(":");
		return tmp1[0];
	}
	
	public boolean checknode(String ip){
		Connection conn = DbConnection.getConnection();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curtime = df.format(new Date());
		String curhour=parsetime(curtime);
		boolean flag=false; //当前时间有数据为true
		try{
			Statement stat=conn.createStatement();
			String sql2="select time from logs_for_press where ip='"+ip+"'";
			ResultSet rs2=stat.executeQuery(sql2);
			while(rs2.next()){
				String tmp = rs2.getString("time");
				String tmphour = parsetime(tmp);
				if(tmphour.equals(curhour)){
					flag=true;
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
