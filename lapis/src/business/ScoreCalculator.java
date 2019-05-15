package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.EvaluationScore;
import persistence.StudentScore;
import persistence.Team;

public class ScoreCalculator {
	

	public double teamScore(Team team) {
		TeamDAO teamDAO = new TeamDAO();
		
	        List<StudentScore> studentScores = team.getStudentScores();
	        double finalScore = 0;
	        for(StudentScore studentScore: studentScores) {
	            finalScore += studentScore.getFinalScore();
	        }
	        int teamID = team.getId();
	        teamDAO.updateFinalScore(finalScore, teamID);
	        return finalScore;
	    }
	
	public void setTeamStudentsFinalScore(Team team) {
		List<StudentScore> studentScores = team.getStudentScores();
		
		for(StudentScore studentScore : studentScores){
			float finalScore=0;
			
			for(EvaluationScore evaluationScore : studentScore.getScores()) {
				finalScore += (evaluationScore.getScore()*evaluationScore.getEvaluation().getPercentage()/100);
			}
			
			persistStudentScoreFinalScore(studentScore.getId(), finalScore);
		}
	}
	
	public void persistStudentScoreFinalScore(int id, float finalScore) {
		Session session = DBConnection.getSession();

		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session
				.createQuery("update StudentScore ss set ss.finalScore = :finalScore where ss.id = :id");
		updateQuery.setInteger("id", id);
		updateQuery.setFloat("finalScore", finalScore);
		updateQuery.executeUpdate();
		updateTransaction.commit();
	}
	
}
