package business;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Criterion;
import persistence.Evaluation;

public class EvaluationDAO {
	
	public void persistEvaluation(List<Evaluation> evaluationList) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		for (Evaluation evaluation : evaluationList) {
			session.persist(evaluation);
		}
		readTransaction.commit();
	}

}
