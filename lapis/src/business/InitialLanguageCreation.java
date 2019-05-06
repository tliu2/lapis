package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import persistence.Language;

public class InitialLanguageCreation {

	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}
	
	public static void generateLanguage(Session session) {
		List<Language> languageList = new ArrayList<Language>();

		Language java = new Language("Java");
		languageList.add(java);

		Language c = new Language("C");
		languageList.add(c);

		Language css = new Language("css");
		languageList.add(css);

		Language xhtml = new Language("XHTML");
		languageList.add(xhtml);

		Language xml = new Language("XML");
		languageList.add(xml);

		Language sql = new Language("SQL");
		languageList.add(sql);

		Language html = new Language("HTML");
		languageList.add(html);

		Language php = new Language("PHP");
		languageList.add(php);

		Language matlab = new Language("MATLAB");
		languageList.add(matlab);

		Language arduino = new Language("Arduino");
		languageList.add(arduino);

		Language openGl = new Language("OpenGL");
		languageList.add(openGl);

		Language android = new Language("Android");
		languageList.add(android);

		Language vba = new Language("VBA");
		languageList.add(vba);

		Language r = new Language("R");
		languageList.add(r);

		Language python = new Language("Python");
		languageList.add(python);

		Language drRacket = new Language("Dr.Racket");
		languageList.add(drRacket);

		Language tex = new Language("Tex");
		languageList.add(tex);

		Language lisp = new Language("Lisp");
		languageList.add(lisp);

		Language vhdl = new Language("VHDL");
		languageList.add(vhdl);

		Language scheme = new Language("SCHEME");
		languageList.add(scheme);

		Language cPlusPlus = new Language("C++");
		languageList.add(cPlusPlus);

		Language cSharp = new Language("C#");
		languageList.add(cSharp);

		Language shell = new Language("SHELL");
		languageList.add(shell);

		Language javaScript = new Language("JavaScript");
		languageList.add(javaScript);

		for (Language language : languageList) {
			session.persist(language);
		}
	}
	
	public static void main(String[] args) {

		createTables();
		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();
		
		generateLanguage(session);
		
		persistTransaction1.commit();
		session.close();
	}
	
}
