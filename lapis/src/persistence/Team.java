package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Team {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Project.class)
	private Project project;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Student.class)
	private List<Student> students = new ArrayList<Student>();
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = EvaluationScore.class)
	private List<EvaluationScore> scores = new ArrayList<EvaluationScore>();
	
	private double finalScore;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = StudentScore.class)
	private List<StudentScore> studentScores = new ArrayList<StudentScore>();

	public Team(Project project, List<Student> students, List<EvaluationScore> scores, double finalScore,
			List<StudentScore> studentScores) {
		this.project = project;
		this.students = students;
		this.scores = scores;
		this.finalScore = finalScore;
		this.studentScores = studentScores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<EvaluationScore> getScores() {
		return scores;
	}

	public void setScores(List<EvaluationScore> scores) {
		this.scores = scores;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

	public List<StudentScore> getStudentScores() {
		return studentScores;
	}

	public void setStudentScores(List<StudentScore> studentScores) {
		this.studentScores = studentScores;
	}

	public Team() {
	}
	

}
