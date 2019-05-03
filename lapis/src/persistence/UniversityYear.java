package persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UniversityYear {
	
	@Id
	@GeneratedValue
	private int id;
	private int first;
	private int last;

	public UniversityYear() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UniversityYear(int first, int last) {
		this.first = first;
		this.last = last;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return first + "-" + last;
	}
	
}
