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
public class StudentScore {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Student.class)
	private Student student;
	private double finalScore;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = EvaluationScore.class)
	private List<EvaluationScore> scores = new ArrayList<EvaluationScore>();

	public StudentScore(Student student, double finalScore, List<EvaluationScore> scores) {
		this.student = student;
		this.scores = scores;
		this.finalScore = finalScore;
	}

	public int getId() {
		return id;
	}

	public List<EvaluationScore> getScores() {
		return scores;
	}

	public void setScores(List<EvaluationScore> scores) {
		this.scores = scores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

	public StudentScore() {
	}

}
