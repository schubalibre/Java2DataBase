package info.goolap.beuth.jdbc;

import info.goolap.beuth.jdbc.Storage.FBI_DataAccessObject.FBI_Storage;
import info.goolap.beuth.jdbc.Storage.Keyword_DataAccessObject.Keyword_Storage;
import info.goolap.beuth.jdbc.logic.FBI.FBI_Logic;
import info.goolap.beuth.jdbc.logic.Keyword.Keyword_Logic;

import java.sql.SQLException;

import csvio.CSVFBIReader;
import csvio.CSVKeywordReader;

public class CSV_Insert {
	
	public static void main(String[] args) throws SQLException {
		
		// New DB Connection Instance
		FBI_Storage FBI_Storage = new FBI_Storage();
		
		// Open DB
		FBI_Storage.openDB();
		
//		FBI_Logic[] fbiArray = CSVFBIReader.readEntityList("csv/fbi2006.csv", ";");
//		for(FBI_Logic fbi : fbiArray){
//			fbi.persist(FBI_Storage);
//			System.out.println(fbi);
//		}
		
		// SELECT * FROM "FBI"
		FBI_Storage.printAllFBI();

		// close DB
		FBI_Storage.closeDB();
		
		/* ***********************************************************************************************
		 * Keyword_Storage
		 * ***********************************************************************************************/
		
//		// New DB Connection Instance
//		Keyword_Storage keyword_Storage = new Keyword_Storage();
//		
//		// Open DB
//		keyword_Storage.openDB();
//		
//		Keyword_Logic[] keywordArray = CSVKeywordReader.readEntityList("csv/KeywordList.csv", ";");
//		for(Keyword_Logic keyword : keywordArray){
//			keyword.persist(keyword_Storage);
//			System.out.println(keyword);
//		}
//		
//		// SELECT * FROM "KEYWORDS"
//		keyword_Storage.printAllKeywords();
//
//		// close DB
//		keyword_Storage.closeDB();
		
	}

}
