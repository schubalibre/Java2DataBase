package info.goolap.beuth.jdbc.logic.FBI;

import info.goolap.beuth.jdbc.Storage.FBI_DataAccessObject.FBI_Storage;

public class FBI_Logic {
	private String city;
	private int population, violent_crime, property_crime;
	
	public FBI_Logic() {
		
	}
	
	public FBI_Logic(String city, int violent_crime, int property_crime, int population) {
		this.setCity(city);
		this.setViolent_crime(violent_crime);
		this.setProperty_crime(property_crime);
		this.setPopulation(population);
	}
	
	public FBI_Logic(String[] s) {
		this.setCity(s[1]);
		this.setPopulation(s[2]);
		this.setViolent_crime(s[3]);
		this.setProperty_crime(s[8]);
	}
	
	private String cleanCityName(String string) {
		// nimmt alle Fußnoten aus den Namen
		return string.replaceAll("[^\\D.]", "");
	}
	
	private Integer cleanInteger(String string) {
		// Wenn leer dann gib Null zurück
		if(string.trim().length() == 0) return 0;
		// ersetze amerikanische Zifferngruppierungen und wandle in Int um.
		return Integer.parseInt(string.replace(",",""));
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = this.cleanCityName(city);
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setPopulation(String population) {
		this.population = this.cleanInteger(population);
	}

	public int getViolent_crime() {
		return violent_crime;
	}
	
	public void setViolent_crime(int violentCrime) {
		this.violent_crime = violentCrime;
	}

	public void setViolent_crime(String violentCrime) {
		this.violent_crime = this.cleanInteger(violentCrime);
	}

	public int getProperty_crime() {
		return property_crime;
	}

	public void setProperty_crime(int propertyCrime) {
		this.property_crime = propertyCrime;
	}

	public void setProperty_crime(String propertyCrime) {
		this.property_crime = this.cleanInteger(propertyCrime);
	}

	/**
	 * @param myPerson_Storage
	 * This method persists "this" instance and calls in Person_Storage the methid insertPerson (...) 
	 */
	public void persist(FBI_Storage myExcel_Storage) {
		myExcel_Storage.insertFBI(city, violent_crime, property_crime, population);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("FBI[");
		str.append(getCity() + ",");
		str.append(getViolent_crime() + ", ");
		str.append(getProperty_crime() + ",");
		str.append(getPopulation());
		str.append("]");
		
		return str.toString();
	}
}
