package business;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.DBConnection;
import persistence.UniversityYear;

public class PromotionCreation {

	@SuppressWarnings("rawtypes")
	public static List<UniversityYear> testWhereClause() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from UniversityYear");
		List result = readQuery.list();
		Iterator iterator = result.iterator();
		while (iterator.hasNext()) {
			UniversityYear universityYear = (UniversityYear) iterator.next();
			System.out.println(universityYear.toString());
		}
		readTransaction.commit();
		
		return result;
	}
}
