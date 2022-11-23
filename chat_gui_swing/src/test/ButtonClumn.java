package test;

 import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

//OUR MAIN CLASS
public class ButtonClumn extends JFrame {

  public ButtonClumn(){
  //FORM TITLE
  super("Button Column Example");

  //DATA FOR OUR TABLE
  Object[][] data=
    {
      {"1","Man Utd",new Integer(2013),"21"},
      {"2","Man City",new Integer(2014),"3"},
      {"3","Chelsea",new Integer(2015),"7"},
      {"4","Arsenal",new Integer(1999),"10"},
      {"5","Liverpool",new Integer(1990),"19"},
      {"6","Everton",new Integer(1974),"1"},
    };

  //COLUMN HEADERS
  String columnHeaders[]={"Position","Team","Last Year Won","Trophies"};
  //CREATE OUR TABLE AND SET HEADER
  JTable table=new JTable(data,columnHeaders);



  //SCROLLPANE,SET SZE,SET CLOSE OPERATION
  JScrollPane pane=new JScrollPane(table);
  getContentPane().add(pane);
  setSize(450,100);

  setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    ButtonClumn bc=new ButtonClumn();
    bc.setVisible(true);
  }

}

//BUTTON RENDERER CLASS


//BUTTON EDITOR CLASS

