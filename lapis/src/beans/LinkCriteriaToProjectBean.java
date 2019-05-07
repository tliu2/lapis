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

import business.CourseDAO;
import business.CriteriaToProjectDAO;
import business.ProjectDAO;
import business.PromotionDAO;
import business.UniversityYearDAO;
import persistence.Course;
import persistence.Evaluation;
import persistence.Project;
import persistence.Promotion;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class LinkCriteriaToProjectBean {

	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataPromo = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataCourse = new HashMap<String, List<String>>();

	private String year;
	private String promo;
	private String course;
	private String project;

	private List<String> years;
	private List<String> promotions;
	private List<String> courses;
	private List<String> projects;

	private String subject;
	private String description;
	private int minEtu = -1;
	private int maxEtu = -1;
	private int maxTeam = 10;
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();

	// private StudentDAO studentDAO = new StudentDAO();
	private CriteriaToProjectDAO criteriaToProjectDAO = new CriteriaToProjectDAO();
	private ProjectDAO projectDAO = new ProjectDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private PromotionDAO promoDAO = new PromotionDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();

	public LinkCriteriaToProjectBean() {

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
			List<Course> courses = courseDAO.readCourseByPromoId(id);
			List<String> courseList = new ArrayList<String>();
			for (Course course : courses) {
				courseList.add(course.getName());
			}
			dataPromo.put(promotion.toString(), courseList);
		}
		
		List<Course> allCourses = courseDAO.readAllCourse();
		for (Course course : allCourses) {
			int id = course.getId();
			List<Project> projects = projectDAO.readProjectByCourseId(id);
			List<String> projectList = new ArrayList<String>();
			for (Project project : projects) {
				projectList.add(project.getId() + "-" + project.getSubject());
			}
			dataCourse.put(course.getName(), projectList);
			
		}
		
	}

	public Map<String, List<String>> getDataCourse() {
		return dataCourse;
	}

	public void setDataCourse(Map<String, List<String>> dataCourse) {
		this.dataCourse = dataCourse;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public CriteriaToProjectDAO getCriteriaToProjectDAO() {
		return criteriaToProjectDAO;
	}

	public void setCriteriaToProjectDAO(CriteriaToProjectDAO criteriaToProjectDAO) {
		this.criteriaToProjectDAO = criteriaToProjectDAO;
	}

	public Map<String, List<String>> getDataPromo() {
		return dataPromo;
	}

	public void setDataPromo(Map<String, List<String>> dataPromo) {
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

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinEtu() {
		return minEtu;
	}

	public void setMinEtu(int minEtu) {
		this.minEtu = minEtu;
	}

	public int getMaxEtu() {
		return maxEtu;
	}

	public void setMaxEtu(int maxEtu) {
		this.maxEtu = maxEtu;
	}

	public int getMaxTeam() {
		return maxTeam;
	}

	public void setMaxTeam(int maxTeam) {
		this.maxTeam = maxTeam;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
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

	public void onChange() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}

	public void onChangeCourse() {
		if (promo != null && !promo.equals("")) {
			courses = dataPromo.get(promo);
		} else {
			courses = new ArrayList<String>();
		}
	}
	
	public void onChangeProject() {
		if (project != null && !project.equals("")) {
			projects = dataCourse.get(project);
		} else {
			projects = new ArrayList<String>();
		}
	}

	public void createProject() {
		FacesMessage msg;
		if (promo != null && year != null && course != null && !subject.equals("") && maxEtu != -1 && minEtu != -1
				&& maxTeam != -1) {

			Course courseObject = null;
			int id = promoDAO.getIdFromPromotionString(promo);
			List<Course> courses = courseDAO.readCourseByPromoId(id);
			for (Course cour : courses) {
				if (cour.toString().equals(course)) {
					courseObject = cour;
				}
			}

			projectDAO.createProject(subject, description, courseObject, minEtu, maxEtu, maxTeam, evaluations);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Project created !", null);
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

}
