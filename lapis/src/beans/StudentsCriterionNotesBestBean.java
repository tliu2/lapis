package beans;

import java.util.ArrayList;
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
		
        BarChartModel model = new BarChartModel();
        
       List<Team> teamlist = teamDAO.readTeamById(1, session); 
       Team team = teamlist.get(0);//pour le moment la team 1
       
       
       List<Student> studentList = team.getStudents();
       Student student = studentList.get(0);
       List<StudentScore> studentScoreList = team.getStudentScores();
       StudentScore studentscore = studentScoreList.get(0);//pour le moment le premier student
       
       List<EvaluationScore> evaluationScorelist = studentscore.getScores();
        
       ChartSeries criterionBar = new ChartSeries();
	   criterionBar.setLabel(student.getFirstname() +' ' + student.getLastname());
	   
       for(EvaluationScore evaluationScore : evaluationScorelist) {
    	   
    	   Evaluation evaluation = evaluationScore.getEvaluation();
    	   
    	   String criterionName =  evaluation.getCriterion().getName();
    	   int score = evaluationScore.getScore();
    	   
           criterionBar.set(criterionName, score);
         
       }
       
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
