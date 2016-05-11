package pku.edu.cn.listener;

import pku.edu.cn.Entity.MetadataNode;



public abstract class ServerListenerAdapter implements ServerListener {
	 public void nodeChanged(String nodeJson){}
	 public void AddNode(MetadataNode metadataNode){}
	 public void mainCenterChanged(String ip){}
	 public void statusChanged(String ip){}
	 public void Animation(String json){};
	 public void addLog(String log){};
}
