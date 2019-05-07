package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Evaluation;
import persistence.Language;
import persistence.Project;
import persistence.Promotion;
import persistence.Student;

public class LanguageDAO {
	
	public List<Language> readAllLanguages() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Language");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
}
