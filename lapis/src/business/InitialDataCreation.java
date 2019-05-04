package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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

public class InitialDataCreation {
	
	public static void createTables() {
		AnnotationConfiguration config = DBConnection.getConfig();
		SchemaExport schemaExport = new SchemaExport(config);
		schemaExport.create(true, true);
	}

	public static void main(String[] args) {
		createTables();

		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();
		
		int START_YEAR = 2000;
        int END_YEAR = 2100;
        List<UniversityYear> years = new ArrayList<UniversityYear>();
        for (int i = START_YEAR; i < END_YEAR; i++) {
            UniversityYear newYear = new UniversityYear(i, i + 1);
            years.add(newYear);
        }
        
        for (UniversityYear universityYear : years) {
            session.persist(universityYear);
        }

        Promotion promotion1 = new Promotion(years.get(0), "Licence", 1);
        Promotion promotion2 = new Promotion(years.get(0), "Licence", 2);
        Promotion promotion3 = new Promotion(years.get(0), "Licence", 3);
        Promotion promotion4 = new Promotion(years.get(0), "Master", 1);
        Promotion promotion5 = new Promotion(years.get(0), "Master", 2);
        session.persist(promotion1);
        session.persist(promotion2);
        session.persist(promotion3);
        session.persist(promotion4);
        session.persist(promotion5);
        
        int FIRST_LICENCE = 1;
        int LAST_LICENCE = 3;
        int FIRST_MASTER = 1;
        int LAST_MASTER = 2;
        List<Promotion> promos = new ArrayList<Promotion>();
        for (int j = 1; j< END_YEAR-START_YEAR; j++) {
            for (int i = FIRST_LICENCE; i <= LAST_LICENCE; i++) {
                Promotion newPromo = new Promotion(years.get(j), "Licence", i);
                promos.add(newPromo);
            }
            for (int i = FIRST_MASTER; i <= LAST_MASTER; i++) {
                Promotion newPromo = new Promotion(years.get(j), "Master", i);
                promos.add(newPromo);
            }
        }
        for (Promotion promotion : promos) {
            session.persist(promotion);
        }
		
		Course course = new Course(promos.get(0), "GLP");
		session.persist(course);
		
		Criterion criterion = new Criterion("Qualité", "Bien ou pas bien");
		session.persist(criterion);
		
		Evaluation evaluation = new Evaluation(criterion,40);
		List<Evaluation> evaluationList = new ArrayList<Evaluation>();
		evaluationList.add(evaluation);
		
		Project project = new Project("Jeu de Go", "Description rapide", course, evaluationList, 2, 3, 15);
		session.persist(project);
		
		Domain domain = new Domain("Info");
		session.persist(domain);
		List<Domain> domainList = new ArrayList<Domain>();
		domainList.add(domain);

		Language language = new Language("Java");
		session.persist(language);
		List<Language> languageList = new ArrayList<Language>();
		languageList.add(language);
		
		Tool tool = new Tool("IDE");
		session.persist(tool);
		
		ToolContent toolContent = new ToolContent(tool, "Eclipse");
		session.persist(toolContent);
		List<ToolContent> toolContentList = new ArrayList<ToolContent>();

		ProjectInfo projectInfo = new ProjectInfo(project, "Tianxiao Liu", true, domainList, languageList, toolContentList, "Detailed Description");
		session.persist(projectInfo);
		
		Student student = new Student("Antoine", "Leguay", "9862498ZJ87Z", "21616900", promos.get(0));
		session.persist(student);
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(student);
		
		StudentScore studentScore = new StudentScore(student, 17);
		session.persist(studentScore);
		List<StudentScore> studentScoreList = new ArrayList<StudentScore>();
		
		EvaluationScore evaluationScore = new EvaluationScore(evaluation, 12);
		List<EvaluationScore> evaluationScoreList = new ArrayList<EvaluationScore>();
		
		Team team = new Team(project, studentList, evaluationScoreList, 17, studentScoreList);
		session.persist(team);
		
		persistTransaction1.commit();

		session.close();

	}
}
