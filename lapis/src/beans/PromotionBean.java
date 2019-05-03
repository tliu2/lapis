package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import business.PromotionCreation;
import persistence.UniversityYear;

@ManagedBean
public class PromotionBean {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = getUniversityYear();
	private UniversityYear year;
	private String diplomName;
	private int level;

	public PromotionBean() {
	}

	@PostConstruct
	public void init() {
		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.toString());
			items.add(menuChoice);
		}
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

	public String doAction() {
		return ("show-test-data");
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

	public List<UniversityYear> getUniversityYear() {
		return PromotionCreation.testWhereClause();
	}

}
