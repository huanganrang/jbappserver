package jb.android.handler;

import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.auth.AuthToken;
import org.androidpn.server.xmpp.handler.IQHandler;
import org.androidpn.server.xmpp.session.ClientSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.IQ;

public abstract class BaseHandler extends IQHandler {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		validLogin(packet); 
		return handle(packet);
	}

	protected abstract IQ handle(IQ packet);
	
	private void validLogin(IQ packet) throws UnauthorizedException{
		ClientSession session = sessionManager.getSession(packet.getFrom());
		//sessionManager.gets
	    AuthToken token = session.getAuthToken();
		if(token == null){
			 throw new UnauthorizedException(
                    "you have not login.");
		}
	} 
}
