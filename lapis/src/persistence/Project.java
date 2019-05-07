package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project {

	@Id
	@GeneratedValue
	private int id;
	private String subject;
	private String description;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Course.class)
	private Course course;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Evaluation.class)
	private List<Evaluation> evaluation = new ArrayList<Evaluation>();
	
	private int minStudentCount;
	private int maxStudentCount;
	private int maxTeamCount;
	public Project(String subject, String description, Course course, List<Evaluation> evaluation,
			int minStudentCount, int maxStudentCount, int maxTeamCount) {
		this.subject = subject;
		this.description = description;
		this.course = course;
		this.evaluation = evaluation;
		this.minStudentCount = minStudentCount;
		this.maxStudentCount = maxStudentCount;
		this.maxTeamCount = maxTeamCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Evaluation> getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(List<Evaluation> evaluation) {
		this.evaluation = evaluation;
	}
	public int getMinStudentCount() {
		return minStudentCount;
	}
	public void setMinStudentCount(int minStudentCount) {
		this.minStudentCount = minStudentCount;
	}
	public int getMaxStudentCount() {
		return maxStudentCount;
	}
	public void setMaxStudentCount(int maxStudentCount) {
		this.maxStudentCount = maxStudentCount;
	}
	public int getMaxTeamCount() {
		return maxTeamCount;
	}
	public void setMaxTeamCount(int maxTeamCount) {
		this.maxTeamCount = maxTeamCount;
	}
	
	public String toString() {
		return getId()+"-"+getSubject();
	}
	public Project() {
	}
	
	

}
