package jb.android.handler;

import jb.service.AppServiceI;

import org.androidpn.server.service.UserNotFoundException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class MenuHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:menu";

    private Element probeResponse;
    
  //  private AndroidUserServiceImpl userService;
    
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public MenuHandler() {
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        //userService = (AndroidUserServiceImpl)XmppServer.getInstance().getBean("userService"); 
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        appServiceImpl = wac.getBean(AppServiceI.class);
    }

	@Override
	protected IQ handle(IQ packet) {
		IQ reply = null;
		reply = IQ.createResultIQ(packet);       
		ClientSession session = sessionManager.getSession(packet.getFrom());
		String username = null;
		try {
			username = session.getUsername();
		} catch (UserNotFoundException e1) {
			logger.error("session.getUsername()失败", e1);
		}
        String s = JSON.toJSONString(appServiceImpl.getAppShowTree(username,session));    
        if(logger.isDebugEnabled()){
			logger.debug("返回menus："+s);
		}
        Element t =  probeResponse.createCopy();
        Element e = t.addElement("menus");
        e.setText(s);
        reply.setChildElement(t);      
        return reply;
	}
	
	
	@Override
	public String getNamespace() {

        return NAMESPACE;
	}

}
