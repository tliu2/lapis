package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Promotion;
import persistence.UniversityYear;

public class CourseDAO {
	public void createCourse(Promotion promotion, String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		System.out.println("Try to persist course");
		Course course = new Course(promotion, name);
		session.persist(course);
		readTransaction.commit();
	}

}
