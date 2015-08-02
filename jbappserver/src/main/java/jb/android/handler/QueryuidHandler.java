package jb.android.handler;

import jb.service.AppServiceI;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class QueryuidHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:queryuid";

    private Element probeResponse;
      
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public QueryuidHandler() {
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
        String quids = query.elementText("quid");
        System.out.println(quids);
        if(logger.isDebugEnabled()){
        	logger.debug("测点值查询quid:"+quids);
        }
        String s = JSON.toJSONString(appServiceImpl.getUidValues(quids.split("[,;|]")));
        if(logger.isDebugEnabled()){
        	logger.debug("测点值查询返回结果:"+s);
        }
        Element t =  probeResponse.createCopy();
        Element e = t.addElement("value");
        e.setText(s);
        reply.setChildElement(t);
        return reply;
	}
	
	
	@Override
	public String getNamespace() {

        return NAMESPACE;
	}

}
