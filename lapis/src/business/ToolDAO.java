package business;

import java.util.List;

import org.hibernate.Query;
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
	
	public List<Tool> readAllTools() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Tool");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
}
 