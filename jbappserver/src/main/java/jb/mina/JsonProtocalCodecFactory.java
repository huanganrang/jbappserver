package jb.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

public class JsonProtocalCodecFactory implements ProtocolCodecFactory {  
    private final TextLineEncoder encoder;  
    private final JsonProtocalDecoder decoder;  
  
    public JsonProtocalCodecFactory(Charset charset) {  
        encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);;  
        decoder = new JsonProtocalDecoder(charset);  
    }  
  
    public ProtocolEncoder getEncoder(IoSession session) {  
        return encoder;  
    }  
  
    public ProtocolDecoder getDecoder(IoSession session) {  
        return decoder;  
    }  
}    
  
