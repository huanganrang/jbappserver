package jb.mina;

import java.nio.charset.Charset;

import jb.absx.Objectx;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class JsonProtocalDecoder extends Objectx implements ProtocolDecoder {  
    private final Charset charset;  
    private IoBuffer buf = IoBuffer.allocate(500).setAutoExpand(true);
    private boolean start = true;
    private byte start_tag = '{';
    public JsonProtocalDecoder() {  
        this(Charset.defaultCharset());  
    }  
  
    public JsonProtocalDecoder(Charset charset) {  
        this.charset = charset;  
    }  


  
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {         
       byte[] end = new byte[3];
       byte[] temp = new byte[2];
       
       while (in.hasRemaining()) {
           byte b = in.get(); 
           if(start){
        	   if(b!=start_tag){
        		   continue;
        	   }else{
        		   start = false;
        	   }
           }
           switch (b){
               case '}':
            	   buf.put(b);
            	   if(checkJson(end,b,temp)){
            		   buf.flip();
            		   try{           			   
	            		   String msg = buf.getString(charset.newDecoder());
	            		   out.write(msg);	            		  
            		   }catch(Exception e){
            			   logger.error("通知解码错误", e);
            		   }finally{
            			   buf.sweep(); 
	            		   start = true;
            		   }
            	   }   
            	   break;
               case ']':
            	   checkJson(end,b,temp);
               default:
                   buf.put(b);
           }
                
       }
    }  
    
    private boolean checkJson(byte[] end,byte b,byte[]temp){
    	temp[0] = end[1];
    	temp[1] = end[2];
    	end[0] = temp[0];
    	end[1] = temp[1];
    	end[2] = b;
    	if(end[0] == '}'&&end[1]==']'&&end[2]=='}')
    		return true;
    	return false;
    }
  
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {  
    }  
  
    public void dispose(IoSession session) throws Exception {  
    }  
  
}  
