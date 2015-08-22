package jb.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jb.android.handler.CfgqueryHandler;
import jb.android.handler.CfgsaveHandler;
import jb.android.handler.MenuHandler;
import jb.android.handler.OnlineuserHandler;
import jb.android.handler.QueryuidHandler;
import jb.android.handler.SeqchartHandler;
import jb.android.handler.SureeventHandler;
import jb.mina.MinaClientManage;
import jb.pageModel.BaseData;
import jb.service.BasedataServiceI;
import jb.util.Constants;

import org.androidpn.server.util.ConfigManager;
import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.handler.IQHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Application implements ServletContextListener {
	private static ServletContext context;
	private static String PREFIX = "SV.";
	@Override
	public void contextInitialized(ServletContextEvent event) {	
		 context = event.getServletContext();
		 
		 initAppVariable();
		 XmppServer.getInstance();
		 //System.out.println(System.getProperty("user.dir"));
		 //System.out.println(Application.class.getResource("/").getPath());
		 ConfigManager.getInstance().getConfig().setProperty("server.home.dir", Application.class.getResource("/").getPath());
		 startPush();
	}

	private void startPush(){
		String ipPort = getString(Constants.SYSTEM_TCP_URL);
		String[] strs = ipPort.split("[:;]");
		MinaClientManage.startMinaClient(strs[0], Integer.parseInt(strs[1]));
	}
	
	private static void initAppVariable(){
		ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(context); 
		BasedataServiceI service = app.getBean(BasedataServiceI.class);
		Map<String,BaseData> map = service.getAppVariable();
		for(String key : map.keySet()){
			context.setAttribute(PREFIX+key, map.get(key));
		}
	}
	
	public static List<IQHandler> getHandlers(){
		List<IQHandler> ls = new ArrayList<IQHandler>();
		ls.add(new OnlineuserHandler());
		ls.add(new MenuHandler());
		ls.add(new QueryuidHandler());
		ls.add(new SureeventHandler());
		ls.add(new SeqchartHandler());
		ls.add(new CfgqueryHandler());
		ls.add(new CfgsaveHandler());		
		return ls;
	}
	
	/**
	 * 刷新全局变量值
	 */
	public static void refresh(){
		initAppVariable();
	}
	
	/**
	 * 获取全局变量值
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		BaseData bd = (BaseData)context.getAttribute(PREFIX+key);
		String val = null;
		if(bd != null){
			val = bd.getName();
		}
		return val;
	}
	
	/**
	 * 获取全局变量值
	 * @param key
	 * @return
	 */
	public static BaseData get(String key){
		BaseData bd = (BaseData)context.getAttribute(PREFIX+key);		
		return bd;
	}
	
	public static BasedataServiceI getBasedataService(){
		ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(context); 
		BasedataServiceI service = app.getBean(BasedataServiceI.class);
		return service;
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		

	}

}
