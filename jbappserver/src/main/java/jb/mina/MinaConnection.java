package jb.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
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
	@Override
	public void exceptionCaught(IoSession session, Throwable e)
			throws Exception {
		session.close(false);
	}
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		// 如果IoSession闲置，则关闭连接
		if (status == IdleStatus.BOTH_IDLE) {
			session.close(false);
		}
	}
}
