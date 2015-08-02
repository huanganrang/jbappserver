package jb.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangzhi
 * @version 3.0
 * @date 2014年8月7日
 */
public final class MinaClient extends IoHandlerAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String host;
	private int port;
	// 客户端连接器
	private IoConnector connector;

	private IoSession session;

	private boolean ok = false;

	/** 心跳包内容 */
	private static final String HEARTBEATREQUEST = "HEARTBEATREQUEST";
	private static final String HEARTBEATRESPONSE = "HEARTBEATRESPONSE";

	private long lastTime = 0;
	
	private IMinaHandler handler;
	static {
		// map.put("clearSiteCache", new SiteCacheClear());
	}

	public boolean isOk() {

		return ok;
	}

	public void restart() {
		logger.warn("重新连接mina server");
		IoSession _session = session;
		IoConnector _connector = connector;
		session = null;
		connector = null;
		ok = false;
		if (_session != null)
			_session.close(false);
		if (_connector != null)
			_connector.dispose();		
		init();		
	}

	public MinaClient(String host, int port,IMinaHandler handler) {
		this.host = host;
		this.port = port;
		this.handler = handler;
		init();
	}

	private void init() {
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(15000);
		connector.setHandler(this);
		JsonProtocalCodecFactory factory = new JsonProtocalCodecFactory(Charset.forName("UTF-8")); 
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(factory));
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress(
				host, port));
		connectFuture.awaitUninterruptibly(); // 同步，等待，直到连接完成
		if (connectFuture.isDone()) {
			if (!connectFuture.isConnected()) { // 若在指定时间内没连接成功，则抛出异常
				connector.dispose(); // 不关闭的话会运行一段时间后抛出，too many open
				connector = null;
			}
		}
		lastTime = System.currentTimeMillis();
	}

	/*
	 * private void init(){ // 客户端连接器 IoConnector connector = new
	 * NioSocketConnector(); // 实现业务处理逻辑,同服务端一样 connector.setHandler(this); //
	 * 添加过滤器和日志组件 connector.getFilterChain().addLast("codec", new
	 * ProtocolCodecFilter(new TextLineCodecFactory()));
	 * connector.getFilterChain().addLast("logging", new LoggingFilter()); //
	 * 连接服务端 ConnectFuture cf = connector.connect(new
	 * InetSocketAddress("localhost", 8080)); }
	 */

	@Override
	public void messageReceived(IoSession session, Object msg) throws Exception {
		if (msg instanceof String) {
			if (HEARTBEATREQUEST.equals(msg)) {
				session.write(HEARTBEATRESPONSE);
				lastTime = System.currentTimeMillis();
			}else{
				try{
					Object rs = handler.handle(msg);
					if(rs!=null)
					session.write(rs);
				}catch(Exception e){
					logger.error("handler处理异常", e);
				}
			}
		}
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		ok = true;
		this.session = session;
		// 会话打开时向服务端发送当前日期
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		logger.warn("KingWeb(TCP),我連接上來了.....");
		this.session.write("{\"Action\":\"E\",\"Data\":[{}]}");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
		// 如果IoSession闲置，则关闭连接
		if (status == IdleStatus.BOTH_IDLE) {
			session.write(HEARTBEATRESPONSE);
		}
	}

	public boolean sendHeartbeat() {
		try {
			WriteFuture future = this.session.write(HEARTBEATRESPONSE);
			return future.getException() == null;
		} catch (Exception e) {
			logger.error("send heartbeat error", e);
			return false;
		}

	}

	public void sendMessage(String msg) {
		this.session.write(msg);
	}

	public long getLastTime() {
		return lastTime;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable e)
			throws Exception {
		logger.error("exceptionCaught", e);
		ok = false;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}
