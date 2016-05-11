package pku.edu.cn.listener.handler;



import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.Session;

import pku.edu.cn.listener.ServerListenerAdapter;
import pku.edu.cn.websockets.WebSocket;


public class AminationHandler extends ServerListenerAdapter {
 
	@Override
	public void Animation(String json) {
		// TODO Auto-generated method stub
	    if(WebSocket.list.size()==0){
			System.out.println("没发出去");
			return;
		}  
		ArrayList<Session> delSession = new ArrayList<Session>();
		for(Session session:WebSocket.list){
			if(session.isOpen()){
				System.out.println("session is open");
				try {
					System.out.println("发出去了");
					session.getBasicRemote().sendText(json);
					System.out.println("log send out-----------------");
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

}
