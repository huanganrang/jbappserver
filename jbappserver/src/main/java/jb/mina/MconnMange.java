package jb.mina;

import java.nio.charset.Charset;

import jb.listener.Application;
import jb.util.Constants;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

public final class MconnMange {
	private static GenericObjectPool<IoSession> pool = null;
	private static GenericObjectPool<IoSession> pool2 = null;

	public static GenericObjectPool<IoSession> getPool(){
		if(pool==null){
			synchronized (MconnMange.class) {
				if(pool==null){
					GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
					conf.setMaxTotal(20);
					conf.setMaxIdle(10);
					conf.setTestOnBorrow(true);
					PooledConnectionFactory pooledConnection = new PooledConnectionFactory();
					/*ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();
					factory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
					factory.setEncoderMaxObjectSize(Integer.MAX_VALUE);*/
					StringProtocalCodecFactory factory = new StringProtocalCodecFactory(Charset.forName("UTF-8")); 
					pooledConnection.setProtocolCodecFilter(new ProtocolCodecFilter(factory));
					pooledConnection.setConnectTimeoutMillis(15*1000);
					pooledConnection.setIdleTime(60*1000);
					String hostPort = Application.getString(Constants.SYSTEM_TCP_URL);
					String[] strs = hostPort.split("[:;]");
					pooledConnection.setHostName(strs[0]);
					pooledConnection.setPort(Integer.parseInt(strs[1]));
					pooledConnection.setIoHandler(new MinaConnection());
					pool = new GenericObjectPool<IoSession>(pooledConnection, conf);
					
				}
			}
		}
		return pool;
	}

	public static GenericObjectPool<IoSession> getPool2(){
		if(pool2==null){
			synchronized (MconnMange.class) {
				if(pool2==null){
					GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
					conf.setMaxTotal(20);
					conf.setMaxIdle(10);
					conf.setTestOnBorrow(true);
					PooledConnectionFactory pooledConnection = new PooledConnectionFactory();
					/*ObjectSerializationCodecFactory factory = new ObjectSerializationCodecFactory();
					factory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
					factory.setEncoderMaxObjectSize(Integer.MAX_VALUE);*/
					StringProtocalCodecFactory factory = new StringProtocalCodecFactory(Charset.forName("UTF-8"));
					pooledConnection.setProtocolCodecFilter(new ProtocolCodecFilter(factory));
					pooledConnection.setConnectTimeoutMillis(15*1000);
					pooledConnection.setIdleTime(60*1000);
					String hostPort = Application.getString(Constants.SYSTEM_TCP_URL);
					String[] strs = hostPort.split("[:;]");
					pooledConnection.setHostName(strs[0]);
					pooledConnection.setPort(Integer.parseInt(strs[1]));
					pooledConnection.setIoHandler(new MinaConnection());
					pool2 = new GenericObjectPool<IoSession>(pooledConnection, conf);

				}
			}
		}
		return pool2;
	}
}
