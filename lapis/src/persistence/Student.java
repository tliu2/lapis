package persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Student {

	@Id
	@GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	private String ine;
	private String ucpNumber;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Promotion.class)
	private Promotion promotion;

	public Student(String firstname, String lastname, String ine, String ucpNumber, Promotion promotion) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.ine = ine;
		this.ucpNumber = ucpNumber;
		this.promotion = promotion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIne() {
		return ine;
	}

	public void setIne(String ine) {
		this.ine = ine;
	}

	public String getUcpNumber() {
		return ucpNumber;
	}

	public void setUcpNumber(String ucpNumber) {
		this.ucpNumber = ucpNumber;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Student() {
	}

	
}
