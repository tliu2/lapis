package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.UniversityYear;

public class UniversityYearDAO {

	public List<UniversityYear> readAllUniversityYears() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from UniversityYear");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

	public int getIdFromUniversityYearString(String year) {
		int result = 0;
		Session session = DBConnection.getSession();
		String[] split = year.split("-");

		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from UniversityYear y where y.first = :first and y.last = :last");
		readQuery.setString("first", split[0]);
		readQuery.setString("last", split[1]);
		List resultQuery = readQuery.list();
		result = ((UniversityYear) resultQuery.get(0)).getId();
		readTransaction.commit();
		return result;
	}
}
