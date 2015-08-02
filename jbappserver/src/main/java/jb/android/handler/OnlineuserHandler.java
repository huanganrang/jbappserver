package jb.android.handler;

import jb.service.impl.AndroidUserServiceImpl;

import org.androidpn.server.xmpp.XmppServer;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class OnlineuserHandler extends BaseHandler {
	 private static final String NAMESPACE = "jabber:iq:onlineuser";

    private Element probeResponse;
    
    private AndroidUserServiceImpl userService;

    /**
     * Constructor.
     */
    public OnlineuserHandler() {
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        userService = (AndroidUserServiceImpl)XmppServer.getInstance().getBean("userService");      
    }

	@Override
	protected IQ handle(IQ packet) {
		IQ reply = null;
		reply = IQ.createResultIQ(packet);       
        String s = JSON.toJSONString(userService.getUserInfo());
        Element t =  probeResponse.createCopy();
        Element e = t.addElement("onlineuser");
        e.setText(s);
        reply.setChildElement(t);
        return reply;
	}
	
	
	@Override
	public String getNamespace() {

        return NAMESPACE;
	}

}
