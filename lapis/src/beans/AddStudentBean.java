package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import business.PromotionDAO;
import business.StudentDAO;
import business.UniversityYearDAO;
import persistence.Promotion;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class AddStudentBean {

	private Map<String, List<String>> data = new HashMap<String, List<String>>();

	private String year;
	private String promo;

	private List<String> years;
	private List<String> promotions;

	private String firstname;
	private String lastname;
	private String ine;
	private String ucpNumber;

	private StudentDAO studentDAO = new StudentDAO();
	private PromotionDAO promoDAO = new PromotionDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();

	public AddStudentBean() {

	}

	@PostConstruct
	public void init() {

		List<UniversityYear> allYears = yearDAO.readAllUniversityYears();
		years = new ArrayList<String>();
		for (UniversityYear universityYear : allYears) {
			years.add(universityYear.toString());
		}

		for (UniversityYear universityYear : allYears) {
			int id = universityYear.getId();
			List<Promotion> promos = promoDAO.readPromoByYearId(id);
			List<String> promoList = new ArrayList<String>();
			for (Promotion promo : promos) {
				promoList.add(promo.toString());
			}
			data.put(universityYear.toString(), promoList);
		}
	}

	public void onChange() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}
	
	public void createStudent() {
		FacesMessage msg;
		if (promo != null && year != null && firstname != null && !firstname.equals("") && lastname != null && !lastname.equals("")) {
			
			Promotion promotion = null;
			int id = yearDAO.getIdFromUniversityYearString(year);
			List<Promotion> promos = promoDAO.readPromoByYearId(id);
			for (Promotion pro : promos) {
				if (pro.toString().equals(promo)) {
					promotion = pro;
				}
			}
			
			studentDAO.createStudent(promotion, firstname, lastname, ine, ucpNumber);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student created !", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid : missing information !", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<String> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<String> promotions) {
		this.promotions = promotions;
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

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public PromotionDAO getPromoDAO() {
		return promoDAO;
	}

	public void setPromoDAO(PromotionDAO promoDAO) {
		this.promoDAO = promoDAO;
	}

	public UniversityYearDAO getYearDAO() {
		return yearDAO;
	}

	public void setYearDAO(UniversityYearDAO yearDAO) {
		this.yearDAO = yearDAO;
	}

}
