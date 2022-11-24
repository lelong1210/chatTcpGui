package core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Service {
	private Connection conn;
	private PreparedStatement prst;
	private ArrayList<MessInfo> vData, vTitle;
	private ArrayList<UserInfo> vUser;

	public Service() {
		connectDB();
	}

	public void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectJava", "root", "");
			vData = new ArrayList<>();
			vTitle = new ArrayList<>();
			vUser = new ArrayList<>();
		} catch (Exception e) {

		}
	}

	public boolean login(String username, String password) {

		String Login = "SELECT * FROM user WHERE user.username = ? AND user.password = ? AND user.status = 1";
		try {
			prst = conn.prepareStatement(Login);
			prst.setString(1, username);
			prst.setString(2, password);
			ResultSet rst = prst.executeQuery();
			while (rst.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<UserInfo> getListUser() {
		try {
			if (vUser != null) {
				vUser.clear();
			}

			String getUser = "SELECT username,status FROM user WHERE 1";

			prst = conn.prepareStatement(getUser);
			ResultSet rst = prst.executeQuery();

			while (rst.next()) {
				UserInfo userInfo = new UserInfo(null, rst.getString(1), rst.getInt(2));
				vUser.add(userInfo);
			}
			return vUser;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vUser;
	}

	public ArrayList<MessInfo> getMessInfo(String username) {
		try {
			if (vData != null) {
				vData.clear();
				vTitle.clear();
			}

			String Login = "SELECT id, userSource, userDes, messContent, time, file FROM messInfo WHERE messInfo.userDes = ? OR messInfo.userSource = ?";

			prst = conn.prepareStatement(Login);
			prst.setString(1, username);
			prst.setString(2, username);
			ResultSet rst = prst.executeQuery();
			ResultSetMetaData rstm = rst.getMetaData();
			while (rst.next()) {
				MessInfo messInfo = new MessInfo(rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						null);
				vData.add(messInfo);

				if (rst.getString(6) != null) {
					FileInfo fileInfo = getFileInfo(rst.getString(6), "");
					messInfo.setFileInfo(fileInfo);
				}
			}

			return vData;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return vData;
	}

	public ArrayList<MessInfo> getMessInfoOfServer(String username) {

		try {
			if (vData != null) {
				vData.clear();
				vTitle.clear();
			}

			String sql = "SELECT id, userSource, userDes, messContent, time, file FROM messInfo WHERE " 
					+ "((messInfo.userSource='admin' AND messInfo.userDes =?) OR "
					+ "(messInfo.userSource=? AND messInfo.userDes ='admin'))";

			prst = conn.prepareStatement(sql);
			prst.setString(1, username);
			prst.setString(2, username);
			ResultSet rst = prst.executeQuery();
			ResultSetMetaData rstm = rst.getMetaData();
			while (rst.next()) {
				MessInfo messInfo = new MessInfo(rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5),
						null);
				vData.add(messInfo);

				if (rst.getString(6) != null) {
					FileInfo fileInfo = getFileInfo(rst.getString(6), "");
					messInfo.setFileInfo(fileInfo);
				}
			}

			return vData;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return vData;
	}

	public boolean insertMessInfo(String userSource, String userDes, String messContent, String time, String file) {
		String insertMessInfo = "INSERT INTO messInfo(userDes, userSource, messContent, time, file) VALUES (?,?,?,?,?)";
		try {
			prst = conn.prepareStatement(insertMessInfo);
			prst.setString(1, userDes);
			prst.setString(2, userSource);
			prst.setString(3, messContent);
			prst.setString(4, time);
			prst.setString(5, file);
			if (prst.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean Register(String username, String password) {

		String Register = "INSERT INTO user(username, password, status) VALUES (?,?,?)";
		try {
			prst = conn.prepareStatement(Register);
			prst.setString(1, username);
			prst.setString(2, password);
			prst.setInt(3, 1);
			if (prst.executeUpdate() != 0) {
				return true;
			}
			return false;
		} catch (Exception e) {

		}
		return false;
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

	public void closeStream(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
