package core;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Service {
	private Connection conn;
	private PreparedStatement prst;
	private ArrayList<String> vData,vTitle;
	
	public Service() {
		connectDB();
	}
	
	public void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectJava", "root", "");
			vData = new ArrayList<>();
			vTitle = new ArrayList<>();
		} catch (Exception e) {

		}
	}
	public ArrayList<String> login(String username,String password) {
		
		if(vData != null) {
			vData.clear();
			vTitle.clear();
		}

		
		String Login = "SELECT * FROM user WHERE user.username = ? AND user.password = ?";
		try {
			prst = conn.prepareStatement(Login);
			prst.setString(1, username);
			prst.setString(2, password);
			ResultSet rst = prst.executeQuery();
			ResultSetMetaData rstm = rst.getMetaData();
			int col = rstm.getColumnCount();
			for (int i = 1; i <= col; i++) {
				vTitle.add(rstm.getColumnName(i).toString());
			}
			while (rst.next()) {
				ArrayList<String> row = new ArrayList<>();
				for (int i = 1; i <= col; i++) {
					row.add(rst.getString(i));
				}
				vData.addAll(row);
			}
			System.out.println(vData.size());
			return vData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vData;
	}
 	public ArrayList<String> getMessInfo(String username){
 		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
 		return vData;
 	}
 	public boolean insertMessInfo(String userSource,String userDes,String messContent,String time, String file) {
 		String insertMessInfo = "INSERT INTO messInfo(userDes, userSource, messContent, time, file) VALUES (?,?,?,?,?)";
 		try {
			prst = conn.prepareStatement(insertMessInfo);
			prst.setString(1, userDes);
			prst.setString(2, userSource);
			prst.setString(3, messContent);
			prst.setString(4, time);
			prst.setString(5, file);
			if(prst.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 		return false;
 	}
	public boolean Register() {
		return false;
	}
	public static void main(String[] args) {
		Service service = new Service();
		service.login("lql","12345678");
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		service.insertMessInfo("1", "2", "3", "4", dtf.format(now));
	}
}