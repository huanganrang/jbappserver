package jb.mina;


/**
 * <pre>
 *   Title: MinaClientManage.java
 *   Description: 
 *   Project:智能地址归集系统
 *   Copyright: yundaex.com Copyright (c) 2013
 *   Company: 上海韵达货运有限公司
 * </pre>
 * 
 * @author huangzhi
 * @version 3.0
 * @date 2014年8月7日
 */
public class MinaClientManage {

	public static void main(String[] args) throws InterruptedException {	/*
		System.out.println(123232);
		startMinaClient("localhost",52221);*/
		//MinaClient client = new MinaClient("localhost",5222);
		/*startMinaClient("117.143.238.118",2800);
		Thread.currentThread().sleep(300000);*/
	}
	

	
	
	public static void startMinaClient(String ip,int port){
		 Thread shh = new Thread("监控mina thread"){
				private String ip;
				private int port;
				public void run(){
					MinaClient client = new MinaClient(ip,port,new WarnMessageHandler());
					while(true){										
						try {
							sleep(1000*10);
							checkTcp(client);
						} catch (InterruptedException e) {
							break;
						}
						
					}
										
				}
				
				private void checkTcp(MinaClient client){
					if(!client.isOk()){
						client.restart();
					}else{
						long nowTime = System.currentTimeMillis();
						if((nowTime - client.getLastTime())>1000*90){
							if(!client.sendHeartbeat()){
								client.restart();
							}
						}
					}
				}
				
				public Thread set(String ip,int port){
					this.ip = ip;
					this.port = port;
					return this;
				}
			}.set(ip, port);
			shh.setDaemon(true);
			shh.start();
	}

}
