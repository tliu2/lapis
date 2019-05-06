package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Promotion;
import persistence.UniversityYear;

public class CourseDAO {
	public void createCourse(Promotion promotion, String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Course course = new Course(promotion, name);
		session.persist(course);
		readTransaction.commit();
	}
	
	public List<Course> readCourseByPromoId(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Course c where c.promotion.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
}
