package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Criterion;
import persistence.Evaluation;

public class EvaluationDAO {

	public void createEvaluation(List<Criterion> critList, List<Integer> percentages) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		int length = critList.size();
		for (int i = 0; i<length; i++) {
			Evaluation evaluation = new Evaluation(critList.get(i), percentages.get(i));
			session.persist(evaluation);
		}
		readTransaction.commit();
	}

}
