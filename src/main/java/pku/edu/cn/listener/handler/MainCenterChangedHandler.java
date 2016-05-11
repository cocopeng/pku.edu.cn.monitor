package pku.edu.cn.listener.handler;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import pku.edu.cn.listener.ServerListenerAdapter;
import pku.edu.cn.websockets.WebSocket;
import net.sf.json.JSONObject;


public class MainCenterChangedHandler extends ServerListenerAdapter {
	public void mainCenterChanged(String ip){
		if(WebSocket.list.size()==0){
			System.out.println("没链接");
			return;
		}
		ArrayList<Session> delSession = new ArrayList<Session>();
		Map map = new HashMap<>();
		map.put("mainCenterChanged", ip);
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		for(Session session:WebSocket.list){
			if(session.isOpen()){
				System.out.println("session is open");
				try {
					System.out.println("发出去了");
					session.getBasicRemote().sendText(jsonObject.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				delSession.add(session);
			}
		}
		if(delSession.size()>0){
			WebSocket.list.removeAll(delSession);
			System.out.println("session removed");
		}
	}
	public static void main(String args[]){
		Map map = new HashMap<>();
		map.put("mainCenterChanged", "xxxxxxxxxxx");
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
	}
}
