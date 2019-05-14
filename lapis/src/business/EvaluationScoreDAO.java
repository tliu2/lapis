package business;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EvaluationScoreDAO {

	public boolean updateEvaluationScoreWithId(int id, int score, String description) {
		Session session = DBConnection.getSession();

		// description
		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session
				.createQuery("update EvaluationScore es set es.description = :description where es.id = :id");
		updateQuery.setInteger("id", id);
		updateQuery.setString("description", description);
		updateQuery.executeUpdate();
		updateTransaction.commit();

		// score
		Transaction updateTransaction1 = session.beginTransaction();
		Query updateQuery1 = session.createQuery("update EvaluationScore es set es.score = :score where es.id = :id");
		updateQuery1.setInteger("id", id);
		updateQuery1.setInteger("score", score);
		updateQuery1.executeUpdate();
		updateTransaction1.commit();

		return true;
	}
}
