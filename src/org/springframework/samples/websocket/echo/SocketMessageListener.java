package org.springframework.samples.websocket.echo;

public interface SocketMessageListener {
	public void fireMessageChangeEvent();
}
