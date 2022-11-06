package server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

import core.FileInfo;
import core.MessInfo;
import core.UserInfo;

// class này là nhân của server
public class TCPServer extends Thread {
	// khai báo biến serversocket
	private ServerSocket serverSocket;
	// cổng 9900
	private int port = 9900;
	// một textArea dùng để cập nhật 
	private JTextArea textAreaLog;
	// mảng các User 
	private ArrayList<UserInfo> arrayListUser = new ArrayList<UserInfo>();
	// constructor với tham số truyền vào là textArea
	public TCPServer(JTextArea textAreaLog) {
		this.textAreaLog = textAreaLog;
	}
	// function dùng để mở cửa server
	public void open() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("server is open on port " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// function run là một function sẽ thực hiện đa luồng khi ta kế thừa Thread
	public void run() {
		try {
			while (true) {
				// đồng ý cho client kết nối và khởi tạo một socket 
				// ( khi một client kết nối tới server thì sẽ khởi tạo 1 socket 
				//   nghĩ đơn giản thì đây là 1 chiếc xe giành riêng cho server và client đó )
				// nên vì vậy phải có Thread		
				Socket client = serverSocket.accept();
				// client gửi thông tin tới server
				DataInputStream inFromClient = new DataInputStream(client.getInputStream());
				String userNameLogin = inFromClient.readUTF();
				// create userInfo (login)
				UserInfo userInfo = new UserInfo(client, userNameLogin);
				arrayListUser.add(userInfo);
				// get mess info
				Thread threadInFromClient = new Thread() {
					// class ObjectInputStream và ObjectOutputStream được dùng để gửi class
					private ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
					private ObjectOutputStream oos = null;

					@Override
					public void run() {
						try {
							// get input from client
							while (true) {
								MessInfo messInfo = (MessInfo) ois.readObject();
								textAreaLog.append("\n Server send from " + userInfo.getUsername() + " to "
										+ messInfo.getUserDes() + " with content: " + messInfo.getMessContent());

								// check send file
								if (messInfo.getFileInfo() != null) {
									// khởi tạo file
									FileInfo fileInfo = (FileInfo) messInfo.getFileInfo();
									// tạo file
									createFile(fileInfo);
									// định nghĩa nơi để file
									fileInfo.setDestinationDirectory("/media/lql/HDD/Code/Code_Java/Code_Chat_GUI/Client/");
									// định nghĩa nơi lấy file
									fileInfo.setSourceDirectory(fileInfo.getDestinationDirectory()+fileInfo.getFilename());
								} 
								
								// find users and send mess
								for (UserInfo userInfo : arrayListUser) {
									if (userInfo.getUsername().equals(messInfo.getUserDes())) {
										Socket socketOfuserSend = (Socket) userInfo.getSocket();
										oos = new ObjectOutputStream(socketOfuserSend.getOutputStream());
										sendMess(oos, messInfo);
										oos = null;
										break;
									}
								}

							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				};
				threadInFromClient.start();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void sendMess(ObjectOutputStream oos, MessInfo messInfo) {
		try {
			Thread threadSend = new Thread() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						oos.writeObject(messInfo);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			threadSend.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void closeSocket(Socket socket) {
		try {
			if (socket != null) {
				socket.close();
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