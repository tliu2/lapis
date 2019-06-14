package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import business.CourseDAO;
import business.CriteriaToProjectDAO;
import business.PromotionDAO;
import business.StudentDAO;
import business.StudentScoreDAO;
import business.UniversityYearDAO;
import persistence.Course;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;
import persistence.UniversityYear;

@ManagedBean
public class StudentsDistributionNotesBean {
	
	private PieChartModel pieModel;
	
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private List<String> students;

	private String student;
	
	private StudentDAO studentDAO = new StudentDAO();
	private StudentScoreDAO studentScoreDAO = new StudentScoreDAO();

	public StudentsDistributionNotesBean() {
	}


	@PostConstruct
	public void init() {
		List<Student> allStudents = studentDAO.readAllStudent();
		students = new ArrayList<String>();
		for (Student student : allStudents) {
			students.add(student.getFirstname()+" "+student.getLastname()+ " "+student.getId());
		}
		
		createPieModel();
	}
	
	public void onChange() {
		if (student != null && !student.equals("")) {
			String[] parts = student.split(" ");
			String studentID = parts[2];
			
			List <Student> studentsList = studentDAO.readStudentById(Integer.parseInt(studentID));
			
			createPieWithStudent(studentsList.get(0));
		} else {
			//promotions = new ArrayList<String>();
		}
	}
	

    private void createPieModel() {
        pieModel = new PieChartModel();
 
        pieModel.set("Default", 0);
 
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }
    
    private void createPieWithStudent(Student student) {
    	 pieModel = new PieChartModel();
    	 int first=0;
    	 int second=0;
    	 int third=0;
    	 int fourth=0;
    	 
    	 List<StudentScore> studentScoreList = studentScoreDAO.getStudentScoreListOfStudent(student);
    	 
    	 for(StudentScore studentScore : studentScoreList) {
    		if(studentScore.getFinalScore()<=5)
    			first ++;
    		else if(studentScore.getFinalScore()<=10)
    			second++;
    		else if(studentScore.getFinalScore()<=15)
    			third++;
    		else fourth++;
    	 }
         pieModel.set("<=5", first);
         pieModel.set("<=10", second);
         pieModel.set("<=15", third);
         pieModel.set("<=20", fourth);
  
         pieModel.setLegendPosition("w");
         pieModel.setShadow(false);
    	
    }
	
    public PieChartModel getPieModel() {
        return pieModel;
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


	public void setStudents(List<String> students) {
		this.students = students;
	}


	public String getStudent() {
		return student;
	}


	public void setStudent(String student) {
		this.student = student;
	}


	public StudentDAO getStudentDAO() {
		return studentDAO;
	}


	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}


	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

}
