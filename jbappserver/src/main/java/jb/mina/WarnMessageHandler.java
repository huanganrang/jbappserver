package jb.mina;

import jb.absx.Objectx;
import jb.service.impl.AppServiceImpl;

public class WarnMessageHandler extends Objectx implements IMinaHandler {

	private AppServiceImpl appService;
	
	public WarnMessageHandler(){
		appService = new AppServiceImpl();
	}
	@Override
	public Object handle(Object msg) {		
		if (msg instanceof String) {
			appService.notification((String)msg);
			if(logger.isDebugEnabled()){
				logger.debug("发出通知消息："+msg);
			}
		}		
		return null;
	}

}
