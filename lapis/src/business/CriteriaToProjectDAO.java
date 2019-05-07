package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Criterion;
import persistence.UniversityYear;

public class CriteriaToProjectDAO {
	
	public List<Criterion> readAllCriterion() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Criterion");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

}
