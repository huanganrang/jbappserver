package jb.android.handler;

import java.util.List;

import jb.pageModel.PageHelper;
import jb.pageModel.UserGroup;
import jb.service.AppServiceI;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.xmpp.packet.IQ;

import com.alibaba.fastjson.JSON;

public class CfgqueryHandler extends BaseHandler {
	private static final String NAMESPACE = "jabber:iq:cfgquery";

    private Element probeResponse;
      
    private AppServiceI appServiceImpl;

    /**
     * Constructor.
     */
    public CfgqueryHandler() {
        probeResponse = DocumentHelper.createElement(QName.get("query",
                NAMESPACE));
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        appServiceImpl = wac.getBean(AppServiceI.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	protected IQ handle(IQ packet) {
		IQ reply = null;
		reply = IQ.createResultIQ(packet);
		Element iq = packet.getElement();
        Element query = iq.element("query");
        String action = query.elementText("action");
        String s = "";
        if("AllGroup".equals(action)){
        	List<UserGroup> rs = appServiceImpl.dataGrid(null, new PageHelper()).getRows();
        	s = JSON.toJSONString(rs);
        }else if("GroupPerm".equals(action)){
        	String groupNo = query.elementText("nGroupNo");
        	s = JSON.toJSONString(appServiceImpl.allTree(groupNo));
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
