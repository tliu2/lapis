package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import business.CourseDAO;
import business.DBConnection;
import business.PromotionDAO;
import business.StudentDAO;
import business.TeamDAO;
import business.UniversityYearDAO;
import persistence.Criterion;
import persistence.Evaluation;
import persistence.EvaluationScore;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;

@ManagedBean
public class StudentsCriterionNotesBestBean {

	private BarChartModel barModel;
	
	private TeamDAO teamDAO = new TeamDAO();
	private StudentDAO studentDAO = new StudentDAO();
	
	private Session session = DBConnection.getSession();

	public StudentsCriterionNotesBestBean() {
	}


	@PostConstruct
	public void init() {
		createBarModel();
	}

	public BarChartModel getBarModel() {
        return barModel;
    }
	
	private void createBarModel() {
        barModel = initBarModel();
 
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Criterion");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Note");
        yAxis.setMin(0);
        yAxis.setMax(20);
    }
	
	private BarChartModel initBarModel() {
	
		HashMap<String,Integer> criterionMap = new HashMap<String,Integer>();
		HashMap<String,Integer> occurenceMap = new HashMap<String,Integer>();
		BarChartModel model = new BarChartModel();
		ChartSeries criterionBar = new ChartSeries();
		List<Team> allTeamList = teamDAO.readAllTeam(session); 
		List<Team> teamList =  new ArrayList<Team>();
		Student student = (Student) (studentDAO.readStudentById(7,session).get(0));
		
		for(Team currentTeam : allTeamList) {
			if(currentTeam.getStudents().contains(student)) {
				teamList.add(currentTeam);
			}
		}
		
		for(Team team : teamList) {
			
			int index = 0;
			for(Student currentStudent : team.getStudents()) {
				
				index++;
				if(currentStudent.getIne() == student.getIne()) {
					
					List<StudentScore> studentScoreList = team.getStudentScores();
					StudentScore studentScore = studentScoreList.get(index-1); 
					List<EvaluationScore> evaluationScorelist = studentScore.getScores();
					criterionBar.setLabel(student.getFirstname() +' ' + student.getLastname());
					for(EvaluationScore evaluationScore : evaluationScorelist) {
						
			    	   Evaluation evaluation = evaluationScore.getEvaluation();
			    	   String criterionName =  evaluation.getCriterion().getName();
			    	   int score = evaluationScore.getScore();
				    	   if(criterionMap.containsKey(criterionName)) {
				    		   
				    		   if(criterionMap.get(criterionName) > 10) {
				    			   
					    		   occurenceMap.put(criterionName, occurenceMap.get(criterionName) +1);
					    		   criterionMap.put(criterionName, (criterionMap.get(criterionName) + score));
				    		   }
				    	   }
				    	   else {
				    		   if(score > 10) {
				    			   
					    		   occurenceMap.put(criterionName, 1);
					    		   criterionMap.put(criterionName, score /(occurenceMap.get(criterionName)));
				    		   }
				    	   }
			        }
				}
			}
		}
		
		for(String criterion : criterionMap.keySet())
			criterionBar.set(criterion, criterionMap.get(criterion));
        
		model.addSeries(criterionBar);
		return model;
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
