package ITC313A3T1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SwingUtilities;

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
	}
	
	private void setCreateSQL(){
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader("CREATE.sql"));
			String line, sql;
			
			while((line = buf.readLine()) != null){
				// up to here got to write sql into switchboard
				theView.setText("HI im the text", 1);
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
