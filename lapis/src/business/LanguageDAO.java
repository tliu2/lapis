package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Language;

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
