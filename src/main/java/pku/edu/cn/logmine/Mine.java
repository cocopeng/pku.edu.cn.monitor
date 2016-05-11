package pku.edu.cn.logmine;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import pku.edu.cn.listener.Notifier;

public class Mine extends Thread{
	private String line;
	private  BufferedReader br ;
	private String filename;
	private File file = null;
    private long lastModifieTime = 0L;
	public Mine(String filename,BufferedReader br,String line)throws FileNotFoundException{
		this.filename =filename;
		this.br = br;
		this.line = line;
		file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        lastModifieTime = file.lastModified();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long newTime = 0L;
		LogFilter filter = new LogFilter();
		LogParser parser = new LogParser();
        while(true) {
            newTime = file.lastModified();
               if (newTime != lastModifieTime) {
                lastModifieTime = newTime;
                System.out.println("new log appears!");
            }
            
             try {
				while ((line = br.readLine()) != null) {
					if(!filter.isNeeded(line)){
						continue;
					}
					String dataJson = parser.parseLog(line);
					System.out.println("test-----------------------");
					System.out.println(dataJson);
					Notifier.getNotifier().fireAmination(dataJson);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
