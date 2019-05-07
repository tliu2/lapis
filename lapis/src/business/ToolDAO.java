package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Tool;

public class ToolDAO {

	public void createTool(String category) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Tool tool = new Tool(category);
		session.persist(tool);
		readTransaction.commit();
	}
}
