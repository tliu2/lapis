package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import business.CourseCreation;
import persistence.Promotion;

public class CourseBean {

	private int id;
	private Promotion promotion; // need to change to type String and search in database with string instead
									// ofpromotion
	private String name;
	private List<SelectItem> items = new ArrayList<SelectItem>();

	@ManagedProperty(value = "#{promotionService}")
	private PromotionService promotionService;
	private List<Promotion> promotions = promotionService.getPromotions();

//	@ManagedProperty(value ="#{promotionService.promotions}")
//	private List<Promotion> promotions;

	private CourseCreation courseCreation = new CourseCreation();

	public CourseBean() {
		System.out.println(promotions.size());
		items = new ArrayList<SelectItem>();
		for (Promotion promotion : promotions) {
			SelectItem menuChoice = new SelectItem(promotion.getDiplomaName() + " " + promotion.getLevel());
			items.add(menuChoice);
		}
	}

	public void createCourse() {
		courseCreation.createCourse(promotion, name);

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Course created !", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
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

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public CourseCreation getCourseCreation() {
		return courseCreation;
	}

	public void setCourseCreation(CourseCreation courseCreation) {
		this.courseCreation = courseCreation;
	}

	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

}
