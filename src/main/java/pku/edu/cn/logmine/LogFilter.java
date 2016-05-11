package pku.edu.cn.logmine;

public class LogFilter {
	public LogFilter(){
		
	}

	public boolean isNeeded(String line) {
		// TODO Auto-generated method stub
		/*int length=line.length()-2;
		line=line.substring(1,length);
		System.out.println(line);*/
		if(line.contains("LocalIp")&&line.contains("RemoteIp")&&line.contains("State")){
			return true;
		}
		System.out.println("right log");
		return false;
	}
	public static void main(String args[]){
		LogFilter filter = new LogFilter();
		//System.out.print(filter.isNeeded("363  [main] INFO  com.serverAgent.ServerAgent - End:ServerAgent method;LocalIp=192.168.213.235;RemoteIP=192.168.213.231;State=success,xxxxx"));
		System.out.println(filter.isNeeded("{'message':' Begin:dataBaseConn method;LocalIp=192.170.20.183;RemoteIp=88.3.29.8;State=success','@version':'1','@timestamp':'2014-11-27T00:21:13.471+08:00','type':'user-interface','host':'0.0.0.0','path':'/var/log/lilu/server.log'}"));
	}
}