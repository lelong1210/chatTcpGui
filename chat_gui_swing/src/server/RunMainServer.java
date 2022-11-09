package server;

//class này là dùng để khởi động
public class RunMainServer {
 public static void main(String[] args) {
	 ServerGuiView view = new ServerGuiView();
     new Controller(view);
 }
}
