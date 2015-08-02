package jb.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @author huangzhi
 * @version 3.0
 * @date 2014年8月7日
 */
public final class MinaConnection extends IoHandlerAdapter {	
	@Override
	public void messageReceived(IoSession session, Object msg) throws Exception {
		if (msg instanceof String) {		
			synchronized (session) {			
				session.setAttribute("response", msg);
				session.notifyAll();
			}				
		}
	}
}
