package info.goolap.beuth.jdbc.Storage.FBI_DataAccessObject;

/**
 * @author Robert Dziuba
 * This class implements a single place of  RDBMS tables to java variables. 
 *
 */
public class FBI_SQL_Statements {
	
	// mappings for simple example 
	final static String TBL_fbi = "FBI_TMP"; 
	final static String COL_city ="City";
	final static String COL_violent_crime = "VIOLENT_CRIME";
	final static String COL_property_crime = "PROPERTY_CRIME";
	final static String COL_population ="POPULATION";

	// example queries
	final static String SELECT_STAR_FROM_FBI = "select * from "+ TBL_fbi;
	//final static String SELECT_PERSONS_WITH_YEAR= "select * from "+TBL_fbi + " where  year("+ COL_born_date + ") = ? ";
	final static String	INSERT_FBI = "insert into "+ TBL_fbi
							+"("+ 
							COL_city 	+ 	", "+ 
							COL_violent_crime 	+ 	", "+ 
							COL_property_crime 	+ 	", "+
							COL_population 	+	") values (?, ?, ?, ?)";
	
	final static String DELETE_FBI = "delete  from "+ TBL_fbi + " where  "+ COL_city + " = ? ";
}
