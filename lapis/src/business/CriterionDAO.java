package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Criterion;
import persistence.ProjectInfo;

public class CriterionDAO {
	
	public void createCriterion(String name, String description) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Criterion criterion = new Criterion(name, description);
		session.persist(criterion);
		readTransaction.commit();
	}
	
	public List<Criterion> readAllCriterion() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Criterion");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
	public List<Criterion> getCriterionByName(String name) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from Criterion c where c.name = :name");
		readQuery.setString("name", name);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
	
	public void updateDescription(String description, String name) {
		Session session = DBConnection.getSession();
		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session.createQuery("update Criterion c set c.description = :description where c.name=:name");
		updateQuery.setString("description", description);
		updateQuery.setString("name", name);
		updateQuery.executeUpdate();
		updateTransaction.commit();
	}
}
