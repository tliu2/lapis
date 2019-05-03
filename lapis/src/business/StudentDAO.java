package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Promotion;
import persistence.UniversityYear;

public class StudentDAO {
	
	public List<Promotion> readPromotion(UniversityYear year) {
		System.out.println(year.toString());
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Promotion p where p.year=:year");
		readQuery.setEntity("year", year);
		List result = readQuery.list();
		readTransaction.commit();
		return result;
	}
}
