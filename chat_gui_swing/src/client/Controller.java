package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import core.FileInfo;
import core.MessInfo;
import core.Service;
import event.EventChat;
import event.EventLogin;
import event.PublicEvent;
import guiCore.*;

public class Controller implements ActionListener {
	private TCPClient tcpClient;
	private Service service;
	private String host = "localhost";
	private int port = 9900;
	private boolean isSendFile = false;

	public Controller(ClientGuiView view, LoginView loginView) throws Exception {
		try {
			
			service = new Service();
			
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

			view.setVisible(false);
			PublicEvent.getInstance().addEventLogin(new EventLogin() {

				@Override
				public void register(String username, String password, String rePassword) {

					if (!username.isBlank() && !password.isEmpty() && !rePassword.isEmpty()) {
						JOptionPane.showMessageDialog(loginView.getFrame(), "Register Success", "Alert",
								JOptionPane.DEFAULT_OPTION);
						loginView.paintLogin();
					} else {
						JOptionPane.showMessageDialog(loginView.getFrame(), "Please enter full information", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}

				}

				@Override
				public void login(String username, String password) {
					// TODO Auto-generated method stub
					if (!username.isEmpty() && !password.isEmpty()) {
						System.out.println("Login with: " + username + " <---> " + password);
						if(service.login(username, password).size() != 0) {
							view.setVisible(true);
							loginView.getFrame().setVisible(false);
							connectSocket(view, username);
						}else {
							JOptionPane.showMessageDialog(loginView.getFrame(), "Incorrect Information", "Alert",
									JOptionPane.WARNING_MESSAGE);
						}
						
						
					} else {
						JOptionPane.showMessageDialog(loginView.getFrame(), "Please enter full information", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}

				}
			});

		} catch (Exception ex) {

		}
	}

	public void connectSocket(ClientGuiView view, String username) {
		
		
		tcpClient = new TCPClient(host, port, view.getHome().getChat().getChatBody(), view.getHome().getMenu_Left(),
				view.getHome().getChat().getChatTitle(), username);
		tcpClient.connectServer();

		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						tcpClient.getMess();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		thread.start();

		PublicEvent.getInstance().addEventChat(new EventChat() {
			@Override
			public void sendMessage(String text) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
				String time = dtf.format(now);
				
				String userDes = "";

				if (view.getHome().getChat().getChatTitle().getUserDes() == null) {
					userDes = JOptionPane.showInputDialog(view, "nhập user bạn muốn nhắn tin");
					view.getHome().getChat().getChatTitle().setUserDes(userDes);
					view.getHome().getChat().getChatTitle().setUserName(userDes);

					if (isSendFile) {
						String sourceFilePath = text.trim();
						String destinationDir = "/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/";

						MessInfo messInfo = new MessInfo(username, userDes, text,time, null);
						tcpClient.sendFile(sourceFilePath, destinationDir, messInfo);
					} else {
						tcpClient.sendMess(new MessInfo(username, userDes, text,time, null));
					}

				} else {

					if (isSendFile) {
						String sourceFilePath = text.trim();
						String destinationDir = "/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/";

						MessInfo messInfo = new MessInfo(username, view.getHome().getChat().getChatTitle().getUserDes(),
								text, time,null);
						tcpClient.sendFile(sourceFilePath, destinationDir, messInfo);
					} else {
						tcpClient.sendMess(new MessInfo(username, view.getHome().getChat().getChatTitle().getUserDes(),
								text,time, null));
					}
				}

				refresh(view);
				isSendFile = false;
			}

			@Override
			public void receiveMessage(String text) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				LocalDateTime now = LocalDateTime.now();
			}

			@Override
			public void newMessage(String text) {
				// TODO Auto-generated method stub
				view.getHome().getChat().getChatTitle().setUserDes(text);
				view.getHome().getChat().getChatTitle().setUserName(text);
				view.getHome().getChat().getChatBody().clearChat();
			}

			@Override
			public void sendFile() {
				String path = view.chooseFile();
				isSendFile = true;
				view.getHome().getChat().getChatBottom().getTxt().setText(path);
			}
		});

		refresh(view);
	}

	public void refresh(ClientGuiView view) {
		view.repaint();
		view.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
