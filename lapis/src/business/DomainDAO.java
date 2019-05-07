package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Domain;
import persistence.UniversityYear;

public class DomainDAO {

	public void createDomain(String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Domain domain = new Domain(name);
		session.persist(domain);
		readTransaction.commit();
	}
	
	public List<Domain> readAllDomains() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Domain");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

}
