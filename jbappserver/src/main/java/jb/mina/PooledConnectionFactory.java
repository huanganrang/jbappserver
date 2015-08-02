package jb.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import jb.listener.Application;
import jb.util.Constants;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class PooledConnectionFactory extends BasePooledObjectFactory<IoSession>{
    private String hostName;
    private int port;
    private int connectionCount;
    private long connectTimeoutMillis;
    private long writeTimeoutMillis;
    private int idleTime; //秒
    private ProtocolCodecFilter protocolCodecFilter;
    private IoHandler ioHandler;

    public String getHostName(){
        return hostName;
    }

    public void setHostName(String hostName){
        this.hostName = hostName;
    }

    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public int getConnectionCount(){
        return connectionCount;
    }

    public void setConnectionCount(int connectionCount){
        this.connectionCount = connectionCount;
    }

    public long getConnectTimeoutMillis(){
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(long connectTimeoutMillis){
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public long getWriteTimeoutMillis(){
        return writeTimeoutMillis;
    }

    public void setWriteTimeoutMillis(long writeTimeoutMillis){
        this.writeTimeoutMillis = writeTimeoutMillis;
    }

    public int getIdleTime(){
        return idleTime;
    }

    public void setIdleTime(int idleTime){
        this.idleTime = idleTime;
    }

    public ProtocolCodecFilter getProtocolCodecFilter(){
        return protocolCodecFilter;
    }

    public void setProtocolCodecFilter(ProtocolCodecFilter protocolCodecFilter){
        this.protocolCodecFilter = protocolCodecFilter;
    }

    public IoHandler getIoHandler(){
        return ioHandler;
    }

    public void setIoHandler(IoHandler ioHandler){
        this.ioHandler = ioHandler;
    }

    @Override
    public IoSession create() throws Exception{
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(connectTimeoutMillis);
        connector.getSessionConfig().setBothIdleTime(idleTime);
        connector.getSessionConfig().setTcpNoDelay(true);
        connector.getFilterChain().addLast("codec", protocolCodecFilter);
        connector.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
        connector.setHandler(ioHandler);
        String hostPort = Application.getString(Constants.SYSTEM_TCP_URL);
		String[] strs = hostPort.split("[:;]");
        ConnectFuture future = connector.connect(new InetSocketAddress(strs[0], Integer.parseInt(strs[1])));
        boolean completed = future.awaitUninterruptibly(connectTimeoutMillis);
        if(!completed){
            throw new TimeoutException();
        }
        IoSession ioSession = future.getSession();
        return ioSession;
    }

 
    @SuppressWarnings("rawtypes")
	@Override
    public void destroyObject(PooledObject p) throws Exception{
        IoSession ioSession = (IoSession)p.getObject();
        ioSession.close(false);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PooledObject<IoSession> wrap(IoSession ioSession) {
		return new DefaultPooledObject(ioSession);
	}



}

