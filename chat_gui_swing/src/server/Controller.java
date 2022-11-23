package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import client.TCPClient;
import core.FileInfo;
import core.Service;
import core.UserInfo;
import event.EventChat;
import event.EventExitOrLogout;
import event.EventServer;
import event.PublicEvent;
import guiCore.HomeChatServer;
import guiCore.HomeClient;

public class Controller implements ActionListener  {
	private ServerGuiView view;
	private int port = 9900;
	private TCPServer tcpServer;
	private ArrayList<UserInfo> arrayListUserDatabaseToGUI;
	private  SubChatClientView viewSub;
	
	public void actionPerformed(ActionEvent e) {

	}
    public Controller(ServerGuiView view) {
    	
        this.view = view;
        tcpServer = new TCPServer(port,view.getTextAreaResult(),view);
        tcpServer.open();
        tcpServer.start();
        arrayListUserDatabaseToGUI = new ArrayList<UserInfo>();
        this.viewSub = new SubChatClientView();
        this.viewSub.setVisible(false);
        
		// getuser tu database va ve lai bang
        arrayListUserDatabaseToGUI = tcpServer.getService().getListUser();
		view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI,tcpServer.getArrayListUser());
        
        PublicEvent.getInstance().addeventServer(new EventServer() {
			
			@Override
			public void UpdateUser(String username, int option) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void Adduser(String username, String Password) {
				// TODO Auto-generated method stub
				System.out.println("User In system "+tcpServer.getArrayListUser().toString());
			}

			@Override
			public void SelectTableUser(String username,String status) {
				// TODO Auto-generated method stub
				for (UserInfo userInfo : arrayListUserDatabaseToGUI) {
					if(username.equals(userInfo.getUsername())) {
						view.getTxtUsername().setText(username);
						view.getTxtComboBoxStatus().setSelectedIndex(userInfo.getStatus());
						break;
					}
				}
				
			}

			@Override
			public void UpdateStatusUserLoginInSystem(ArrayList<UserInfo> userInfoInSystem) {
				view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, userInfoInSystem);
			}

			@Override
			public void KickUser(String username, String statusInSytem) {
				// TODO Auto-generated method stub
				for (UserInfo userInfo : tcpServer.getArrayListUser()) {
					if(username.equals(userInfo.getUsername())) {
						try {
							userInfo.getSocket().close();
							tcpServer.getArrayListUser().remove(tcpServer.getArrayListUser().indexOf(userInfo));
							view.UpdateUserLoginInSystem(arrayListUserDatabaseToGUI, tcpServer.getArrayListUser());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println("kick user");
						}
					}
				}
			}

			@Override
			public void ChatWithServer() {
				// SHOW US SOME MESSAGE
				viewSub.setVisible(true);
//				JOptionPane.showMessageDialog(view.getContentPane(), view.getTxtUsername().getText() + " Clicked");
			}

		});
        PublicEvent.getInstance().addEventChat(new EventChat() {
			
			@Override
			public void sendMessage(String text) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendFile() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void newMessage(String text) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void downloadFile(FileInfo fileInfo) {
				// TODO Auto-generated method stub
				
			}
		});
        PublicEvent.getInstance().addeventExitOrLogout(new EventExitOrLogout() {
			
			@Override
			public void sendLogoutToServer() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendExitToServer() {
				viewSub.setVisible(false);
				
			}
		});
    }
}
