package pku.edu.cn.rest;

import java.util.ArrayList;
import java.util.List;

public class DetectThread extends Thread{
	
	List<String> list = OperSql.getIPList();
	static List<String> downlist = new ArrayList<String>();
	
	public boolean monitor(){
		OperSql os = new OperSql();
		for(int i=0;i<list.size();i++){
			if(!os.checknode(list.get(i))){
				boolean f = true;
				for(int j=0;j<downlist.size();j++){
					if(list.get(i).equals(downlist.get(j))){
						f = false;
					}
				}
				if(f){
					downlist.add(list.get(i));
					os.update(list.get(i));
					System.out.println("node down:"+list.get(i));

				}
			}
		}
		
		return true;
	}
	
	public void run(){
		while(true){
			//System.out.println("sleep 5s...");
			if(monitor()){
				//System.out.println("monitoring");
			}
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
