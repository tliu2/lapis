package business;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Tool;
import persistence.ToolContent;

public class ToolContentDAO {

	public void createToolContent(String name, Tool tool) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		ToolContent toolcontent = new ToolContent(tool, name);
		session.persist(toolcontent);
		readTransaction.commit();
	}
}
