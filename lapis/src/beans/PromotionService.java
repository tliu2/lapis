package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import business.PromotionCreation;
import business.PromotionManager;
import persistence.Promotion;
import persistence.UniversityYear;

@ManagedBean
public class PromotionService {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = new ArrayList<UniversityYear>();
	private List<Promotion> promotions = new ArrayList<Promotion>();

	private String year;
	private String promotion;
	private String diplomaName;
	private int level;

	private PromotionCreation promotionCreation = new PromotionCreation();
	private PromotionManager promotionManager = new PromotionManager();

	public PromotionService() {
		universityYears = promotionCreation.readUniversityYears();

		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.getFirst() + "-" + universityYear.getLast());
			items.add(menuChoice);
		}
	}
	
	public String fromUniversityListToPromotion() {
		String result = "createCourse";
		System.out.println(year);
		 if(year !=null && !year.toString().equals("")) {
	        	
	        	int id = promotionManager.getIdFromUniversityYearString(year);
	        	System.out.println(id);
	        	
	        	promotions = promotionManager.readPromotionFromUniversityYear(id);
	        }
		 return result;
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

	public PromotionCreation getPromotionCreation() {
		return promotionCreation;
	}

	public void setPromotionCreation(PromotionCreation promotionCreation) {
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	
	
	
	
}
