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
import javax.faces.event.AjaxBehaviorEvent;

import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

import com.mysql.jdbc.RowDataCursor;

import business.*;
import persistence.*;


@ManagedBean
@ViewScoped
public class EditStudentsFromPromotionBean {



	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<Student>> dataPromo = new HashMap<String, List<Student>>();

	private String year;
	private String promo;
	private String student;

	private List<String> years;
	private List<String> promotions;
	private List<Student> students;
	
	private String studentSelected ;

	private PromotionDAO promoDAO = new PromotionDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private StudentDAO studentDAO = new StudentDAO();
	
	private Session session = DBConnection.getSession();

	
	public EditStudentsFromPromotionBean() {
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

		List<Promotion> allPromos = promoDAO.readAllPromo();
		for (Promotion promotion : allPromos) {
			int id = promotion.getId();
			List<Student> students = studentDAO.readStudentByPromoId(id);
			List<String> studentList = new ArrayList<String>();
			for (Student student : students) {
				studentList.add(student.getFirstname()+ " " + student.getLastname());
			}
			dataPromo.put(promotion.toString(), students);
		}

	}
	
	public void updateFields() {
		
		for (Student s : students) {
			studentDAO.updateStudent(s, session) ;
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student(s) updated !", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Student Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onChange() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}

	public void onChangeStudent() {
		if (year != null && !year.equals("") && promo != null && !promo.equals("")) {
			students = dataPromo.get(promo);
		} else {
			students = new ArrayList<Student>();
		}
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getStudentSelected() {
		return studentSelected;
	}

	public void setStudentSelected(String studentSelected) {
		this.studentSelected = studentSelected;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Map<String, List<Student>> getDataPromo() {
		return dataPromo;
	}

	public void setDataPromo(Map<String, List<Student>> dataPromo) {
		this.dataPromo = dataPromo;
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

	public List<Student> getCourses() {
		return students;
	}

	public void setCourses(List<Student> courses) {
		this.students = courses;
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

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
}
