package csvio;


import info.goolap.beuth.jdbc.logic.Keyword.Keyword_Logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVKeywordReader {
	
	/* Helferklasse, die uns ermöglicht, filename als String zu übergeben - ruft dann die untere readEntityList() aus */
	public static Keyword_Logic[] readEntityList(String dateiname, String splitter){
		Path source = Paths.get(dateiname);
		return readEntityList(source, splitter);
	}
	
	/*
	 * wie beim Writer nur andersrum lesen wir unsere Datei ein 
	 * und kreieren uns aus den eingelesenen Strings wieder ein Objekt. 
	 * */
	public static Keyword_Logic[] readEntityList(Path source, String splitter){
		List<Keyword_Logic> target = new ArrayList<Keyword_Logic>();
		try {
			List<String> lines = Files.readAllLines(source);
			int i = 0;
			for (String line : lines){
				target.add(new Keyword_Logic(i++,line.split(splitter)));
			}
		} catch (IOException ex) { ex.printStackTrace(System.err);
			target.addAll(null); //null addition to target to indicate problem
		}
		
		return target.toArray(new Keyword_Logic[target.size()]);
	}

}
