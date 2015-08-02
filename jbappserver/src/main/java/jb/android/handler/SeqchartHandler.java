package jb.android.handler;

import jb.service.AppServiceI;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class SeqchartHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:seqchart";

    private Element probeResponse;
      
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public SeqchartHandler() {
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
        String starttime = query.elementText("starttime");
        String endtime = query.elementText("endtime");
        String uid = query.elementText("uid");
        if(logger.isDebugEnabled()){
        	logger.debug("时序图：starttime="+starttime+"|endtime="+endtime+"|uid="+uid);
        }
        String s = JSON.toJSONString(appServiceImpl.seqchart(starttime, endtime, uid));
        if(logger.isDebugEnabled()){
        	logger.debug("时序图结果："+s);
        }
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
