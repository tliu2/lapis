package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Domain;
import persistence.Language;

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
	
	public List<Domain> readDomainByName(String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Domain d where d.name = :name");
		readQuery.setString("name", name);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

}
