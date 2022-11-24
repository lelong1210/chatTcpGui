package client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import chat_gui.Chat_Body;
import chat_gui.Chat_Title;
import core.ExitOrLogout;
import core.FileInfo;
import core.ListMessChat;
import core.LoginRegisterMessInfo;
import core.MessInfo;
import guiCore.Item_People;
import guiCore.LoginView;
import guiCore.Menu_Left;

public class TCPClient {
	// create Socket object
	private Socket client;
	private String host;
	private int port;
	private Chat_Body body;
	private Menu_Left menu_Left;
	private Chat_Title chat_Title;
	private ObjectOutputStream oos = null;
	private LoginView loginView;
	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private ArrayList<ListMessChat> listUserChat = new ArrayList<ListMessChat>();

	public TCPClient(String host, int port, Chat_Body body, Menu_Left menu_Left, Chat_Title chat_Title,LoginView loginView) {
		this.host = host;
		this.port = port;
		this.body = body;
		this.menu_Left = menu_Left;
		this.chat_Title = chat_Title;
		this.loginView = loginView;
	}

	public void connectServer() {
		try {
			this.client = new Socket(host, port);
			this.oos = new ObjectOutputStream(client.getOutputStream());
			System.out.println("connected to server.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(loginView.getFrame(), "Connect Server Failed", "Alert",
					JOptionPane.DEFAULT_OPTION);
			System.exit(0);
		}
	}

	public void sendFile(String sourceFilePath, String destinationDir, MessInfo messInfo) {
		try {
			// get file info
			FileInfo fileInfo = getFileInfo(sourceFilePath, destinationDir);

			// send file
			messInfo.setFileInfo(fileInfo);
			sendMess(messInfo);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getMess() throws Exception{
		
		
		boolean isUserTrue = false;
		
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		MessInfo messInfo = (MessInfo) ois.readObject();

//		if (messInfo.getFileInfo() != null) {
//			createFile(messInfo.getFileInfo());
//		}

		this.chat_Title.setUserName(messInfo.getUserSource());

		Item_People item_People = new Item_People(messInfo.getUserSource());

		item_People.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				body.clearChat();
				for (ListMessChat listMessChat : listUserChat) {
					if (listMessChat.getUsername().equals(messInfo.getUserSource())) {

						chat_Title.setUserName(messInfo.getUserSource());
						chat_Title.setUserDes(messInfo.getUserSource());

						ListMessChat listMess = listUserChat.get(listUserChat.indexOf(listMessChat));

						for (MessInfo mess : listMess.getListMessInfo()) {
							if (username.equals(mess.getUserSource())) {
								body.addItemRight(mess.getMessContent(), mess.getTime(), mess);
							} else {
								body.addItemLeft(mess.getMessContent(), mess.getTime(), mess);
							}
						}

						break;
					}
				}
				body.revalidate();
			}
		});

		for (ListMessChat listMessChat : listUserChat) {
			if (listMessChat.getUsername().equals(messInfo.getUserSource())) {

				ListMessChat listMessChatTmp = listMessChat;
				listMessChatTmp.setMessLast(messInfo.getMessContent());
				listMessChatTmp.getListMessInfo().add(messInfo);

				listUserChat.remove(listUserChat.indexOf(listMessChat));
				listUserChat.add(0, listMessChatTmp);

				isUserTrue = true;
				break;
			}
		}
		if (!isUserTrue) {
			ListMessChat list = new ListMessChat(messInfo.getUserSource(), messInfo.getMessContent(), item_People);
			list.getListMessInfo().add(messInfo);
			listUserChat.add(0, list);
		}

		if (messInfo.getUserSource().equals(chat_Title.getUserDes())) {
			for (ListMessChat listMessChat : listUserChat) {

				if (listMessChat.getUsername().equals(chat_Title.getUserDes())) {
					body.clearChat();
					ListMessChat listMess = listUserChat.get(listUserChat.indexOf(listMessChat));

					for (MessInfo mess : listMess.getListMessInfo()) {
						if (username.equals(mess.getUserSource())) {
							body.addItemRight(mess.getMessContent(), mess.getTime(), mess);
						} else {
							body.addItemLeft(mess.getMessContent(), mess.getTime(), mess);
						}
					}
					body.revalidate();
					break;
				}
			}
		}

