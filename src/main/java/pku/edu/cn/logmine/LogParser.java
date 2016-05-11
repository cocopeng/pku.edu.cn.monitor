package pku.edu.cn.logmine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pku.edu.cn.conn.MetaData;
import net.sf.json.JSONObject;

public class LogParser {
	public LogParser(){
		
	}
	public String parseLog(String line){
		
		Map<String,LogInfo> map = new HashMap<String,LogInfo>();
		String tmp = line.substring(line.indexOf("LocalIp"));
		System.out.println(tmp);
		String splitTmp[] = tmp.split(";");
		

		String t1[] = tmp.split("\"");
		System.out.println(t1[0]);
		String t2[] = t1[0].split(";");
		String local = t2[0].split("=")[1];
		String remote = t2[1].split("=")[1];
		String sta = t2[2].split("=")[1];
		System.out.println(local+"  "+remote+"  "+sta);
		
		
		//String localIp = splitTmp[1].substring(splitTmp[0].indexOf("=")+1);
		String localIp = remote;
		
		String localName = null;
		if(localIp!=null){
			localName = MetaData.getNameByIp(localIp);
		}
		
		//String remoteIp = splitTmp[0].substring(splitTmp[1].indexOf("=")+1);
		String remoteIp = local;
		
		String remoteName = null;
		if(remoteIp!=null){
			remoteName = MetaData.getNameByIp(remoteIp);
		}

		//String state = splitTmp[2].substring(splitTmp[2].indexOf("=")+1,splitTmp[2].indexOf("\""));
		String state = sta;
		
		LogInfo info = new LogInfo();
		Calendar cal = Calendar.getInstance();
		String time = "";
		//time = time + cal.get(Calendar.YEAR) + "-"+ cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		time = df.format(now);
		
		//System.out.println(time);
		
		info.setLocalIp(localIp);
		info.setRemoteIp(remoteIp);
		info.setState(state.trim());
		info.setMessage(localName+"向"+remoteName+"发送请求");
		info.setTime(time);
		
		MetaData meta = new MetaData();
		String content=localName+"向"+remoteName+"发送请求";
		meta.insertlog(localIp.trim(), remoteIp.trim(),content,time);
		System.out.println("localIP="+localIp);
		System.out.println("remoteIP="+remoteIp);
		meta.insert_data_access_node(localIp.trim(), remoteIp.trim(), time);
		
		if(state.trim().equals("success")){
			map.put("success", info);
		}
		else{
			map.put("failure", info);
		}
		Map<String,Object> logMap = new HashMap<String,Object>();
		logMap.put("log", map);
		JSONObject jsonObject = JSONObject.fromObject(logMap);
		return jsonObject.toString();
	}
	public static void main(String args[]){
		LogParser parser = new LogParser();
//		System.out.println(parser.parseLog("363  [main] INFO  com.serverAgent.ServerAgent - End:ServerAgent method;LocalIp=192.168.213.235;RemoteIP=192.168.213.231;State=success\",\"xxxxxxxxxxxxxx"));
//		System.out.println(parser.parseLog("message\":\"363  [main] INFO  com.serverAgent.ServerAgent - End:ServerAgent method;LocalIp=192.169.10.5;RemoteIP=192.169.10.6;State=success\",\"version\":\"1\",\"@timestamp\":"));
		File file = new File("C:\\Users\\CAEIT-33\\Desktop\\new.log");
		BufferedReader reader = null;
		try{
			//InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			reader = new BufferedReader(new FileReader(file));
			String tmp = null;
			while((tmp=reader.readLine())!=null){
				System.out.println("old log:"+tmp);
				System.out.println("after parse");
				System.out.println(parser.parseLog(tmp));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
