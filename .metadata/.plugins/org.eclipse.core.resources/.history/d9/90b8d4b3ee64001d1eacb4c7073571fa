package server;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// class giao diện
public class ServerGuiView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textAreaResult;
    private JScrollPane scrollableTextArea;
    
    public ServerGuiView() {
        setTitle("Server");
        
        textAreaResult = new JTextArea();
        scrollableTextArea = new JScrollPane(textAreaResult); 
        scrollableTextArea.setBounds(10, 10, 734, 422);
        getContentPane().add(scrollableTextArea);
 
        getContentPane().setLayout(null);
        setSize(768, 479);
        setVisible(true);
        // thoát chương trình khi tắt window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
  
    public JTextArea getTextAreaResult() {
        return textAreaResult;
    }
    public void setTextAreaResult(JTextArea textAreaResult) {
        this.textAreaResult = textAreaResult;
    }
}