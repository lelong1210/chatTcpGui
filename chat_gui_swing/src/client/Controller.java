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
import event.EventChat;
import event.PublicEvent;
import guiCore.*;

public class Controller implements ActionListener {
	private TCPClient tcpClient;
	private String host = "localhost";
	private int port = 9900;
	private boolean isSendFile = false;

	public Controller(ClientGuiView view) throws Exception {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

			String username = JOptionPane.showInputDialog(view, "nhập user của bạn");

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
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					String userDes = "";

					if (view.getHome().getChat().getChatTitle().getUserDes() == null) {
						userDes = JOptionPane.showInputDialog(view, "nhập user bạn muốn nhắn tin");
						view.getHome().getChat().getChatTitle().setUserDes(userDes);
						view.getHome().getChat().getChatTitle().setUserName(userDes);

						if (isSendFile) {
							String sourceFilePath = text.trim();
							String destinationDir = "/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/";

							MessInfo messInfo = new MessInfo(username, userDes, text, null);
							tcpClient.sendFile(sourceFilePath, destinationDir, messInfo);
						} else {
							tcpClient.sendMess(new MessInfo(username, userDes, text, null));
						}

					} else {

						if (isSendFile) {
							String sourceFilePath = text.trim();
							String destinationDir = "/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Server/";

							MessInfo messInfo = new MessInfo(username, view.getHome().getChat().getChatTitle().getUserDes(), text, null);
							tcpClient.sendFile(sourceFilePath, destinationDir, messInfo);
						} else {
							tcpClient.sendMess(new MessInfo(username,
									view.getHome().getChat().getChatTitle().getUserDes(), text, null));
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

		} catch (Exception ex) {

		}
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