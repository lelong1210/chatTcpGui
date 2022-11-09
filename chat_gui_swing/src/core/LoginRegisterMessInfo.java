package core;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginRegisterMessInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<MessInfo> arraylistMessInfo = new ArrayList<>();

	public ArrayList<MessInfo> getArraylistMessInfo() {
		return arraylistMessInfo;
	}

	public void setArraylistMessInfo(ArrayList<MessInfo> arraylistMessInfo) {
		this.arraylistMessInfo = arraylistMessInfo;
	}

	public LoginRegisterMessInfo(String username, String password, int option, boolean status) {
		super();
		this.username = username;
		this.password = password;
		this.option = option;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	private String username;
	private String password;
	private int option;
	private boolean status;
}
