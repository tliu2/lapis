package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import business.PromotionDAO;
import business.UniversityYearDAO;
import persistence.UniversityYear;

@ManagedBean
public class AddPromoBean {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = new ArrayList<UniversityYear>();

	private String year;
	private String diplomaName;
	private int level;

	private PromotionDAO promotionCreation = new PromotionDAO();
	private UniversityYearDAO yearDao = new UniversityYearDAO();

	public AddPromoBean() {
		universityYears = yearDao.readAllUniversityYears();

		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.getFirst() + "-" + universityYear.getLast());
			items.add(menuChoice);
		}
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDiplomaName() {
		return diplomaName;
	}

	public void setDiplomaName(String diplomaName) {
		this.diplomaName = diplomaName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public PromotionDAO getPromotionCreation() {
		return promotionCreation;
	}

	public void setPromotionCreation(PromotionDAO promotionCreation) {
		this.promotionCreation = promotionCreation;
	}

	public void createPromotion() {
		UniversityYear yearObject = null;
		for (UniversityYear universityYear : universityYears) {
			if (universityYear.toString().equals(year)) {
				yearObject = universityYear;
			}
		}
		promotionCreation.createPromotion(yearObject, diplomaName, level);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion created !", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
