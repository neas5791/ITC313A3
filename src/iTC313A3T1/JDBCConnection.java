package iTC313A3T1;
import java.sql.*;

public class JDBCConnection {
	private Connection connection = null;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost/ST11187033";
	private final String user = "root";
	private final String password = "abc123";
	private Statement statement;
	private String message;
	
	public void initiateConnection(){
	    try {
	        if(this.connection == null || this.connection.isClosed()) {
	            try {
	                Class.forName (driver).newInstance ();
	                this.connection = DriverManager.getConnection(url, user, password);
	                System.out.println("database connection established.");
	                setMessage("Connection established.");
	            }
	            catch(SQLException sqe) {
	                throw new SQLException (sqe);
	            }
	            catch (InstantiationException e) {
	            	setMessage(e.getMessage());
	                e.printStackTrace();
	            } 
	            catch (IllegalAccessException e) {
	            	setMessage(e.getMessage());
	                e.printStackTrace();
	            } 
	            catch (ClassNotFoundException e) {
	            	setMessage(e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	    catch(SQLException sqe) {
	        setMessage(sqe.getMessage());
            sqe.printStackTrace();
	    }
	}
	
	public void disconnect(){
		if (connection != null) {
            try {
				connection.close ();
	            System.out.println ("Database connection terminated");
	            setMessage("Connection disconnected.");
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean insertData(String sql) {
	    PreparedStatement s = null;
	    try {
	    	
	        if(connection == null || connection.isClosed()) 
	        	initiateConnection();

	        s = connection.prepareStatement(sql);
	        int count = s.executeUpdate();
	        s.close();
	        System.out.println (count + " rows were inserted");
	        setMessage("Statement execution was successful: " + sql.substring(0,sql.indexOf(" ")));
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        if (connection != null) {
	            try {
	                connection.close();
	                System.out.println ("Database connection terminated");
	                setMessage("Statement failed: " + e.getMessage());
	                return false;
	            }
	            catch (Exception se) { 
	            	setMessage("Exception: " + se.getMessage());
	            	return false; 
            	}
	        }
	    }
		return true;
	}

	public ResultSet query(String sql)
	{
	    statement = null;
	    try {
	        if(this.connection == null || this.connection.isClosed()) 
	        	initiateConnection();
	        
	        statement = connection.createStatement();
	        statement.executeQuery(sql);
	        ResultSet rs = statement.getResultSet();
	        System.out.println("lets see " + rs.getFetchSize());
	        return rs;
	    }
	    catch(SQLException sq) {
	        System.out.println("Error in query");
	        return null;
	    }
	}

	public boolean isConnected(){
		
			try {
				if(connection == null || connection.isClosed())
					return false;
				else
					return true;
			} catch (SQLException e) {
				return false;
			}
	}

	public String getMessage() {  return message;  }
	
	private void setMessage(String str){  message = str;  }
}
