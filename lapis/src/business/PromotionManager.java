package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.DBConnection;
import persistence.Promotion;
import persistence.UniversityYear;

public class PromotionManager {


	public List<Promotion> readPromotionFromUniversityYear(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Promotion p where p.year.id = :"+id);
		List result = readQuery.list(); 
		readTransaction.commit();
		
		
		return result;
	}
	
	public int getIdFromUniversityYearString(String year) {
		int result = 0;
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from UniversityYear p where p.year = :"+year);
		List resultQuery = readQuery.list();
		result = ((UniversityYear)resultQuery.get(0)).getId();
		readTransaction.commit();
		return result;
	}
}
