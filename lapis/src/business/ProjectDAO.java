package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Evaluation;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;

public class ProjectDAO {

	public void createProject(String subject, String description, Course course, int minEtu, int maxEtu, int maxTeam, List<Evaluation> evaluations) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Project project = new Project(subject, description, course, evaluations, minEtu, maxEtu, maxTeam);
		session.persist(project);
		readTransaction.commit();

	}

	public List<Project> readProjectByCourseId(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Project p where p.course.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
}
