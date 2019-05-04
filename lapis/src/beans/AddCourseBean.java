package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import business.CourseDAO;
import business.PromotionDAO;
import business.UniversityYearDAO;
import persistence.Promotion;

@ManagedBean
public class AddCourseBean {

	private int id;

	private String name;

	private List<SelectItem> items = new ArrayList<SelectItem>();

	private List<Promotion> promotions = new ArrayList<Promotion>();

	private String promoString;

	@ManagedProperty(value = "#{navig}")
	private NavigationBean navig;

	private String year;

	private CourseDAO courseDAO = new CourseDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private PromotionDAO promoDAO = new PromotionDAO();

	public AddCourseBean() {

	}

	@PostConstruct
	public void init() {
		year = navig.getYear();

		retrievePromoForYear();

		items = new ArrayList<SelectItem>();
		for (Promotion promotion : promotions) {
			SelectItem menuChoice = new SelectItem(promotion.getDiplomaName() + " " + promotion.getLevel());
			items.add(menuChoice);
		}
	}

	private String retrievePromoForYear() {
		String result = "addCourse";
		System.out.println(year);
		if (year != null && !year.toString().equals("")) {

			int id = yearDAO.getIdFromUniversityYearString(year);
			System.out.println(id);

			promotions = promoDAO.readPromoByYearId(id);
		}
		return result;
	}

	public void createCourse() {
		Promotion promotion = null;
		
		for (Promotion promo : promotions) {
			//For the same university year, each promotion has its unique toString() value.
			if (promo.toString().equals(promoString)) {
				promotion = promo;
			}
		}

		courseDAO.createCourse(promotion, name);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Course created !", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public NavigationBean getNavig() {
		return navig;
	}

	public void setNavig(NavigationBean navig) {
		System.out.println("set !");
		this.navig = navig;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public CourseDAO getCourseCreation() {
		return courseDAO;
	}

	public void setCourseCreation(CourseDAO courseCreation) {
		this.courseDAO = courseCreation;
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

	public void setYearDAO(UniversityYearDAO yearDAO) {
		this.yearDAO = yearDAO;
	}

	public PromotionDAO getPromoDAO() {
		return promoDAO;
	}

	public void setPromoDAO(PromotionDAO promoDAO) {
		this.promoDAO = promoDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public String getPromoString() {
		return promoString;
	}

	public void setPromoString(String promoString) {
		this.promoString = promoString;
	}

}
