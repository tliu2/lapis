package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import business.PromotionCreation;
import persistence.UniversityYear;

@ManagedBean
public class PromotionBean {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = new ArrayList<UniversityYear>();

	private UniversityYear year;
	private String diplomaName;
	private int level;

	private PromotionCreation promotionCreation = new PromotionCreation();

	public PromotionBean() {
		universityYears = promotionCreation.readUniversityYears();

		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.getFirst() + "-" + universityYear.getLast());
			items.add(menuChoice);
		}
	}

	public UniversityYear getYear() {
		return year;
	}

	public void setYear(UniversityYear year) {
		this.year = year;
	}

	public String getDiplomaName() {
		return diplomaName;
	}

	public void setDiplomaName(String diplomName) {
		this.diplomaName = diplomName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public List<UniversityYear> getUniversityYears() {
		return universityYears;
	}

	public void setUniversityYears(List<UniversityYear> universityYears) {
		this.universityYears = universityYears;
	}

	public void createPromotion() {
		System.out.println(year.toString());
		System.out.println("created");
		promotionCreation.createPromotion(year, diplomaName, level);
	}
}
