package pku.edu.cn.listener;



import java.util.Vector;

import pku.edu.cn.Entity.MetadataNode;


public class Notifier {
	private Vector listeners = null;
	private static Notifier instance = null;
	private Notifier(){
		listeners = new Vector<>();
	}

	public static synchronized Notifier getNotifier(){
		if(instance == null){
			instance = new Notifier();
			return instance;
		}else return instance;
	}
	
	public void addListener(ServerListener listener){
		synchronized (listeners) {
			if(!listeners.contains(listener)){
				listeners.add(listener);
			}
		}
	}
	public void fireNodeChanged(String nodeJson){
		for(int i =0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).nodeChanged(nodeJson);
		}
	}
	public void fireAddNode(MetadataNode metadataNode){
		for(int i =0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).AddNode(metadataNode);
		}
	}
	public void fireMainCenterChanged(String ip){
		for(int i =0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).mainCenterChanged(ip);
		}
	}
	public void fireAmination(String json){
		for(int i =0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).Animation(json);
		}
	}
	public void fireStatusChanged(String ip){
		for(int i =0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).statusChanged(ip);
		}
	}
	public void fireAddLog(String log){
		for(int i=0;i<listeners.size();i++){
			((ServerListener) listeners.get(i)).addLog(log);
		}
	}
}
