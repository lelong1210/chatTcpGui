package core;


import java.util.ArrayList;
import javax.swing.JLabel;
import guiCore.*;


public class ListMessChat {
	
	private String username;
	private String messLast;
	private Item_People item_People;
	public Item_People getItem_People() {
		return item_People;
	}
	public void setItem_People(Item_People item_People) {
		this.item_People = item_People;
	}

	private ArrayList<MessInfo> listMessInfo = new ArrayList<>(); //String allMess;
	

	public ArrayList<MessInfo> getListMessInfo() {
		return listMessInfo;
	}
	public void setListMessInfo(ArrayList<MessInfo> listMessInfo) {
		this.listMessInfo = listMessInfo;
	}

	private JLabel labelOfUsername;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessLast() {
		return messLast;
	}
	public void setMessLast(String messLast) {
		this.messLast = messLast;
	}
	public JLabel getLabelOfUsername() {
		return labelOfUsername;
	}
	public void setLabelOfUsername(JLabel labelOfUsername) {
		this.labelOfUsername = labelOfUsername;
	}
	
	public ListMessChat(String username,String messLast,Item_People item_People) {
		this.username = username;
		this.messLast = messLast;
		this.item_People = item_People;
	}
}
