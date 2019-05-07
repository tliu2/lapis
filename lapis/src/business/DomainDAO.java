package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Domain;

public class DomainDAO {

	public void createDomain(String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Domain domain = new Domain(name);
		session.persist(domain);
		readTransaction.commit();
	}

}
