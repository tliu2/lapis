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

	private BarChartModel barModel;
	
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

	
	
	private void createBarModel() {
		HashMap<String, Integer> criterionMap = new HashMap<String, Integer>();
		HashMap<String, Integer> occurenceMap = new HashMap<String, Integer>();
		 barModel = new BarChartModel();
		ChartSeries criterionBar = new ChartSeries();
		List<Team> allTeamList = teamDAO.readAllTeam(session);
		List<Team> teamList = new ArrayList<Team>();
		Student student = (Student) (studentDAO.readStudentById(7, session).get(0));

		for (Team currentTeam : allTeamList) {
			if (currentTeam.getStudents().contains(student)) {
				teamList.add(currentTeam);
			}
		}

		for (Team team : teamList) {

			int index = 0;
			for (Student currentStudent : team.getStudents()) {

				index++;
				if (currentStudent.getIne() == student.getIne()) {

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
						} else {
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
		
		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Criterion");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Note");
		yAxis.setMin(0);
		yAxis.setMax(20);
	}



	private void initBarModel(Student student) {
		//System.out.println(student.getFirstname()+student.getLastname()+student.getId());
		HashMap<String, Integer> criterionMap = new HashMap<String, Integer>();
		HashMap<String, Integer> occurenceMap = new HashMap<String, Integer>();
		barModel = new BarChartModel();
		
		List<Team> allTeamList = teamDAO.readAllTeam(session);
		List<Team> teamList = new ArrayList<Team>();

		for (Team currentTeam : allTeamList) {
			if (currentTeam.getStudents().contains(student)) {
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
						} else {
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
		
		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Criterion");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Note");
		yAxis.setMin(0);
		yAxis.setMax(20);
	}

	public void onChange() {
		if (student != null && !student.equals("")) {
			String[] parts = student.split(" ");
			String studentID = parts[2];
			
			List <Student> studentsList = studentDAO.readStudentById(Integer.parseInt(studentID));
			
			initBarModel(studentsList.get(0));
		} else {
			//promotions = new ArrayList<String>();
		}
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
