package core;

import java.io.Serializable;

public class ExitOrLogout implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ExitOrLogout(String username) {
		super();
		this.username = username;
	}

}
