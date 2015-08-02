package jb.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class StringProtocalDecoder implements ProtocolDecoder {
	private final Charset charset;

	public StringProtocalDecoder() {
		this(Charset.defaultCharset());
	}

	public StringProtocalDecoder(Charset charset) {
		this.charset = charset;
	}

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		String o = in.getString(charset.newDecoder());
		out.write(o);
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
	}

	public void dispose(IoSession session) throws Exception {

	}

}
