package jb.mina;

public class MinaRequest {
	private String requestText;
	private String response;
	public String getRequestText() {
		return requestText;
	}
	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public MinaRequest(String requestText) {
		super();
		this.requestText = requestText;
	}		
}
