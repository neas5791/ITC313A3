package zzITC313A3T100;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Switchboard extends JFrame{
	
	public Switchboard(){
		this.setTitle("Assignment 3 Task 1");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.add(new uiPanel());
		
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);	
	}
	
	private class uiPanel extends JPanel{
		private uiPanel(){
			

			
			// Setup the panels to be added to the TabbedPane
			tab1 = new Create();
			tab2 = new Insert();
			tab3 = new Search();
			tab4 = new Calculate();

			// add the panels
			tabs.addTab("Create", tab1);
			tabs.addTab("Insert", tab2);
			tabs.addTab("Search", tab3);
			tabs.addTab("Calculate", tab4);
			this.add(tabs);
		}
	}
	
	private class Create extends JPanel{
		private JTextArea statement = new JTextArea(10,30);
		private JScrollPane content = new JScrollPane(statement);
		private Create(){
			this.setLayout(new BorderLayout());

			JTextArea statement = new JTextArea();
			statement.setLineWrap(true);			
			statement.setPreferredSize(dimension);

			content.setPreferredSize(dimension);
			this.add(content, BorderLayout.CENTER);
			
			create = new JButton("Create");
			create.setActionCommand("CREATE");
			create.setPreferredSize(create.getPreferredSize());
			create.setToolTipText("Execute Sql statement");
			
			this.add(create, BorderLayout.SOUTH);
		}
	
		public void setText(String str){
			statement.setText(str);
			statement.setBackground(Color.lightGray);
			statement.setEditable(false);
			statement.setCaretPosition(0);
			this.revalidate();
		}
		
		public String getText() { return statement.getText(); }
	}
	
	
	public class Insert extends JPanel{
		private String sql;
		private JComboBox studentID = new JComboBox();
		private JTextField name = new JTextField();
		private JTextField assignment1 = new JTextField();
		private JTextField assignment2 = new JTextField();
		private JTextField assignment3 = new JTextField();
		private JTextField fin = new JTextField();
		private JTextArea statement = new JTextArea(10,30);
		private JScrollPane content = new JScrollPane(statement);
		
		private Insert(){
			JPanel fields = new JPanel(new GridLayout(0,6));
			// Loop through naming the labels
			for (String s: FIELDS){
				JLabel b = new JLabel(s, JLabel.CENTER);
//				b.setFont(new Font("Tahoma", Font.PLAIN, 12));
				fields.add(b);
			}
			
//			studentID = new JComboBox();
			studentID.setEditable(true);
			studentID.setPreferredSize(studentID.getPreferredSize());
			
			// Add textFields and combo
			fields.add(studentID);
			fields.add(name);
			fields.add(assignment1);
			fields.add(assignment2);
			fields.add(assignment3);
			fields.add(fin);
			
			
			insert = new JButton("Insert");
			insert.setPreferredSize(insert.getPreferredSize());

			fields.add(insert);
			
			statement.setLineWrap(true);			
			statement.setPreferredSize(dimension);

			content.setPreferredSize(dimension);
			
			// Set up text field for displaying SQL statements
			JPanel frame = new JPanel(new BorderLayout());			

			frame.add(content, BorderLayout.SOUTH);
			frame.add(fields, BorderLayout.CENTER);
			
			this.add(frame);
		}
		
		public void setText(String str){
			statement.setText(str);
			statement.setBackground(Color.lightGray);
			statement.setEditable(false);
			statement.setCaretPosition(0);
			this.revalidate();
		}
		
		public String getText() { return statement.getText(); }
	
		public void setStudentID(String list){
			String temp[] = list.split(",");
			//studentID.remove(studentID.getComponentCount()-1);

			for (int i = 0; i < temp.length; i++){
				studentID.addItem(temp[i]);
			}
			studentID.addItem("Add new");
		}
	
		public String getStudentID() 	{  return String.valueOf(studentID.getSelectedItem()); }
		public String getName() 		{  return name.getText();  }
		public String getAss1() 		{  return assignment1.getText();  }
		public String getAss2() 		{  return assignment2.getText();  }
		public String getAss3() 		{  return assignment3.getText();  }
		public String getFinal()		{  return fin.getText();  }	
	}
	
	
	private class Search extends JPanel{
		private String sql = "";

		private JComboBox studentID = new JComboBox();
		private JTextField name = new JTextField();
		private JTextField assignment1 = new JTextField();
		private JTextField assignment2 = new JTextField();
		private JTextField assignment3 = new JTextField();
		private JTextField fin = new JTextField();
		private JTextArea result = new JTextArea();
		
		private Search(){
			JPanel fields = new JPanel(new GridLayout(0,6));
			// Loop through naming the labels
			for (String s: FIELDS){
				JLabel b = new JLabel(s, JLabel.CENTER);
				b.setFont(new Font("Tahoma", Font.PLAIN, 12));
				fields.add(b);
			}
			

			studentID.setPreferredSize(studentID.getPreferredSize());
			
			// Add textFields and combo
			fields.add(studentID);
			fields.add(name);
			fields.add(assignment1);
			fields.add(assignment2);
			fields.add(assignment3);
			fields.add(fin);
			
			
			search = new JButton("Search");
			search.setPreferredSize(insert.getPreferredSize());

			fields.add(search);
			
			result = new JTextArea();
			result.setLineWrap(true);			
			
//			display = new JScrollPane(result);
//			display.setPreferredSize(sqlDimension);
			
			// Set up text field for displaying SQL statements
			JPanel frame = new JPanel(new BorderLayout());			

//			frame.add(display, BorderLayout.SOUTH);
			frame.add(fields, BorderLayout.CENTER);
			
			this.add(frame);
		}
	
		public void setSQL(String sql){
			this.sql.concat(sql); 
		}
	}
	
	private class Calculate extends JPanel{
		private JTextArea result = new JTextArea();
		private JComboBox studentID = new JComboBox();
		private Calculate(){
			this.setLayout(new BorderLayout());

			result = new JTextArea();
			result.setLineWrap(true);			
			
//			display = new JScrollPane(result);
//			display.setPreferredSize(sqlDimension);
//			this.add(display, BorderLayout.CENTER);

			JPanel ui = new JPanel(new FlowLayout());
			
			calculate = new JButton("Calculate");
			calculate .setActionCommand("CALCULATE");
			calculate .setPreferredSize(calculate .getPreferredSize());

			studentID.setPreferredSize(calculate.getPreferredSize());
			
			ui.add(studentID);
			ui.add(calculate);
			
			
			this.add(ui, BorderLayout.SOUTH);
		}

	}

	public void createListener (ActionListener createStuff){
		create.addActionListener(createStuff);
	}
	
	public void insertListener (ActionListener insertStuff){
		insert.addActionListener(insertStuff);
	}
	
	public void searchListener (ActionListener searchStuff){
		search.addActionListener(searchStuff);
	}
	
	public void calculateListener (ActionListener calculateStuff){
		calculate.addActionListener(calculateStuff);
	}
	public void tabChangeListener (ChangeListener tabChanged){
		tabs.addChangeListener(tabChanged);
	}
	
	public void setText(String line, String tab){
		switch(tab){
		case "CREATE":
			// enter test into the Create result textPane tab1
			tab1.setText(line);
			return;
		case "INSERT":
		case "SEARCH":
		case "CALCULATE":
		case "STUDENT":
			tab2.setStudentID(line);
		default:
			return;
		}	
	}
	
	public String getText(String tab){
		switch (tab){
		case "CREATE":
			return tab1.getText();
		default:
			return null;
		}
	}
	
	
	/* ************************************************************************************ */
	/* BELOW HERE IS THE CLASS ATTRIBUTES ************************************************* */
	private JTabbedPane tabs = new JTabbedPane();	
	private String[] FIELDS = {"Student ID", "Name", "Assignment 1", "Assignment 2", "Assignment 3", "Final"};
	
	/* GUI Controls */
	@SuppressWarnings("unused")
	private JButton create;
	@SuppressWarnings("unused")
	private JButton insert;
	@SuppressWarnings("unused")
	private JButton search;
	@SuppressWarnings("unused")
	private JButton calculate;
//	private JComboBox<Integer> studentID;
	@SuppressWarnings("unused")
	private JLabel feedback;
	
	private Dimension dimension= new Dimension(800,600);

	private Create tab1;
	private Insert tab2;
	private Search tab3;
	private Calculate tab4;
}
