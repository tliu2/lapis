package business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import persistence.Course;
import persistence.Criterion;
import persistence.Domain;
import persistence.Evaluation;
import persistence.EvaluationScore;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;
import persistence.Tool;
import persistence.ToolContent;
import persistence.UniversityYear;

public class DBConnection {
	private static SessionFactory sessionFactory;
	private static AnnotationConfiguration config;

	public static AnnotationConfiguration getConfig() {
		if (config == null) {
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(Course.class);
			config.addAnnotatedClass(Criterion.class);
			config.addAnnotatedClass(Domain.class);
			config.addAnnotatedClass(Evaluation.class);
			config.addAnnotatedClass(EvaluationScore.class);
			config.addAnnotatedClass(Language.class);
			config.addAnnotatedClass(Project.class);
			config.addAnnotatedClass(ProjectInfo.class);
			config.addAnnotatedClass(Promotion.class);
			config.addAnnotatedClass(Student.class);
			config.addAnnotatedClass(StudentScore.class);
			config.addAnnotatedClass(Team.class);
			config.addAnnotatedClass(Tool.class);
			config.addAnnotatedClass(ToolContent.class);
			config.addAnnotatedClass(UniversityYear.class);
			
			String packageName = DBConnection.class.getPackage().getName();
			config.configure(packageName + "/connection.cfg.xml");
		}
		return config;
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				AnnotationConfiguration config = getConfig();
				sessionFactory = config.buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
