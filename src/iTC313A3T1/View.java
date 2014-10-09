package iTC313A3T1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame{

//	public static void main (String[] args){
//		View v = new View();
//	}
	
	private JTextArea console = new JTextArea("Welcome to the MySQL Database Connection\nThe database is plugged in and ready for use.\n");
	private JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private JButton recreate = new JButton("Re-Create");
	private JRadioButton in = new JRadioButton("Insert");
	private JRadioButton se = new JRadioButton("Search",true);
	private JRadioButton ca = new JRadioButton("Calculate");
	

	private JButton clear = new JButton("Clear");
	private JButton execute = new JButton();

	private JComboBox<String> studentID = new JComboBox<String>();
	private JTextField name = new JTextField();
	private JTextField assignment1 = new JTextField();
	private JTextField assignment2 = new JTextField();
	private JTextField assignment3 = new JTextField();
	private JTextField exam = new JTextField();
	
	private JButton connection = new JButton(new ImageIcon("database-icon.png"));
	
	public View(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Assignment 3 Task 1 - MYSQL Database connection");
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel ui = new JPanel(new GridLayout(2,0));
		
		JPanel textfields = new JPanel(new GridLayout(0,6));
		JPanel buttons = new JPanel(new GridLayout(0,6));
		JPanel functions = new JPanel(new GridLayout(0,7));
		scroll.setPreferredSize(new Dimension(600,400));
		ButtonGroup bg = new ButtonGroup();
		
		in.setActionCommand("INSERT");
		in.setToolTipText("Insert new Student reults");
		se.setActionCommand("SEARCH");
		se.setToolTipText("Search Student result database");
		ca.setActionCommand("CALCULATE");
		ca.setToolTipText("Calculate final marks based on database results");
		
		bg.add(in);
		bg.add(se);
		bg.add(ca);
		
		functions.add(new JLabel("Choose your query type:"));
		functions.add(new JLabel());

		functions.add(in);
		functions.add(new JLabel());
		functions.add(se);
		functions.add(new JLabel());
		functions.add(ca);
		
		textfields.add(new JLabel("Student ID", JLabel.CENTER));
		textfields.add(new JLabel("Name", JLabel.CENTER));
		textfields.add(new JLabel("Assignment 1", JLabel.CENTER));
		textfields.add(new JLabel("Assignment 2", JLabel.CENTER));
		textfields.add(new JLabel("Assignment 3", JLabel.CENTER));
		textfields.add(new JLabel("Final", JLabel.CENTER));
		
		name.setActionCommand("NAME");
		assignment1.setActionCommand("ASSIGN1");
		assignment2.setActionCommand("ASSIGN2");
		assignment3.setActionCommand("ASSIGN3");
		exam.setActionCommand("FINAL");
				
		textfields.add(studentID);
		textfields.add(name);
		textfields.add(assignment1);
		textfields.add(assignment2);
		textfields.add(assignment3);
		textfields.add(exam);

		connection.setActionCommand("CONNECT");
		connection.setBorderPainted(false);
		connection.setContentAreaFilled(false);
		connection.setFocusPainted(false);
		connection.setOpaque(false);
		connection.setToolTipText("Click to CONNECT or DISCONNECT Database");
		
		clear.setActionCommand("CLEAR");		
		clear.setToolTipText("Clear the query window");
		execute.setEnabled(false);
		
		recreate.setActionCommand("CREATE");
		recreate.setToolTipText("Click me if you want"
							+ " to re-create the database");

		buttons.add(clear);
		buttons.add(new JLabel());
		buttons.add(execute);
		buttons.add(new JLabel());
		buttons.add(recreate);
		buttons.add(connection);
		
		ui.add(textfields);
		ui.add(buttons);
		
		panel.add(functions, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.SOUTH);
		panel.add(ui, BorderLayout.CENTER);
		
		this.add(panel);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
//	public String getQueryText() {  return query.getText();  }
	
	public void appendLog(String message){
		console.append("\n");
		console.append(message);
		console.setCaretPosition(console.getDocument().getLength());
		console.revalidate();
	}
	
	public void buttonListener(ActionListener click){
		recreate.addActionListener(click);
		clear.addActionListener(click);
		connection.addActionListener(click);
		execute.addActionListener(click);
	}
	public void checkListener(ActionListener check){
		in.addActionListener(check);
		se.addActionListener(check);
		ca.addActionListener(check);
	}
	public void textVerfier(InputVerifier input){
		assignment1.setInputVerifier(input);
		assignment2.setInputVerifier(input);
		assignment3.setInputVerifier(input);
		exam.setInputVerifier(input);
	}
	
	public void setConnectionIcon(boolean isConnected){
		if(isConnected){
			connection.setIcon(new ImageIcon("database-accept-icon.png"));
			connection.setToolTipText("The database is connected.");
		}
		else{
			connection.setIcon(new ImageIcon("database-icon.png"));
			connection.setToolTipText("The database is disconnected.");
		}
	}

	public void setInsertTheme(){
		studentID.setEditable(false);
		studentID.setSelectedIndex(0);
		name.setEditable(true);
		assignment1.setEditable(true);
		assignment2.setEditable(true);
		assignment3.setEditable(true);
		exam.setEditable(true);
		execute.setEnabled(true);
		execute.setText("Insert");
		execute.setToolTipText("Insert a new record");
		execute.setActionCommand("INSERT");
	}
	public void setSearchTheme(){
		studentID.setEditable(true);
		studentID.setToolTipText("To see all available results leave this field blank");
		name.setEditable(true);
		name.setText("");
		name.setToolTipText("Enter the students name");
		assignment1.setEditable(true);
		assignment1.setText("");
		assignment1.setToolTipText("Enter the reuslt value");
		assignment2.setEditable(true);
		assignment2.setText("");
		assignment2.setToolTipText("Enter the reuslt value");
		assignment3.setEditable(true);
		assignment3.setText("");
		assignment3.setToolTipText("Enter the reuslt value");
		exam.setEditable(true);
		exam.setText("");
		exam.setToolTipText("Enter the reuslt value");
		execute.setEnabled(true);
		execute.setText("Search");
		execute.setToolTipText("Get reults of search criteria");
		execute.setActionCommand("SEARCH");
		
		if(studentID.getItemCount()>0)
			studentID.setSelectedIndex(0);
	}
	public void setFinalTheme(){
		studentID.setEditable(true);
		studentID.setToolTipText("To see all available results leave this field blank");
		name.setEditable(false);
		name.setText("");
		assignment1.setEditable(false);
		assignment1.setText("");
		assignment2.setEditable(false);
		assignment2.setText("");
		assignment3.setEditable(false);
		assignment3.setText("");
		exam.setEditable(false);
		execute.setEnabled(true);
		execute.setActionCommand("CALCULATE");
		execute.setText("Calculate");
		execute.setToolTipText("Get reults of search criteria");

		
		if(studentID.getItemCount()>0)
			studentID.setSelectedIndex(0);
		
	}
	
	public String getStudentID(){
		String selected = (String) studentID.getSelectedItem();
		
		if (selected.isEmpty())
			return "";
		
		return selected;  
		}
	public String getName()	  {  return name.getText();  }
	public String getAssign1(){  return assignment1.getText();  }
	public String getAssign2(){  return assignment2.getText();  }
	public String getAssign3(){  return assignment3.getText();  }
	public String getFinal()  {  return exam.getText();  	   }
	
	public void clearLog(){
		console.setText("");
	}
	
	public void populateStudentID(ArrayList<String> students){
		studentID.removeAllItems();
		
		studentID.addItem("");
		
		for (int i = 0; i < students.size(); i++)
			studentID.addItem(students.get(i));
	}

	public void getTextFocus(String actionCommand){
		if(actionCommand.equalsIgnoreCase("NAME"))
			name.requestFocusInWindow();
		else if(actionCommand.equalsIgnoreCase("ASSIGN1"))
			assignment1.requestFocusInWindow();
		else if(actionCommand.equalsIgnoreCase("ASSIGN2"))
			assignment2.requestFocusInWindow();
		else if(actionCommand.equalsIgnoreCase("ASSIGN3"))
			assignment3.requestFocusInWindow();
		else if(actionCommand.equalsIgnoreCase("FINAL"))
			exam.requestFocusInWindow();
	}

    /**
     * Allows for messages to be past to be display message box
     * @param errorMessage	the String message for the message box
     */
    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
