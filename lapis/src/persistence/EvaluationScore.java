package persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EvaluationScore {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Evaluation.class)
	private Evaluation evaluation;
	private int score;
	private String description;
	
	public EvaluationScore(Evaluation evaluation, int score, String description) {
		this.evaluation = evaluation;
		this.score = score;
		this.description = description;
	}
	
	public EvaluationScore() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
