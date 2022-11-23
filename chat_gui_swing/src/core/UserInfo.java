package core;

import java.net.Socket;

public class UserInfo {
	private Socket socket;
	private String username;
	private int status;

	public UserInfo(Socket socket, String username,int status) {
		this.socket = socket;
		this.username = username;
		this.status = status;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
