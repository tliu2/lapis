package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.EvaluationScore;
import persistence.Project;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;

public class TeamDAO {
	public void createTeam(Project project, List<Student> students, double finalScore,
			List<StudentScore> studentScores, String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Team team = new Team(project, students, finalScore, studentScores, name);
		session.persist(team);
		readTransaction.commit();
		session.close();
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
			session.close();
		}
		
	}
	
	public List<Team> readTeamByProjectId(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Team t where t.project.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();
		session.close();

		return result;
	}
	

	public List<Team> readTeamById(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Team t where t.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();
		session.close();
		
		return result;
	}
	
	public void updateTeamMembers(Team team) {
		Session session = DBConnection.getSession();
		Transaction updateTransaction = session.beginTransaction();
		session.update(team);
		session.flush();
		updateTransaction.commit();
	}
	
	public void updateFinalScore(Double finalScore, int teamID) {
		Session session = DBConnection.getSession();
		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session.createQuery("update Team t set t.finalScore = :finalScore where t.id=:teamID");
		updateQuery.setDouble("finalScore", finalScore);
		updateQuery.setInteger("teamID", teamID);
		updateQuery.executeUpdate();
		updateTransaction.commit();
		session.close();
	}
	
	public List<Team> readAllTeam() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Team");
		List result = readQuery.list();
		readTransaction.commit();
		session.close();

		return result;
	}
	
	
	////////////////////////////////////////////WITH SESSION 
	
	public void createTeam(Project project, List<Student> students, double finalScore,
			List<StudentScore> studentScores, String name, Session session) {
		Transaction readTransaction = session.beginTransaction();
		Team team = new Team(project, students, finalScore, studentScores, name);
		session.persist(team);
		readTransaction.commit();
	}
	
	public void createEmptyTeam(Project project, int amount, int min, int max, Session session) {
		if(amount >= min && amount <= max) {
			Transaction readTransaction = session.beginTransaction();
			for(int i=0; i<max; i++) {
				Team team = new Team(project);
				session.persist(team);
			}
			readTransaction.commit();
		}
		
	}
	
	public List<Team> readTeamByProjectId(int id, Session session) {
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Team t where t.project.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	

	public List<Team> readTeamById(int id, Session session) {
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Team t where t.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();
		
		return result;
	}
	
	public void updateTeamMembers(Team team,Session session) {
		Transaction updateTransaction = session.beginTransaction();
		session.update(team);
		session.flush();
		updateTransaction.commit();
	}
	
	public void updateFinalScore(Double finalScore, int teamID, Session session) {
		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session.createQuery("update Team t set t.finalScore = :finalScore where t.id=:teamID");
		updateQuery.setDouble("finalScore", finalScore);
		updateQuery.setInteger("teamID", teamID);
		updateQuery.executeUpdate();
		updateTransaction.commit();
	}
	
	public List<Team> readAllTeam(Session session) {
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Team");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
}
