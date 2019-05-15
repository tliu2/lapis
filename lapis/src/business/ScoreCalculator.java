package business;

import java.util.List;

import persistence.StudentScore;
import persistence.Team;

public class ScoreCalculator {

	public double teamScore(Team team) {
		List<StudentScore> studentScores = team.getStudentScores();
		double finalScore = 0;
		for(StudentScore studentScore: studentScores) {
			finalScore += studentScore.getFinalScore();
		}
		return finalScore;
	}
}
