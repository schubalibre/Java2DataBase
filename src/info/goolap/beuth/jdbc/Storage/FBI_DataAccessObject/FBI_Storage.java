package info.goolap.beuth.jdbc.Storage.FBI_DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FBI_Storage {
	   /* These variable values are used to setup the Connection object */
	
	   private static final String DBNAME = "oracle";
	   private static final String URL = "jdbc:oracle:thin:@localhost:1521:oracle";
	   private static final String USER = "Dziuba";
	   private static final String PASSWORD = "8WIbe8de1";
	   private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	   private Connection con;
	   private PreparedStatement prepStatement_INSERT_FBI;
	   
	 
	   /* 
	    This method is used to create a connection using 
	   the values listed above. Notice the throws clause 
	   in the method signature. This allows the calling method 
	   to deal with the exception rather than catching it in 
	   both places. The ClassNotFoundException must be caught 
	   because the forName method requires it. */

	   /**
	    * @param dbname
	    * @return
	    * @throws SQLException
	    *     
	    *  This method is used to create a connection using 
	    *  the values listed above. Notice the throws clause 
	    *  in the method signature. This allows the calling method 
	    *  to deal with the exception rather than catching it in 
	    *  both places. The ClassNotFoundException must be caught 
	    *  because the forName method requires it. 
	    */
	   private Connection connect(String dbname) throws SQLException {
	      Connection con = null;
	      try {
	         Class.forName(DRIVER); 
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	      }
	      catch(ClassNotFoundException ex) {
	         System.exit(-1);
	      }
	      return con;
	   }
	 
	   /** This method prints out the records of table person. Note the try 
	    *  and catch. Virtually all JDBC methods throw a 
	    *  SQLException that must be tended to. The connection 
	    *  object is used to create a statement object. 
	    *  The executeQuery method is used to submit a 
	    *  SELECT SQL query. The executeQuery method returns a ResultSet object. 
	    */
	   public void printAllFBI() {
		  String query = FBI_SQL_Statements.SELECT_STAR_FROM_FBI;
		  ResultSet rs = null;
	      try {
	         Statement s = con.createStatement();
			 long begin = System.currentTimeMillis();
	         rs = s.executeQuery(query);
			 long executiontime = System.currentTimeMillis()-begin;
	         this.printResultSet2Shell(rs,query,executiontime);
	         rs.close();
	         s.close();
	      }
	      catch(SQLException ex) {
	    		// handle any errors
	    		System.out.println("SQLException: " + ex.getMessage());
	    		System.out.println("SQLState: " + ex.getSQLState());
	    		System.out.println("VendorError: " + ex.getErrorCode());        
	    		System.exit(-1);
	      }
	   }

	   /** This method is used to open the DB and to print out 
	   * the connection status.  
	 * @param prepStatement_INSERT_PERSON 
	   */
	   public void openDB() throws SQLException {
		   con  = this.connect(DBNAME);
		   System.out.println("Connected to: " +
				   con.getMetaData().getDatabaseProductName() + " " +
				   con.getMetaData().getDatabaseProductVersion() 
			);
		   prepStatement_INSERT_FBI = con.prepareStatement(FBI_SQL_Statements.INSERT_FBI);
		   //prepStatement_SELECT_PERSONS_WITH_YEAR = con.prepareStatement(FBI_SQL_Statements.SELECT_PERSONS_WITH_YEAR);
		}

	   /** This method opens a connection the DB and prints out 
	    * the connection status.  
	    */
	   public void closeDB() throws SQLException {
		   prepStatement_INSERT_FBI.close();
		   con.close();
		   System.out.println("Connection is closed: "+ con.isClosed());
	   }

	   
	   /**
	    * @param rs
	    * @param query
	    * @param executiontime
	    * @throws SQLException
	    * 
	    * This method navigates through the records 
	    * in the ResultSet object (the next method for 
	    * example moves the cursor to the next row; it 
	    * returns false when it runs out of rows) as well 
	    * as methods to access fields in those rows. 
	    * Notice that the person_id  fields is of  the  data 
	    * type 'long', fields name and location are strings
	    * and field born_date is a date. The ResultSet 
	    * object provides methods to deal with most common data 
	    * types. Please  review how other java data types align 
	    * with database data types. The report is 
	    * formatted using the format method introduced in Java 5.  
	    * */
	   private void printResultSet2Shell(ResultSet rs, String query, long executiontime) throws SQLException
	   {
		   System.out.println("===============================================================");
		   System.out.println(query);
		   System.out.println("Execution Time: " + executiontime +"ms");
		   System.out.println("===============================================================");
		   System.out.format("%-20s %-15s %-15s %10s%n", 
	           "city", "violent_crime", "property_crime", "population");
	       System.out.format("%-20s %-15s %-15s %10s%n", 
	           "-------------------", "---------------", 
				"------------", "----------");

	       while(rs.next()) {
	           String city = rs.getString(FBI_SQL_Statements.COL_city);
	           int violent_crime = rs.getInt(FBI_SQL_Statements.COL_violent_crime);
	           int property_crime = rs.getInt(FBI_SQL_Statements.COL_property_crime);
	           int population = rs.getInt(FBI_SQL_Statements.COL_population);

	           System.out.format("%-20s %-20d %-20d %-20d %n", 
	        		   city, violent_crime, property_crime, population);
	       }
		   System.out.println("================================================================");
	   }

		/**
		 * 
		 * @param city
		 * @param population
		 * @param property_crime
	 	 * @param violent_crime
	 	 * This method demonstrates an insert record operation. The method implements this functionality with 
	 	 * a  prepared statement and an executeUpdate. Eventually, we can extend the method for update 
	 	 * and delete operations as well.
	 	 */
	   public void insertFBI(String city, int violent_crime, int property_crime, int population) 
	   {
		   try {
			   PreparedStatement pstmt = prepStatement_INSERT_FBI;
					 pstmt.setString(1,city);
					 pstmt.setInt(2, violent_crime );
					 pstmt.setInt(3, property_crime);
					 pstmt.setInt(4, population);
					 System.out.println("===== Executing Insert ========================================="); 
					 System.out.println(pstmt.toString());
					 System.out.println("Update executed with code:" +  pstmt.executeUpdate()); 
		   }
			   catch(SQLException ex) {
			   		// handle any errors
			   		System.out.println("SQLException: " + ex.getMessage());
			   		System.out.println("SQLState: " + ex.getSQLState());
			   		System.out.println("VendorError: " + ex.getErrorCode());        
			   		System.exit(-1);
			   }
	   }
	   
	   /**
	    * @param query
	    * This method is currently not used. It demonstrates, given a query, how we can read meta information about tables 
	    * in the query from the data dictionary.  
	    */
	   private void getColumnNamesAndTypes(String query) 
	   {
		   ResultSet rs = null;
		   try {
			   Statement stmt = con.createStatement();     			// Create a Statement object
			   rs = stmt.executeQuery(query); 
			   ResultSetMetaData rsmtadta = rs.getMetaData();      // Create a ResultSetMetaData object  
			   int colCount = rsmtadta.getColumnCount();                                   
			   for (int i=1; i<= colCount; i++) 
			   {                                          
				   String colName = rsmtadta.getColumnName(i);    // Get column name
				   String colType = rsmtadta.getColumnTypeName(i);
				   System.out.println("Column = " + colName + " is data type " + colType);  // Print the column value
			   } 
			   stmt.close();
		   }
		   catch(SQLException ex) {
	   		// handle any errors
	   		System.out.println("SQLException: " + ex.getMessage());
	   		System.out.println("SQLState: " + ex.getSQLState());
	   		System.out.println("VendorError: " + ex.getErrorCode());        
	   		System.exit(-1);
		   }
	   }
}
