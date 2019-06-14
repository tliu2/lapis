package beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import business.ProjectInfoDAO;
import business.StudentScoreDAO;
import business.UniversityYearDAO;
import persistence.UniversityYear;

@ManagedBean
public class HofOccurenceByYearBean {

	private LineChartModel lineModel;
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private List<String> students;

	private String student;
	
	private UniversityYearDAO universityYearDAO = new UniversityYearDAO();
	private ProjectInfoDAO projectInfoDAO = new ProjectInfoDAO();
	private StudentScoreDAO studentScoreDAO = new StudentScoreDAO();


	public HofOccurenceByYearBean() {
		createLineModel();
	}

	private void createLineModel() {
        lineModel = initCategoryModel();
        lineModel.setTitle("hof Occurence By Year");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("nb of Occurence");
       
    }
	
	private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
        List<UniversityYear> universityYearList = universityYearDAO.readAllUniversityYears();
       
        
        ChartSeries hof = new ChartSeries();
        
        hof.setLabel("Hof rate");
        for(UniversityYear universityYear : universityYearList) {
        	hof.set(universityYear.getFirst(), projectInfoDAO.getProjectInfoHofByYear(universityYear.getFirst()).size());
        }

 
        model.addSeries(hof);
 
        return model;
    }
	

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
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

	public ProjectInfoDAO getProjectInfoDAO() {
		return projectInfoDAO;
	}

	public void setProjectInfoDAO(ProjectInfoDAO projectInfoDAO) {
		this.projectInfoDAO = projectInfoDAO;
	}

	public UniversityYearDAO getUniversityYearDAO() {
		return universityYearDAO;
	}

	public void setUniversityYearDAO(UniversityYearDAO universityYearDAO) {
		this.universityYearDAO = universityYearDAO;
	}

	public StudentScoreDAO getStudentScoreDAO() {
		return studentScoreDAO;
	}

	public void setStudentScoreDAO(StudentScoreDAO studentScoreDAO) {
		this.studentScoreDAO = studentScoreDAO;
	}

	
}
