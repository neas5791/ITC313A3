package zzITC313A3T100;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class JDBCConnection {
	private Connection connection = null;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://localhost/ST11187033";
	private final String user = "root";
	private final String password = "abc123";
	private Statement statement;
	
	public void initiateConnection(){
	    try {
	        if(this.connection == null || this.connection.isClosed()) {
	            try {
	                Class.forName (driver).newInstance ();
	                this.connection = DriverManager.getConnection(url, user, password);
	                System.out.println("database connection established.");
	            }
	            catch(SQLException sqe) {
	                throw new SQLException(sqe);
	            }
	            catch (InstantiationException e) {
	                e.printStackTrace();
	            } 
	            catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } 
	            catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    catch(SQLException sqe) {
	        sqe.printStackTrace();
	    }
	}
	
	public void disconnect(){
		if (connection != null) {
            try {
				connection.close ();
	            System.out.println ("Database connection terminated");
			} 
            catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertImage(File fi, String sql){ 
	    PreparedStatement s = null;
	    FileInputStream fis = null;
	    
	    try {
	        if(connection == null || connection.isClosed()) 
	        	initiateConnection();

	        s = connection.prepareStatement(sql);
	        s.setBlob(1, fis);
	        s.executeUpdate();
	        s.close();
	        System.out.println (fi + " was inserted into DB");
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        if (connection != null) {
	            try {
	                connection.close();
	                System.out.println ("Database connection terminated");
	            }
	            catch (Exception se) { /* ignore close errors */ }
	        }
	    }
	}
	
	public String insertData(String sql) {
	    PreparedStatement s = null;
	    try {
	    	
	        if(connection == null || connection.isClosed()) 
	        	initiateConnection();

	        s = connection.prepareStatement(sql);
	        int count = s.executeUpdate();
	        s.close();
	        System.out.println (count + " rows were inserted");
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	        if (connection != null) {
	            try {
	                connection.close();
	                System.out.println ("Database connection terminated");
	                return e.getMessage();
	            }
	            catch (Exception se) { return se.getMessage(); }
	        }
	    }
	    return null;
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
			return !connection.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
}
