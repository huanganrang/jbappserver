package jb.android.handler;

import jb.pageModel.Json;
import jb.service.AppServiceI;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class CfgsaveHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:cfgsave";

    private Element probeResponse;
      
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public CfgsaveHandler() {
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
        String resourceIds = query.elementText("resourceIds");
		String nGroupNo = query.elementText("nGroupNo");
		String uidIds = query.elementText("uidIds");
		Json j = new Json();
		try{
			appServiceImpl.grant(nGroupNo, resourceIds.split("[,]"), uidIds.split("[,]"));
			j.setSuccess(true);
			j.setMsg("授权成功！");	
		}catch(Exception e){
			logger.error("授权失败", e);
		}
        String s = JSON.toJSONString(j);
        Element t =  probeResponse.createCopy();
        Element e = t.addElement("data");
        e.setText(s);
        reply.setChildElement(t);
		return reply;
	}
	
	
	@Override
	public String getNamespace() {

        return NAMESPACE;
	}

}
