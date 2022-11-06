package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.Chat_Body;
import chat.Chat_Title;
import core.FileInfo;
import core.ListMessChat;
import core.MessInfo;
import guiCore.Item_People;
import guiCore.Menu_Left;

public class TCPClient {
	// create Socket object
	private Socket client;
	private String host;
	private int port;
	private DataOutputStream outToServer;
	private Chat_Body body;
	private Menu_Left menu_Left;
	private Chat_Title chat_Title;
	private ObjectOutputStream oos = null;
	private String username;
	private ArrayList<ListMessChat> listUserChat = new ArrayList<ListMessChat>();

	public TCPClient(String host, int port, Chat_Body body, Menu_Left menu_Left, Chat_Title chat_Title,
			String username) {
		this.host = host;
		this.port = port;
		this.body = body;
		this.menu_Left = menu_Left;
		this.chat_Title = chat_Title;
		this.username = username;
	}

	public void connectServer() {
		try {
			this.client = new Socket(host, port);
			this.outToServer = new DataOutputStream(client.getOutputStream());
			System.out.println("connected to server.\n");
			this.outToServer.writeUTF(this.username);
			this.oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void getMess() {
		try {
			boolean isUserTrue = false;
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			MessInfo messInfo = (MessInfo) ois.readObject();
			
			System.out.println("get file"+messInfo.getFileInfo());
			
			if (messInfo.getFileInfo() != null) {
				createFile(messInfo.getFileInfo());
			}

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
//							textAreaLog.setText(listMess.getAllMess());

							for (MessInfo mess : listMess.getListMessInfo()) {
								if (username.equals(mess.getUserSource())) {
									body.addItemRight(mess.getMessContent(), "");
								} else {
									body.addItemLeft(mess.getMessContent(), "");
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
								body.addItemRight(mess.getMessContent(), "");
							} else {
								body.addItemLeft(mess.getMessContent(), "");
							}
						}
						body.revalidate();
						break;
					}
				}
			}

			menu_Left.updateListChat(listUserChat);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMess(MessInfo messInfo) {
		try {
			
			this.oos.writeObject(messInfo);
			
			System.out.println("send to: "+messInfo.getUserDes());

			body.addItemRight(messInfo.getMessContent(), "");

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
									body.addItemRight(mess.getMessContent(), "");
								} else {
									body.addItemLeft(mess.getMessContent(), "");
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

	private boolean createFile(FileInfo fileInfo) {
		BufferedOutputStream bos = null;

		try {
			if (fileInfo != null) {
				File fileReceive = new File(fileInfo.getDestinationDirectory() + fileInfo.getFilename());
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
		
		System.out.println("file get --> "+fileInfo.getFilename());
		
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
}