package info.goolap.beuth.jdbc.logic.Keyword;

import info.goolap.beuth.jdbc.Storage.Keyword_DataAccessObject.Keyword_Storage;

public class Keyword_Logic {
	private String keyword;
	private int id, category;
	
	public Keyword_Logic() {
		
	}
	
	public Keyword_Logic(int id, String keyword, int category) {
		this.setId(id);
		this.setKeyword(keyword);
		this.setCategory(category);
	}
	
	public Keyword_Logic(int id, String[] s) {
		this.setId(id);
		this.setKeyword(s[0]);
		this.setCategory(s[1]);
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = (category.contains("aktive")) ? 1 : 2;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	/**
	 * @param myPerson_Storage
	 * This method persists "this" instance and calls in Person_Storage the methid insertPerson (...) 
	 */
	public void persist(Keyword_Storage keyword_Storage) {
		keyword_Storage.insertKeyword(id, keyword, category);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("Keyword[");
		str.append(getId() + ",");
		str.append(getKeyword() + ", ");
		str.append(getCategory());
		str.append("]");
		
		return str.toString();
	}
}
