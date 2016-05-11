package pku.edu.cn.listener;

import pku.edu.cn.Entity.MetadataNode;

public interface ServerListener {

	void nodeChanged(String nodeJson);
	void AddNode(MetadataNode metadataNode);
	void mainCenterChanged(String ip);
	void Animation(String json);
	void statusChanged(String ip);
	void addLog(String log);
}
