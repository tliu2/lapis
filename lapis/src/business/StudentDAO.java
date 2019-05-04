package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Promotion;
import persistence.Student;

public class StudentDAO {

	public void createStudent(Promotion promotion, String firstname, String lastname, String ine, String ucpNumber) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Student student = new Student(firstname, lastname, ine, ucpNumber, promotion);
		session.persist(student);
		readTransaction.commit();

	}

}
