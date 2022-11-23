package event;

import java.util.ArrayList;

import core.UserInfo;

public interface EventServer {
	public void Adduser(String username,String Password);
	public void UpdateUser(String username,int option);
	public void SelectTableUser(String username,String statusInSytem);
	public void UpdateStatusUserLoginInSystem(ArrayList<UserInfo> userInfo);
	public void KickUser(String username,String statusInSytem);
	public void ChatWithServer() ;
}
