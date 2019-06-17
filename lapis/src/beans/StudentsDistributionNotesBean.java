package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

import business.PromotionDAO;
import business.StudentDAO;
import business.StudentScoreDAO;
import business.UniversityYearDAO;
import persistence.Course;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.UniversityYear;

@ManagedBean
public class StudentsDistributionNotesBean {
	
	private PieChartModel pieModel;
	
	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataStudent = new HashMap<String, List<String>>();
	
	private List<String> students = new ArrayList<String>();

	private String student;
	
	private String year;
	private String promo;

	private List<String> years = new ArrayList<String>();
	private List<String> promotions = new ArrayList<String>();
	
	private UniversityYearDAO yearDAO = new UniversityYearDAO();
	private PromotionDAO promoDAO = new PromotionDAO();
	
	private StudentDAO studentDAO = new StudentDAO();
	private StudentScoreDAO studentScoreDAO = new StudentScoreDAO();

	public StudentsDistributionNotesBean() {
	}


	@PostConstruct
	public void init() {
		/*List<Student> allStudents = studentDAO.readAllStudent();
		students = new ArrayList<String>();
		for (Student student : allStudents) {
			students.add(student.getFirstname()+" "+student.getLastname()+ " "+student.getId());
		}
		*/
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
				System.out.println(student.getFirstname());
			}
			dataStudent.put(promotion.toString(), studentList);
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
	
	public void onChangeUY() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
			System.out.println(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}
	
	public void onChangePromo() {
		if (promo != null && !promo.equals("")) {
			students = dataStudent.get(promo.toString());
			System.out.println(promo.toString() +"   -    "+promo);
		} else {
			students = new ArrayList<String>();
			System.out.println("raté");
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

}
