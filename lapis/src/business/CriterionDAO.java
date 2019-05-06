package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Criterion;

public class CriterionDAO {
	
	public void createCriterion(String name, String description) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Criterion criterion = new Criterion(name, description);
		session.persist(criterion);
		readTransaction.commit();
	}
	
}
