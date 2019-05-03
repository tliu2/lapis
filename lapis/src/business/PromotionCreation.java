package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.DBConnection;
import persistence.Promotion;
import persistence.UniversityYear;

public class PromotionCreation {

	public List<UniversityYear> readUniversityYears() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from UniversityYear");
		List result = readQuery.list();
		readTransaction.commit();
		
		return result;
	}
	
	public void createPromotion(UniversityYear universityYear, String diplomaName, int level) {
		System.out.println("Start creation !");
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Promotion promotion = new Promotion(universityYear, diplomaName, level);
		session.persist(promotion);
		readTransaction.commit();
	}
}
