package csvio;

import info.goolap.beuth.jdbc.logic.FBI.FBI_Logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class CSVFBIReader {
	
	/* Helferklasse, die uns ermöglicht, filename als String zu übergeben - ruft dann die untere readEntityList() aus */
	public static FBI_Logic[] readEntityList(String dateiname, String splitter){
		Path source = Paths.get(dateiname);
		return readEntityList(source, splitter);
	}
	
	/*
	 * wie beim Writer nur andersrum lesen wir unsere Datei ein 
	 * und kreieren uns aus den eingelesenen Strings wieder ein Objekt. 
	 * */
	public static FBI_Logic[] readEntityList(Path source, String splitter){
		List<FBI_Logic> target = new ArrayList<FBI_Logic>();
		try {
			List<String> lines = Files.readAllLines(source);
			for (String line : lines){

				target.add(new FBI_Logic(line.split(splitter)));

			}
		} catch (IOException ex) { ex.printStackTrace(System.err);
			target.addAll(null); //null addition to target to indicate problem
		}
		
		return target.toArray(new FBI_Logic[target.size()]);
	}

}
