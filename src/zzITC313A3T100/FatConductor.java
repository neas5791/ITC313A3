package zzITC313A3T100;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FatConductor {
	private JDBCConnection theModel;
	private Switchboard theView;
	
	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				FatConductor theController = new FatConductor(new JDBCConnection(), new Switchboard());
			}
		});
	}
	
	private FatConductor(JDBCConnection theModel, Switchboard theView){
		this.theModel = theModel;
		this.theView = theView;
		
		init();
	}

	private void init(){
//		theView.setText("HI im the text", 1);
		setCreateSQL();
		
		theView.createListener(new Click());
		theView.tabChangeListener(new Tab());
	}
	
	private void setCreateSQL(){
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader("CREATE.sql"));
			String line;
			String sql = "";
			
			while((line = buf.readLine()) != null){
				if (sql == "")
					sql = line ;
				else 
					sql = sql + "\n" + line ;
			}
			
			theView.setText(sql, "CREATE");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setStudentID(){
		try {
			BufferedReader buf = new BufferedReader(new FileReader("GET_STUDENTS.sql"));
			String line;
			String sql = "";
			
			while((line = buf.readLine()) != null){
				if (sql == "")
					sql = line ;
				else 
					sql = sql + "\n" + line ;
			}
			
			String[] temp = sql.split(";");
			ResultSet r = null;
			for (int i = 0 ; i < temp.length; i++)
				r = theModel.query(sql);
			line = "";
			while (r.next()){
				if (line == "")
					line = r.getString(1);
				else
					line = line + "," + r.getString(1);
			}

			theView.setText(line, "STUDENT");			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private String runCreate(){
		String temp;
		String t = theView.getText("CREATE");
		String[] s = t.split(";");
		
		for (int i = 0; i < s.length; i++){
			if ((temp = theModel.insertData(s[i]))!= null)
				return temp;
		}
		return "YAY! THE TABLE WAS SUCCESSFULLY CREATED";
	}

	private class Click implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()){
			case "CREATE":
				theView.setText(runCreate(), "CREATE");
			case "INSERT":
				
			default:
				return;
			}
			
			
		}	
	}
	
	private void insertButton(){
		String[] temp;
		
//		if (theView.get)
		
	}
	
private class Tab implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println(((JTabbedPane) e.getSource()).getSelectedIndex());
		switch (((JTabbedPane) e.getSource()).getSelectedIndex()){
		case 0:
		case 1:
			setStudentID();
		case 2:
		case 3:
		default:
			return;
		
		}
	}
	
}

}

