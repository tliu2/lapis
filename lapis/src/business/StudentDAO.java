package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.ProjectInfo;
import persistence.Promotion;
import persistence.Student;
import persistence.Team;

public class StudentDAO {

	public void createStudent(Promotion promotion, String firstname, String lastname, String ine, String ucpNumber) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Student student = new Student(firstname, lastname, ine, ucpNumber, promotion);
		session.persist(student);
		readTransaction.commit();

	}
	
	public List<Student> readAllStudent() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Student");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
	public List<Student> readStudentByPromoId(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Student s where s.promotion.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
	public List<Student> readStudentById(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Student s where s.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

	public void updateStudent(Student student, Session session) {
		Transaction updateTransaction = session.beginTransaction();
		session.update(student);
		session.flush();
		updateTransaction.commit();
	}

}
