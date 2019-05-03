package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.sun.org.glassfish.gmbal.ManagedAttribute;

import business.PromotionDAO;
import business.UniversityYearDAO;
import persistence.Promotion;
import persistence.UniversityYear;

@ManagedBean(name = "promotionService")
public class SelectPromoBean {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = new ArrayList<UniversityYear>();

	private List<Promotion> promotions = new ArrayList<Promotion>();

	private String year;

	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private PromotionDAO promoDAO = new PromotionDAO();

	public SelectPromoBean() {
		universityYears = yearDAO.readAllUniversityYears();

		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.getFirst() + "-" + universityYear.getLast());
			items.add(menuChoice);
		}
	}

	public String fromUniversityListToPromotion() {
		String result = "createCourse";
		System.out.println(year);
		if (year != null && !year.toString().equals("")) {

			int id = yearDAO.getIdFromUniversityYearString(year);
			System.out.println(id);

			promotions = promoDAO.readPromotionFromUniversityYear(id);
			System.out.println("nbPromo : " + promotions.size());
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

	public UniversityYearDAO getYearDAO() {
		return yearDAO;
	}

	public PromotionDAO getPromoDAO() {
		return promoDAO;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}



}
