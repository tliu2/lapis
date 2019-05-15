package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Criterion;
import persistence.Evaluation;
import persistence.UniversityYear;

@ManagedBean(name = "criteriaService")
@ApplicationScoped
public class LinkEvaluationToProjectDAO {

	public List<Criterion> readAllCriterion() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Criterion");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

	public List<String> readAllCriterionName(List<Criterion> criteriaList) {
		List<String> criteriaNameList = new ArrayList<String>();
		for (Criterion criterion : criteriaList) {
			criteriaNameList.add(criterion.getName());
		}
		return criteriaNameList;
	}

	public List<Integer> makePercentageList() {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < 100; i++) {
			result.add(i);
		}
		return result;
	}
	
	public List<Evaluation> initEvaluationList() {
		List<Evaluation> evaList = new ArrayList<Evaluation>();
		for (int i = 0; i<1; i++) {
			Criterion crit = new Criterion("","");
			Evaluation eval = new Evaluation(crit,0);
			evaList.add(eval);
		}
		return evaList;
	}

}