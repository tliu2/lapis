package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import business.DBConnection;
import business.PromotionDAO;
import business.StudentDAO;
import business.StudentScoreDAO;
import business.TeamDAO;
import business.UniversityYearDAO;
import persistence.Evaluation;
import persistence.EvaluationScore;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class StudentsCriterionNotesBestBean {

	private BarChartModel barModel = new BarChartModel();
	
	private HashMap<String, Integer> criterionMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> occurenceMap = new HashMap<String, Integer>();
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataStudent = new HashMap<String, List<String>>();
	
	
	private ChartSeries criterionBar = new ChartSeries();
	private Session session = DBConnection.getSession();
	
	private String student;
	private String year;
	private String promo;

	private List<String> years = new ArrayList<String>();
	private List<String> promotions = new ArrayList<String>();
	private List<String> students = new ArrayList<String>();
	
	private TeamDAO teamDAO = new TeamDAO();
	private StudentDAO studentDAO = new StudentDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private PromotionDAO promoDAO = new PromotionDAO();

	public StudentsCriterionNotesBestBean() {
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
				studentList.add(student.getFirstname()+" "+student.getLastname()+ " "+student.getId());
			}
			dataStudent.put(promotion.toString(), studentList);
		}
		createBarModel();
	}

	public void createBarModel() {
		barModel = new BarChartModel();
		
		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Criterion");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Note");
		yAxis.setMin(0);
		yAxis.setMax(20);
		
		initBarModel();
	}
  
	public BarChartModel initBarModelStudent(Student student) {
		
		barModel = new BarChartModel();
		this.student = student.getFirstname()+" "+student.getLastname()+" "+student.getId();
		List<Team> allTeamList = teamDAO.readAllTeam(session);
		List<Team> teamList = new ArrayList<Team>();

		for (Team currentTeam : allTeamList) {
			if (containsStudent(currentTeam.getStudents(),student)) {
				teamList.add(currentTeam);
			}
		}
		for (Team team : teamList) {
			int index = 0;
			for (Student currentStudent : team.getStudents()) {
				index++;
				if (currentStudent.getId() == student.getId()) {
					
					List<StudentScore> studentScoreList = team.getStudentScores();
					StudentScore studentScore = studentScoreList.get(index - 1);
					List<EvaluationScore> evaluationScorelist = studentScore.getScores();
					criterionBar.setLabel(student.getFirstname() + ' ' + student.getLastname());
					
					for (EvaluationScore evaluationScore : evaluationScorelist) {
						
						Evaluation evaluation = evaluationScore.getEvaluation();
						String criterionName = evaluation.getCriterion().getName();
						int score = evaluationScore.getScore();
						if (criterionMap.containsKey(criterionName)) {

							if (criterionMap.get(criterionName) >= 10) {
								occurenceMap.put(criterionName, occurenceMap.get(criterionName) + 1);
								criterionMap.put(criterionName, (criterionMap.get(criterionName) + score));
							}
						}else {
							if (score >= 10) {
								occurenceMap.put(criterionName, 1);
								criterionMap.put(criterionName, (score / (occurenceMap.get(criterionName))));
							}
						}
					}
				}
			}
		}
		
		for (String criterion : criterionMap.keySet())
			criterionBar.set(criterion, criterionMap.get(criterion));

		barModel.addSeries(criterionBar);
		return barModel;
	}
	
	public BarChartModel initBarModel() {
		
		barModel = new BarChartModel();
		barModel.addSeries(criterionBar);
		return barModel;
	}
	
	public void onChangeUY() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}
	
	public void onChangePromo() {
		if (promo != null && !promo.equals("")) {
			students = dataStudent.get(promo);
		}else {
			students = new ArrayList<String>();
		}
	}
	
	public void onChange() {
		
		if (this.student != null && !this.student.equals("")) {
			
			String[] parts = this.student.split(" ");
			String studentID = parts[2];
			List <Student> studentsList = studentDAO.readStudentById(Integer.parseInt(studentID));
			barModel = initBarModelStudent(studentsList.get(0));
		}
	}
	public boolean containsStudent(List<Student> studentList , Student student) {
		
		for(Student currentStudent :studentList) {
			if(currentStudent.getId() == student.getId()){
						return true;
			}
		}
		return false;
	}
	
	public Map<String, List<String>> getDataStudent() {
		return dataStudent;
	}

	public void setDataStudent(Map<String, List<String>> dataStudent) {
		this.dataStudent = dataStudent;
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

	public HashMap<String, Integer> getCriterionMap() {
		return criterionMap;
	}

	public void setCriterionMap(HashMap<String, Integer> criterionMap) {
		this.criterionMap = criterionMap;
	}

	public HashMap<String, Integer> getOccurenceMap() {
		return occurenceMap;
	}

	public void setOccurenceMap(HashMap<String, Integer> occurenceMap) {
		this.occurenceMap = occurenceMap;
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public List<String> getStudents() {
		return students;
	}


	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}
	
	public ChartSeries getCriterionBar() {
		return criterionBar;
	}

	public void setCriterionBar(ChartSeries criterionBar) {
		this.criterionBar = criterionBar;
	}
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}
