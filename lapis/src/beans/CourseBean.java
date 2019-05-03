package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import business.CourseCreation;
import persistence.Promotion;

public class CourseBean {

	private int id;
	private Promotion promotion;
	private String name;
		
	private List<Promotion> promotions = new ArrayList<Promotion>();

	private CourseCreation courseCreation = new CourseCreation();

	public CourseBean() {
	}
	
	public void createCourse() {
		courseCreation.createCourse(promotion,name);
		
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

}
