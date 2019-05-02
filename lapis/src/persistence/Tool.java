package persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Tool {
	
	@Id
	@GeneratedValue
	private int id;
	private String category;
	
	public Tool(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Tool() {
	}
	
}
