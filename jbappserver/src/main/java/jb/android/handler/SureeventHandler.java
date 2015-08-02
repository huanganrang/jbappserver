package jb.android.handler;

import jb.service.AppServiceI;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class SureeventHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:sureevent";

    private Element probeResponse;
      
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public SureeventHandler() {
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        appServiceImpl = wac.getBean(AppServiceI.class);
    }

	@Override
	protected IQ handle(IQ packet) {
		IQ reply = null;
		reply = IQ.createResultIQ(packet);       		
		Element iq = packet.getElement();
        Element query = iq.element("query");
        String id = query.elementText("id");
        String remark = query.elementText("remark");
        String s = JSON.toJSONString(appServiceImpl.sureEvent(id, remark));
        Element t =  probeResponse.createCopy();
        Element e = t.addElement("result");
        e.setText(s);
        reply.setChildElement(t);
		return reply;
	}
	
	
	@Override
	public String getNamespace() {

        return NAMESPACE;
	}

}
