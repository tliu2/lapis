package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Promotion;
import persistence.UniversityYear;

public class PromotionDAO {

	public void createPromotion(UniversityYear universityYear, String diplomaName, int level) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Promotion promotion = new Promotion(universityYear, diplomaName, level);
		session.persist(promotion);
		readTransaction.commit();
	}

	public List<Promotion> readPromoByYearId(int id) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Promotion p where p.year.id = :id");
		readQuery.setInteger("id", id);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
	public List<Promotion> readAllPromo() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Promotion");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

	public int getIdFromPromotionString(String promo) {
		int result = 0;
		Session session = DBConnection.getSession();
		String[] split = promo.split(" ");
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Promotion p where p.diplomaName = :diplomaName and p.level =:level");
		readQuery.setString("diplomaName", split[0]);
		readQuery.setString("level", split[1]);
		List resultQuery = readQuery.list();
		result = ((Promotion) resultQuery.get(0)).getId();
		readTransaction.commit();
		return result;
	}
}
