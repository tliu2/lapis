package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import persistence.Tool;

public class InitialToolCreation {
	
	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	public static void generateToolsAndToolContent(Session session) {
		List<Tool> toolList = new ArrayList<Tool>();
		Tool ide = new Tool("IDE");
		toolList.add(ide);
		Tool ltt = new Tool("Logiciel de traitement de texte");
		toolList.add(ltt);
		Tool lgv = new Tool("Logiciel de gestion de versions");
		toolList.add(lgv);
		Tool lgp = new Tool("Logiciel de gestion de projet");
		toolList.add(lgp);
		Tool lc = new Tool("Logiciel de communication");
		toolList.add(lc);
		for (Tool tool : toolList) {
			session.persist(tool);
		}
	}

	public static void main(String[] args) {

		createTables();
		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();
		
		generateToolsAndToolContent(session);
		
		persistTransaction1.commit();
		session.close();
	}
	
}
