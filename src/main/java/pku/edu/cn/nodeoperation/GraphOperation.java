package pku.edu.cn.nodeoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import pku.edu.cn.Entity.Edges;
import pku.edu.cn.Entity.Legend;
import pku.edu.cn.Entity.Node;
import pku.edu.cn.Entity.NodesScale;
import pku.edu.cn.Entity.NodesType;
import pku.edu.cn.conn.MetaData;
import pku.edu.cn.util.GraphXmlParser;
import net.sf.json.JSONObject;

public class GraphOperation {
	private static final int MAINCENTER=1;
	private static final String USERSYS="0";
	private static final String ACCESSNODE = "1";
	private static final String DATACENTER = "2";
	MetaData meta = new MetaData();
	public List getNodesList(ResultSet result){
		List list = new ArrayList();
		try {
			List<String> iplist = meta.getDataCenterIpList();
			while(result.next()){
				Node node = new Node();
				node.setId(result.getString("t_ipAddr"));
				if(USERSYS.equals(result.getString("nodeKind"))){
					node.setName(result.getString("userSysName"));
					node.setGroup(Integer.parseInt(GraphXmlParser.getSysNodeGroup()));
					node.setShape(GraphXmlParser.getSysNodeShape());
					node.setImagePath(GraphXmlParser.getSysNodeImagePath());
					node.setHeight(Integer.parseInt(GraphXmlParser.getSysNodeHeight()));
					node.setWidth(Integer.parseInt(GraphXmlParser.getSysNodeWidth()));
					node.setX(Integer.parseInt(GraphXmlParser.getSysNodeX()));
					node.setY(Integer.parseInt(GraphXmlParser.getSysNodeY()));
					node.setSize(Integer.parseInt(GraphXmlParser.getSysNodeSize()));
				}
				else if(ACCESSNODE.equals(result.getString("nodeKind"))){
					node.setName(result.getString("accessNodeName"));
					node.setGroup(Integer.parseInt(GraphXmlParser.getAccessNodeGroup()));
					node.setShape(GraphXmlParser.getAccessNodeShape());
					node.setImagePath(GraphXmlParser.getAccessNodeImagePath());
					node.setHeight(Integer.parseInt(GraphXmlParser.getAccessNodeHeight()));
					node.setWidth(Integer.parseInt(GraphXmlParser.getAccessNodeWidth()));
					node.setX(Integer.parseInt(GraphXmlParser.getAccessNodeX()));
					node.setY(Integer.parseInt(GraphXmlParser.getAccessNodeY()));
					node.setSize(Integer.parseInt(GraphXmlParser.getAccessNodeSize()));
				}
				else{
					node.setName(result.getString("dataCenterName"));
					node.setGroup(Integer.parseInt(GraphXmlParser.getCenterNodeGroup()));
					node.setShape(GraphXmlParser.getCenterNodeShape());
					
					node.setHeight(Integer.parseInt(GraphXmlParser.getCenterNodeHeight()));
					node.setWidth(Integer.parseInt(GraphXmlParser.getCenterNodeWidth()));
					node.setX(Integer.parseInt(GraphXmlParser.getCenterNodeX()));
					node.setY(Integer.parseInt(GraphXmlParser.getCenterNodeY()));
					System.out.println("maincetor");
					if(result.getInt("maincenter")==MAINCENTER){
						node.setSize(Integer.parseInt(GraphXmlParser.getMainCenterSize()));
						node.setImagePath(GraphXmlParser.getMainCenterNodeImagePath());
					}
					else{
						node.setSize(Integer.parseInt(GraphXmlParser.getCenterNodeSize()));
						node.setImagePath(GraphXmlParser.getCenterNodeImagePath());
					}				
				}
				list.add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	public List getEdgesList(ResultSet reslut){
		List list = new ArrayList<Object>();
		try {
			List<Edges> centerList = new ArrayList<Edges>();
			while(reslut.next()){
				if(reslut.getString("upIpAddr")!=null){
					Edges edge = new Edges();
					edge.setId1(reslut.getString("t_ipAddr"));
					edge.setId2(reslut.getString("upIpAddr"));
					edge.setColor(GraphXmlParser.getEdgeColor());
					edge.setType(GraphXmlParser.getEdgeLineType());
					list.add(edge);
				}
				else{
					Edges edge = new Edges();
					edge.setId1(reslut.getString("t_ipAddr"));
					edge.setColor(GraphXmlParser.getEdgeColor());
					edge.setType(GraphXmlParser.getEdgeLineType());
					centerList.add(edge);
				}	
			}
			int centerNodeSize = centerList.size();
			for(int j = 0;j<centerNodeSize;j++){
				int i = j;
				while(i<centerNodeSize-1){
					Edges edge = new Edges();
					edge.setId1(centerList.get(j).getId1());
					edge.setId2(centerList.get(i+1).getId1());
					edge.setColor(centerList.get(j).getColor());
					edge.setType(centerList.get(j).getType());
					list.add(edge);
					i++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
//	public Map<String,Map> getLegend(){
//		Map map = new HashMap<String,List>();
//		List NodesList = new ArrayList<Object>();
//		NodesType nodesType1 = new NodesType();
//		nodesType1.setShape("star");
//		nodesType1.setColor("rgb(255,255,0)");
//		nodesType1.setText("数据中心");
//		nodesType1.setId("nodesLegendId1");
//		
//		NodesType nodesType2 = new NodesType();
//		nodesType2.setShape("square");
//		nodesType2.setColor("rgb(255,0,0)");
//		nodesType2.setText("数据访问节点");
//		nodesType2.setId("nodesLegendId2");
//		
//		NodesType nodesType3 = new NodesType();
//		nodesType3.setShape("sphere");
//		nodesType3.setColor("rgb(0,255,0)");
//		nodesType3.setText("用户系统");
//		nodesType3.setId("nodesLegendId4");
//		
//		NodesList.add(nodesType1);
//		NodesList.add(nodesType2);
//		NodesList.add(nodesType3);
//		map.put("nodes", NodesList);
//		NodesScale nodesScale = new NodesScale();
//		nodesScale.setX(-500);
//		nodesScale.setY(-700);
//		Map nodesMap = new HashMap();
//		nodesMap.put("nodes", nodesScale);
//		map.put("pos", nodesMap);
//		return map;
//	}
	public Map<String,Object> getLegend(){
		Map<String,Object> legendMap = new HashMap<String,Object>();
		
		Map<String,Object> nodeListMap = new HashMap<String,Object>();
		List<String> nodesList = new ArrayList<String>();
		nodeListMap.put("nodes", nodesList);
		
		Map<String,Legend> nodesMap = new HashMap<String, Legend>();
		Legend legend = new Legend();
		legend.setX(Integer.parseInt(GraphXmlParser.getLegendX()));
		legend.setY(Integer.parseInt(GraphXmlParser.getLegendY()));
		nodesMap.put("nodes", legend);
		nodeListMap.put("pos",nodesMap);
		
		return nodeListMap;
	}
	public static void main(String args[]){
	
		GraphOperation test =new GraphOperation();
		System.out.println("jasonData :"+test.getJsonData());

	}
	public JSONObject getDataJson(List nodeList,List edgesList,Map legend){ 
		Map map = new Hashtable<String,Object>();
		map.put("legend", legend);
		map.put("edges", edgesList);
		map.put("nodes", nodeList);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject;
	}

	public String getAddNodeJson(Node node,Object edge){
		Map map = new HashMap<String,Object>();
		map.put("addNode", node);
		map.put("addEdge", edge);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();	
	}
	public static String getJsonData(){
		GraphOperation help = new GraphOperation();
		MetaData meta = new MetaData();
		ResultSet rs= meta.getMetadata();
		ResultSet rs1 =meta.getMetadata(); 
		List<Node> list = help.getNodesList(rs);
		List<Edges> listedge = help.getEdgesList(rs1);
		Map map = help.getLegend();
		return help.getDataJson(list, listedge, map).toString();
	}
	
}
