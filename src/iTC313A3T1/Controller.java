package iTC313A3T1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Controller {
	private JDBCConnection theModel;
	private View theView;
	private final String REPORT_LIST = "SELECT * FROM Student_marks_ITC000";

	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Controller theController = new Controller(new JDBCConnection(), new View());
			}
		});
	}
	
	public Controller(JDBCConnection theModel, View theView){
		this.theModel = theModel;
		this.theView = theView;
		
		init();
		theView.buttonListener(new Click());
		theView.checkListener(new Check());
		theView.textVerfier(new TextVerifier());
	}
	
	private void init(){
		
		/* Displays a JOPTIONPANE DIALOGUE    */
		String message = "Would you like to create a new database?\nIf you say NO the "
						+ "database can be RE-CREATED anytime\nby pressing the Re-Create Button";
		
		
		int result = JOptionPane.showConfirmDialog(null, message, "Do you want to Create a new Database?",
		        JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_OPTION) {
			readSQLFile("CREATE.sql");
		} 
		else {
			System.out.println(result);
		}

		// SETS WINDOW THEME TO SEARCH
		theView.setSearchTheme();
		// POPULATES THE STUDENTID COMBO WITH INFO FROM MYSQL DATABASE
		theView.populateStudentID(getStudents());
		
		// CONSOLE OUTPUT
		theView.appendLog("\nThe Database contains the following information\n");
		
		// SHOWS THE CURRENT STATE OF THE DATABASE IN THE CONSOLE WINDOW
		getReport(REPORT_LIST);
		
		theView.setConnectionIcon(true);
	}

	private ArrayList<String> getStudents() {
		String sql = "SELECT StudentID FROM Student_marks_ITC000";
		ResultSet r = theModel.query(sql);
		ArrayList<String> f = new ArrayList<String>();
		
		try {
			while(r.next()){
				f.add(r.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	private void getReport(String sql){

//		theView.appendLog("\n\n");
//		String sql = "SELECT * FROM Student_marks_ITC000";
		String header = "";
		ResultSet r = theModel.query(sql);
		ResultSetMetaData rsMetaData;

		try {
			// GET THE QUERY HEADERS
			rsMetaData = r.getMetaData();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++){
				if (header.isEmpty ())
					header = String.format("%-12s\t",rsMetaData.getColumnName(i));
				else
					header = header + String.format("%-12s\t",rsMetaData.getColumnName(i));
			}
			theView.appendLog(header);
			System.out.println();
			
			// GET RESULTS
			String row ="";
			while(r.next()){
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++){
					if (i == 1)
						row = String.format("%-12s\t", r.getObject(i));
					else
						row = row + String.format("%-12s\t", r.getObject(i));
					
					System.out.printf("%-12s\t", r.getObject(i));
				}
				theView.appendLog(row);
				
				System.out.println();
//				theView.appendLog(r.getString(1) + "\t" + r.getString(2)+ "\t" + r.getString(3)+ "\t" + r.getString(4)+ "\t" + r.getString(5)+ "\t" + r.getString(6));
			}
			
			// if no results found send meesage to console
			if(!r.first())
				theView.appendLog("NO RESULTS FOUND");
			
			theView.appendLog("\n");
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
	}
	
	private class Click implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand() + " has been clicked");
//			if (!theModel.isConnected()){
//				theModel.initiateConnection();
//				theView.setConnectionIcon(theModel.isConnected());
//			}
			
			int result;
			switch (e.getActionCommand()){
			case "CREATE":
				result = JOptionPane.showConfirmDialog(null, "This will run a RE-CREATE script\nAll data will be lost.\nAre you sure you wish to continue?", "WARNING",
				        JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					readSQLFile("CREATE.sql");
				} 
				else {
//					String t = theView.getQueryText();
					theView.appendLog("No Changes made");
					System.out.println(result);
				}
				theView.setConnectionIcon(theModel.isConnected());
				getReport(REPORT_LIST);
				break;
			case "CONNECT":
				if (theModel.isConnected()) {
					theModel.disconnect();
				}
				else {
					theModel.initiateConnection();
				}
				
				theView.appendLog(theModel.getMessage() ); //+ "\nClick the CREATE button above to re-create the database."
				theView.setConnectionIcon(theModel.isConnected());
				break;
			case "INSERT":
				checkConnection();
				insertRecord();
				break;
			case "CALCULATE":
				checkConnection();
				calculateResults();
				break;
			case "SEARCH":
				checkConnection();
				System.out.println(theView.getStudentID());
				generateSearchResult();
				break;
			case "CLEAR":
				result = JOptionPane.showConfirmDialog(null, "All text in query box will be lost.\nAre you sure?", "WARNING",
				        JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION)
					theView.clearLog();

				break;

			default:
				return;
			}
		}	
	}
	
	private void checkConnection(){
		if (!theModel.isConnected()){
			theModel.initiateConnection();
			theView.setConnectionIcon(theModel.isConnected());
		}
	}
	private class Check implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(((JRadioButton) e.getSource()).getActionCommand());
			
			switch  (((JRadioButton) e.getSource()).getActionCommand()){
			case "INSERT":
				theView.setInsertTheme();
				break;
			case "SEARCH":
				theView.setSearchTheme();
				break;
			case "CALCULATE":
				theView.setFinalTheme();
				break;
			default:
				return;
			}
		}
		
	}
	
	private class TextVerifier extends InputVerifier{

		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			
			if(text.isEmpty())
				return true;
//			if(!isInteger(text)){
//				((JTextField) input).selectAll();
//				theView.appendLog("This field only takes integer values");
//			}
//			
//			boolean test = text.matches("[0-9]+");

			if (text.matches("^[0-9]+")){
				if (Integer.valueOf(text) <= 100)
					return true;
			}
			
			((JTextField) input).selectAll();
			theView.appendLog("This field only accepts integer values between 0 and 100");
			return false;
		}
	}

	private void readSQLFile(String file){
		try {
			String[] query;
			String line;
			String SQL = "";
						
			BufferedReader b = new BufferedReader(new FileReader(file));
			
			while((line = b.readLine()) != null){
				theView.appendLog(line);
				if (SQL.isEmpty()){
					SQL = line;
				}
				else{
					SQL = SQL + line;
				}
			}
			query = SQL.split(";");

			String temp = "";
			for (int i = 0; i < query.length; i++){
				theModel.insertData(query[i]);
				if (temp.isEmpty())
					temp = theModel.getMessage();
				else
					temp = temp + "\n" + theModel.getMessage();
			}
			theView.appendLog(temp);
			theView.setConnectionIcon(true);
			b.close();
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private void readTextFile(String file){
//		try {
//			String line;
//			String text="";
//						
//			BufferedReader b = new BufferedReader(new FileReader(file));
//			
//			while((line = b.readLine()) != null){
//				theView.appendLog(line);
//			}
//
//			b.close();
//		}
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}
	
	private void generateSearchResult(){
		
		
		String[] field = {"StudentID", "", "Assignment1", "", "Assignment2", "", "Assignment3", "", "Final", "", "Name", ""};
				
		field[1] = theView.getStudentID();
		field[3] = theView.getAssign1();
		field[5] = theView.getAssign2();
		field[7] = theView.getAssign3();
		field[9] =  theView.getFinal();
		field[11] = theView.getName();
		String sql = "SELECT * FROM Student_marks_ITC000 ";
		String search="";
		
		// only searches array up to Final
		for (int i = 1; i < field.length-2; i+=2){
			
			if(!field[i].isEmpty()){
				if (search.isEmpty())
					if (field[i].compareToIgnoreCase("NULL") == 0 )
						search = "WHERE "+ field[i-1] + " IS NULL";
					else
						search = "WHERE " + field[i-1] + " = " + field[i];
				else
					if (field[i] == "NULL")
						search = search + " AND " + field[i-1] + " IS NULL";
					else
						search = search + " AND " + field[i-1] + " = " + field[i];
			}
		}

		if (!field[11].isEmpty()){
			if (search.isEmpty())
				search = "WHERE " + field[10] + " LIKE '" + field[11] + "'";
			else
				search = search + " AND " + field[10] + " LIKE '" + field[11] + "'";
		}

		System.out.println(sql + search);
		theView.appendLog(sql + search);
		getReport(sql + search);	
	}
		
	private void calculateResults(){
		String student = theView.getStudentID();
		String sql = "SELECT StudentID, Name, (Assignment1*0.1+Assignment2*0.2+Assignment3*0.2+Final*0.5) as 'Final Score' FROM Student_Marks_ITC000";
		
		if(!student.isEmpty()){
			String where = " WHERE StudentID = " + student;
			sql = sql.concat(where);
		}
		
		theView.appendLog(sql);
		getReport(sql);
		
		
	}
	
	private void insertRecord(){
		String[] field = {"Assignment1", "", "Assignment2", "", "Assignment3", "", "Final", "", "Name", ""};
		
		field[1] = theView.getAssign1();
		field[3] = theView.getAssign2();
		field[5] = theView.getAssign3();
		field[7] =  theView.getFinal();
		field[9] = theView.getName();

		
		// Checks for empty values
		if ((field[9] = theView.getName()).isEmpty()){
			theView.getTextFocus("NAME");
			theView.appendLog("Please enter all fileds with data");
			return;
		}
		if ((field[1] = theView.getAssign1()).isEmpty()){
			theView.getTextFocus("ASSIGN1");
			theView.appendLog("Please enter all fileds with data");
			return;
		}
		if ((field[3] = theView.getAssign2()).isEmpty()){
			theView.getTextFocus("ASSIGN2");
			theView.appendLog("Please enter all fileds with data");
			return;	
		}
		if ((field[5] = theView.getAssign3()).isEmpty()){
			theView.getTextFocus("ASSIGN3");
			theView.appendLog("Please enter all fileds with data");
			return;
		}
		if ((field[7] = theView.getFinal()).isEmpty()){
			theView.getTextFocus("FINAL");
			theView.appendLog("Please enter all fileds with data");
			return;
		}
		
		String sql = "INSERT INTO Student_marks_ITC000 (Assignment1, Assignment2, Assignment3, Final, Name) "
						+ "VALUES (" + field[1] + "," + field[3]+"," + field[5] + "," + field[7] + ", '" + field[9] + "')";
		
		if(!theModel.insertData(sql)){
			System.out.println("Something went wrong please check your data");
			theView.appendLog("Something went wrong please check your data");
			return;
		}
		
		theView.appendLog(sql);
		
		sql = "SELECT * FROM Student_Marks_ITC000 WHERE StudentID = ( SELECT MAX(StudentID) FROM Student_Marks_ITC000 )";
		getReport(sql);
		
		theView.populateStudentID(getStudents());
	}
}
