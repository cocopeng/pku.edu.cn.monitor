package pku.edu.cn.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class GraphXmlParser {
	private static Document dom;
	private static Element maincenter;
	private static Element center_node;
	private static Element access_node;
	private static Element sys_node;
	private static Element edge;
	private static Element legend;
	static{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom= db.parse(GraphXmlParser.class.getResourceAsStream("/pku/edu/cn/conf/Graph.xml"));
			maincenter = (Element) dom.getElementsByTagName("maincenter").item(0);
			center_node = (Element) dom.getElementsByTagName("centernode").item(0);
			access_node = (Element) dom.getElementsByTagName("accessnode").item(0);
			sys_node = (Element) dom.getElementsByTagName("sysnode").item(0);
			edge = (Element) dom.getElementsByTagName("edge").item(0);
			legend = (Element) dom.getElementsByTagName("legend").item(0);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getMainCenterNodeImagePath(){
		return maincenter.getElementsByTagName("imagePath").item(0).getFirstChild().getNodeValue();
	}
	
	public static String getMainCenterSize(){
		return center_node.getElementsByTagName("mainCenterSize").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeGroup(){
		return center_node.getElementsByTagName("group").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeShape(){
		return center_node.getElementsByTagName("shape").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeWidth(){
		return center_node.getElementsByTagName("width").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeHeight(){
		return center_node.getElementsByTagName("height").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeImagePath(){
		return center_node.getElementsByTagName("imagePath").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeX(){
		return center_node.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeY(){
		return center_node.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();
	}
	public static String getCenterNodeSize(){
		return center_node.getElementsByTagName("size").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeGroup(){
		return access_node.getElementsByTagName("group").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeShape(){
		return access_node.getElementsByTagName("shape").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeWidth(){
		return access_node.getElementsByTagName("width").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeHeight(){
		return access_node.getElementsByTagName("height").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeImagePath(){
		return access_node.getElementsByTagName("imagePath").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeX(){
		return access_node.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeY(){
		return access_node.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();
	}
	public static String getAccessNodeSize(){
		return access_node.getElementsByTagName("size").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeGroup(){
		return sys_node.getElementsByTagName("group").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeWidth(){
		return sys_node.getElementsByTagName("width").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeShape(){
		return sys_node.getElementsByTagName("shape").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeHeight(){
		return sys_node.getElementsByTagName("height").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeImagePath(){
		return sys_node.getElementsByTagName("imagePath").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeX(){
		return sys_node.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeY(){
		return sys_node.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();
	}
	public static String getSysNodeSize(){
		return sys_node.getElementsByTagName("size").item(0).getFirstChild().getNodeValue();
	}
	public static String getEdgeColor(){
		return edge.getElementsByTagName("color").item(0).getFirstChild().getNodeValue();
	}
	public static String getEdgeLineType(){
		return edge.getElementsByTagName("line_type").item(0).getFirstChild().getNodeValue();
	}
	public static String getEdgeDashedType(){
		return edge.getElementsByTagName("dashed_type").item(0).getFirstChild().getNodeValue();
	}
	public static String getLegendX(){
		return legend.getElementsByTagName("x").item(0).getFirstChild().getNodeValue();
	}
	public static String getLegendY(){
		return legend.getElementsByTagName("y").item(0).getFirstChild().getNodeValue();
	}
	
	public static void main(String args[]){
		System.out.println(GraphXmlParser.getCenterNodeShape());
	}
}
