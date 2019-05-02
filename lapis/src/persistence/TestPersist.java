package persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestPersist {

	public static void main(String[] args) {
		DataInit.createTables();

		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();
		
		UniversityYear year = new UniversityYear(2019, 2020);
		session.persist(year);
		
		Promotion promotion = new Promotion(year, "Licence", 3);
		session.persist(promotion);
		
		Course course = new Course(promotion);
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
		
		Student student = new Student("Antoine", "Leguay", "9862498ZJ87Z", "21616900", promotion);
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
