package server;

import java.io.IOException;

//class này là dùng để khởi động
public class RunMainServer {
 public static void main(String[] args) throws IOException {
	 ServerGuiView view = new ServerGuiView();
     new Controller(view);
 }
}
