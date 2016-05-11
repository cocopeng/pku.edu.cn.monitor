package pku.edu.cn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pku.edu.cn.listener.handler.AddLogHandler;
import pku.edu.cn.listener.handler.AddNodeHandler;
import pku.edu.cn.listener.handler.AminationHandler;
import pku.edu.cn.listener.handler.MainCenterChangedHandler;
import pku.edu.cn.listener.handler.StatusChangedHandler;

public class ListenerRegister implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		AddNodeHandler adsh = new AddNodeHandler();
		AminationHandler amsh = new AminationHandler();
		MainCenterChangedHandler mainsh =new MainCenterChangedHandler();
		StatusChangedHandler stash = new StatusChangedHandler();
		AddLogHandler addlog = new AddLogHandler();
		Notifier notifier = Notifier.getNotifier();
		notifier.addListener(adsh);
		notifier.addListener(amsh);
		notifier.addListener(mainsh);
		notifier.addListener(stash);
		notifier.addListener(addlog);
	}

}
