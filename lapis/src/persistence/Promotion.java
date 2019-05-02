package persistence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Promotion {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = UniversityYear.class)
	private UniversityYear year;
	private String diplomName;
	private int level;
	
	public Promotion() {

	}

	public Promotion(UniversityYear year, String diplomName, int level) {
		this.year = year;
		this.diplomName = diplomName;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UniversityYear getYear() {
		return year;
	}

	public void setYear(UniversityYear year) {
		this.year = year;
	}

	public String getDiplomName() {
		return diplomName;
	}

	public void setDiplomName(String diplomName) {
		this.diplomName = diplomName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
