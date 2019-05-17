package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import business.CourseDAO;
import business.CriteriaToProjectDAO;
import business.DBConnection;
import business.ProjectDAO;
import business.PromotionDAO;
import business.TeamDAO;
import business.UniversityYearDAO;
import persistence.Course;
import persistence.Evaluation;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;
import persistence.Team;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class SelectTeamBean {

	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataPromo = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataProject = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataTeam = new HashMap<String, List<String>>();

	private String year;
	private String promo;
	private String course;
	private String project;
	private String team;

	private List<String> years;
	private List<String> promotions;
	private List<String> courses;
	private List<String> projects;
	private List<String> teams;

	private String subject;
	private String description;
	private List<Evaluation> evaluations = new ArrayList<Evaluation>();
	
	private Session session = DBConnection.getSession();
	
	private CriteriaToProjectDAO criteriaToProjectDAO = new CriteriaToProjectDAO();
	private ProjectDAO projectDAO = new ProjectDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private PromotionDAO promoDAO = new PromotionDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private TeamDAO teamDAO = new TeamDAO();

	@ManagedProperty(value = "#{navigTeamtoEvaluation}")
	private TeamToEvaluationBean teamToEvaluationBean;

	public SelectTeamBean() {

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
			dataProject.put(course.getName(), projectList);
		}

		List<Project> allProjects = projectDAO.readAllProject();
		for (Project project : allProjects) {
			int id = project.getId();
			List<Team> teams = teamDAO.readTeamByProjectId(id, session);
			List<String> teamList = new ArrayList<String>();
			for (Team team : teams) {
				String teamName = "";
				List<Student> students = team.getStudents();
				for (int i = 0; i < students.size(); i++) {
					if (i == 0)
						teamName = students.get(i).getLastname();
					else
						teamName = teamName + "-" + students.get(i).getLastname();
				}
				teamList.add(team.getName() + "(" + teamName + ") #" + team.getId());
			}
			dataTeam.put(project.getSubject(), teamList);
		}

	}

	public String toTeamToEvaluationBean() {
		String split[] = team.split("#");

		teamToEvaluationBean.setTeamID(split[1]);
		return "showTeamEvaluation";
	}

	public TeamToEvaluationBean getTeamToEvaluationBean() {
		return teamToEvaluationBean;
	}

	public void setTeamToEvaluationBean(TeamToEvaluationBean teamToEvaluationBean) {
		this.teamToEvaluationBean = teamToEvaluationBean;
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
		if (course != null && !course.equals("")) {
			projects = dataProject.get(course);
		} else {
			projects = new ArrayList<String>();
		}
	}

	public void onChangeTeam() {
		System.out.println(project);
		if (project != null && !project.equals("")) {
			String[] split = project.split("-");
			teams = dataTeam.get(split[1]);
		} else {
			teams = new ArrayList<String>();
		}

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

	public Map<String, List<String>> getDataProject() {
		return dataProject;
	}

	public void setDataProject(Map<String, List<String>> dataProject) {
		this.dataProject = dataProject;
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

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public CriteriaToProjectDAO getCriteriaToProjectDAO() {
		return criteriaToProjectDAO;
	}

	public void setCriteriaToProjectDAO(CriteriaToProjectDAO criteriaToProjectDAO) {
		this.criteriaToProjectDAO = criteriaToProjectDAO;
	}

	public Map<String, List<String>> getDataTeam() {
		return dataTeam;
	}

	public void setDataTeam(Map<String, List<String>> dataTeam) {
		this.dataTeam = dataTeam;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

}
