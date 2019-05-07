package business;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.EvaluationScore;
import persistence.Project;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;

public class TeamDAO {
	public void createTeam(Project project, List<Student> students, List<EvaluationScore> scores, double finalScore,
			List<StudentScore> studentScores) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Team team = new Team(project, students, scores, finalScore, studentScores);
		session.persist(team);
		readTransaction.commit();

	}
	
	public void createEmptyTeam(Project project, int amount, int min, int max) {
		if(amount >= min && amount <= max) {
			Session session = DBConnection.getSession();
			Transaction readTransaction = session.beginTransaction();
			for(int i=0; i<max; i++) {
				Team team = new Team(project);
				session.persist(team);
			}
			readTransaction.commit();
		}
		

	}
	
	
}
