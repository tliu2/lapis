package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
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
	
	public List<ToolContent> readAllToolContents() {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from ToolContent");
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}
} 