		menu_Left.updateListChat(listUserChat);

	}

	public void sendMess(MessInfo messInfo) {
		try {

			this.oos.writeObject(messInfo);

			body.addItemRight(messInfo.getMessContent(), messInfo.getTime(), messInfo);
			// check xem da co user trong list mess chat chưa
			boolean isUserTrue = false;

			Item_People item_People = new Item_People(messInfo.getUserDes());

			item_People.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// check and ve lai body chat khi click
					body.clearChat();
					for (ListMessChat listMessChat : listUserChat) {
						if (listMessChat.getUsername().equals(messInfo.getUserDes())) {

							chat_Title.setUserName(messInfo.getUserDes());
							chat_Title.setUserDes(messInfo.getUserDes());

							ListMessChat listMess = listUserChat.get(listUserChat.indexOf(listMessChat));

							for (MessInfo mess : listMess.getListMessInfo()) {

								if (username.equals(mess.getUserSource())) {
									body.addItemRight(mess.getMessContent(), mess.getTime(), mess);
								} else {
									body.addItemLeft(mess.getMessContent(), mess.getTime(), mess);
								}
							}

							break;
						}
					}

					body.revalidate();

				}
			});

			for (ListMessChat listMessChat : listUserChat) {
				if (listMessChat.getUsername().equals(messInfo.getUserDes())) {

					ListMessChat listUserTmp = listMessChat;
					listUserTmp.setMessLast(messInfo.getMessContent());
					listUserTmp.getListMessInfo().add(messInfo);

					listUserChat.remove(listUserChat.indexOf(listMessChat));
					listUserChat.add(0, listUserTmp);

					isUserTrue = true;

					break;
				}
			}

			if (!isUserTrue) {
				ListMessChat list = new ListMessChat(messInfo.getUserDes(), messInfo.getMessContent(), item_People);
				list.getListMessInfo().add(messInfo);
				listUserChat.add(0, list);
			}

			menu_Left.updateListChat(listUserChat);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean createFile(FileInfo fileInfo) {
		BufferedOutputStream bos = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		try {
			if (fileInfo != null) {
				File fileReceive = new File(
						fileInfo.getDestinationDirectory() + dtf.format(now) + fileInfo.getFilename());
				bos = new BufferedOutputStream(new FileOutputStream(fileReceive));
				// write file content
				bos.write(fileInfo.getDataBytes());
				bos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStream(bos);
		}
		return true;
	}

	public FileInfo getFileInfo(String sourceFilePath, String destinationDir) {
		FileInfo fileInfo = null;
		BufferedInputStream bis = null;
		try {
			File sourceFile = new File(sourceFilePath);
			bis = new BufferedInputStream(new FileInputStream(sourceFile));
			fileInfo = new FileInfo();
			byte[] fileBytes = new byte[(int) sourceFile.length()];
			// get file info
			bis.read(fileBytes, 0, fileBytes.length);
			fileInfo.setFilename(sourceFile.getName());
			fileInfo.setDataBytes(fileBytes);
			fileInfo.setDestinationDirectory(destinationDir);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			closeStream(bis);
		}

		return fileInfo;
	}

	public void closeSocket() {
		try {
			if (client != null) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeStream(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void closeStream(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean LoginOrRegister(String username, String password, int option) throws Exception {

		LoginRegisterMessInfo loginRegisterMessInfo = new LoginRegisterMessInfo(username, password, option, false);
		this.oos.writeObject(loginRegisterMessInfo);

		ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		LoginRegisterMessInfo loginRegisterMessInfoInPutServer = (LoginRegisterMessInfo) ois.readObject();

		if (loginRegisterMessInfoInPutServer.isStatus()) {

			this.username = username;
			setUpInitListMessChat(loginRegisterMessInfoInPutServer.getArraylistMessInfo());

			return true;
		} else {
			return false;
		}

	}

	public boolean ExitorLogout(ExitOrLogout exitOrLogout) throws Exception {
		this.oos.writeObject(exitOrLogout);
		return true;
	}

	public void setUpInitListMessChat(ArrayList<MessInfo> arrayMessInfos) {
		try {

			for (MessInfo messInfo : arrayMessInfos) {

				// check xem da co user trong list mess chat chưa
				boolean isUserTrue = false;
				Item_People item_People = null;

				// neu nguoi nhan va username giong nhau se lay nguoi gui
				if (messInfo.getUserDes().equals(this.username)) {
					item_People = new Item_People(messInfo.getUserSource());
				} else {
					item_People = new Item_People(messInfo.getUserDes());
				}

				item_People.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// check and ve lai body chat khi click
						body.clearChat();

						if (messInfo.getUserDes().equals(username)) {
							chat_Title.setUserName(messInfo.getUserSource());
							chat_Title.setUserDes(messInfo.getUserSource());

							for (ListMessChat listMessChat : listUserChat) {

								System.out.println("tcpclient: listmesschat " + listMessChat.getUsername());

								if (listMessChat.getUsername().equals(messInfo.getUserSource())) {

									ListMessChat listMess = listUserChat.get(listUserChat.indexOf(listMessChat));

									for (MessInfo mess : listMess.getListMessInfo()) {
										if (username.equals(mess.getUserSource())) {
											body.addItemRight(mess.getMessContent(), mess.getTime(), mess);
										} else {
											body.addItemLeft(mess.getMessContent(), mess.getTime(), mess);
										}
									}

									break;
								}
							}

						} else {
							chat_Title.setUserName(messInfo.getUserDes());
							chat_Title.setUserDes(messInfo.getUserDes());

							for (ListMessChat listMessChat : listUserChat) {

								System.out.println("tcpclient: listmesschat " + listMessChat.getUsername());

								if (listMessChat.getUsername().equals(messInfo.getUserDes())) {

									ListMessChat listMess = listUserChat.get(listUserChat.indexOf(listMessChat));

									for (MessInfo mess : listMess.getListMessInfo()) {
										if (username.equals(mess.getUserSource())) {
											body.addItemRight(mess.getMessContent(), mess.getTime(), mess);
										} else {
											body.addItemLeft(mess.getMessContent(), mess.getTime(), mess);
										}
									}

									break;
								}
							}
						}

						body.revalidate();

					}
				});
				// duyet va kiem tra xem co user trong listUserChat hay khong
				for (ListMessChat listMessChat : listUserChat) {

					// neu nguoi nhan va username giong nhau se lay nguoi gui
					if (messInfo.getUserDes().equals(this.username)) {
						if (listMessChat.getUsername().equals(messInfo.getUserSource())) {

							ListMessChat listMessChatTmp = listMessChat;
							listMessChatTmp.setMessLast(messInfo.getMessContent());
							listMessChatTmp.getListMessInfo().add(messInfo);

							listUserChat.remove(listUserChat.indexOf(listMessChat));
							listUserChat.add(0, listMessChatTmp);

							isUserTrue = true;
							break;
						}
					} else {
						if (listMessChat.getUsername().equals(messInfo.getUserDes())) {

							ListMessChat listMessChatTmp = listMessChat;
							listMessChatTmp.setMessLast(messInfo.getMessContent());
							listMessChatTmp.getListMessInfo().add(messInfo);

							listUserChat.remove(listUserChat.indexOf(listMessChat));
							listUserChat.add(0, listMessChatTmp);

							isUserTrue = true;
							break;
						}
					}

				}
				if (!isUserTrue) {
					// neu nguoi nhan va username giong nhau se lay nguoi gui
					if (messInfo.getUserDes().equals(username)) {
						ListMessChat list = new ListMessChat(messInfo.getUserSource(), messInfo.getMessContent(),
								item_People);
						list.getListMessInfo().add(messInfo);
						listUserChat.add(0, list);
					} else {
						ListMessChat list = new ListMessChat(messInfo.getUserDes(), messInfo.getMessContent(),
								item_People);
						list.getListMessInfo().add(messInfo);
						listUserChat.add(0, list);
					}

				}

				menu_Left.updateListChat(listUserChat);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}