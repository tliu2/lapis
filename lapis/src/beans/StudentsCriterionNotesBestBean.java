package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import business.DBConnection;
import business.StudentDAO;
import business.TeamDAO;
import persistence.Evaluation;
import persistence.EvaluationScore;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;

@ManagedBean
public class StudentsCriterionNotesBestBean {

	private BarChartModel barModel = new BarChartModel();;
	HashMap<String, Integer> criterionMap = new HashMap<String, Integer>();
	HashMap<String, Integer> occurenceMap = new HashMap<String, Integer>();
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private List<String> students;
	private String student;
	
	private TeamDAO teamDAO = new TeamDAO();
	private StudentDAO studentDAO = new StudentDAO();
	ChartSeries criterionBar = new ChartSeries();
	private Session session = DBConnection.getSession();

	public StudentsCriterionNotesBestBean() {
	}

	@PostConstruct
	public void init() {
		List<Student> allStudents = studentDAO.readAllStudent();
		students = new ArrayList<String>();
		for (Student student : allStudents) {
			students.add(student.getFirstname()+" "+student.getLastname()+ " "+student.getId());
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

							if (criterionMap.get(criterionName) > 10) {
								occurenceMap.put(criterionName, occurenceMap.get(criterionName) + 1);
								criterionMap.put(criterionName, (criterionMap.get(criterionName) + score));
							}
						}else {
							if (score > 10) {
								occurenceMap.put(criterionName, 1);
								criterionMap.put(criterionName, score / (occurenceMap.get(criterionName)));
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
