package info.goolap.beuth.jdbc.Storage.Keyword_DataAccessObject;

/**
 * @author Robert Dziuba
 * This class implements a single place of  RDBMS tables to java variables. 
 *
 */
public class Keyword_SQL_Statements {
	
	// mappings for simple example 
	final static String TBL_Keywords = "KEYWORDS"; 
	final static String COL_id ="ID";
	final static String COL_keyword = "VALUE";
	final static String COL_category = "CATEGORY";

	// example queries
	final static String SELECT_STAR_FROM_Keywords = "select * from "+ TBL_Keywords;
	//final static String SELECT_PERSONS_WITH_YEAR= "select * from "+TBL_fbi + " where  year("+ COL_born_date + ") = ? ";
	final static String	INSERT_Keywords = "insert into "+ TBL_Keywords
							+"("+ 
							COL_id 	+ 	", "+ 
							COL_keyword 	+ 	", "+ 
							COL_category 	+ 	") values (?, ?, ?)";
	
	final static String DELETE_Keyword = "delete  from "+ TBL_Keywords + " where  "+ COL_id + " = ? ";
}
