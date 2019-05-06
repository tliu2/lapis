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

	public static void InitTestData() {

		createTables();
		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();

		int START_YEAR = 2000;
		int END_YEAR = 2003;

		List<UniversityYear> years = new ArrayList<UniversityYear>();
		List<Promotion> promos = new ArrayList<Promotion>();
		List<Student> studentList = new ArrayList<Student>();
		List<Evaluation> evaluationList1 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList2 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList3 = new ArrayList<Evaluation>();
		List<Domain> domainList1 = new ArrayList<Domain>();
		List<Domain> domainList2 = new ArrayList<Domain>();
		List<Domain> domainList3 = new ArrayList<Domain>();
		List<ToolContent> toolContentList1 = new ArrayList<ToolContent>();
		List<ToolContent> toolContentList2 = new ArrayList<ToolContent>();
		List<ToolContent> toolContentList3 = new ArrayList<ToolContent>();
		List<StudentScore> studentScoreList = new ArrayList<StudentScore>();
		List<EvaluationScore> evaluationScoreList = new ArrayList<EvaluationScore>();
		List<Language> languageList1 = new ArrayList<Language>();
		List<Language> languageList2 = new ArrayList<Language>();
		List<Language> languageList3 = new ArrayList<Language>();

		// University Year
		PersistUniversityYear(START_YEAR, END_YEAR, session, years);

		// Promotion
		Promotion promotion = new Promotion(years.get(0), "Master", 1);
		Promotion promotion1 = new Promotion(years.get(0), "Master", 2);
		Promotion promotion2 = new Promotion(years.get(1), "Licence", 2);
		Promotion promotion3 = new Promotion(years.get(1), "Licence", 3);
		promos.add(promotion);
		promos.add(promotion1);
		promos.add(promotion2);
		promos.add(promotion3);

		for (Promotion promotionx : promos) {
			session.persist(promotionx);
		}

		// Course
		Course course = new Course(promos.get(0), "GLP");
		Course course1 = new Course(promos.get(1), "COO");
		Course course2 = new Course(promos.get(1), "GP");

		session.persist(course);
		session.persist(course1);
		session.persist(course2);

		// Students of 1st promotion

		Student student1 = new Student("Oumaima", "MOUTAWADII", "1862498ZJ87Z", "217150361", promos.get(0));
		studentList.add(student1);
		session.persist(student1);

		Student student2 = new Student("Samir", "MIMOUNI", "2862498ZJ87Z", "217150362", promos.get(0));
		studentList.add(student2);
		session.persist(student2);

		Student student3 = new Student("Hicham", "MIMOUNI", "3862498ZJ87Z", "217150363", promos.get(0));
		studentList.add(student3);
		session.persist(student3);

		Student student4 = new Student("Touka", "MEROUANI", "4862498ZJ87Z", "217150364", promos.get(0));
		studentList.add(student4);
		session.persist(student4);

		Student student5 = new Student("Ludovic", "DOMINGUES", "5862498ZJ87Z", "217150365", promos.get(0));
		studentList.add(student5);
		session.persist(student5);

		Student student6 = new Student("Antoine", "LEGUAY", "6862498ZJ87Z", "217150366", promos.get(0));
		studentList.add(student6);
		session.persist(student6);

		Student student7 = new Student("Mathieu", "Voisin", "7862498ZJ87Z", "217150367", promos.get(0));
		studentList.add(student7);
		session.persist(student7);

		Student student8 = new Student("Elisa", "BOUGEROT", "8862498ZJ87Z", "217150368", promos.get(0));
		studentList.add(student8);
		session.persist(student8);

		Student student9 = new Student("Sonia", "LAIB", "9862498ZJ87Z", "217150369", promos.get(0));
		studentList.add(student9);
		session.persist(student9);

		Student student10 = new Student("Nicky", "GAO", "1062498ZJ87Z", "217150310", promos.get(0));
		studentList.add(student10);
		session.persist(student10);

		// student of 2nd promotion

		Student student11 = new Student("Leila", "SAMIA", "1162498ZJ87Z", "217150311", promos.get(1));
		studentList.add(student11);
		session.persist(student11);

		Student student12 = new Student("Manal", "FAOUZI", "1262498ZJ87Z", "217150312", promos.get(1));
		studentList.add(student12);
		session.persist(student12);

		Student student13 = new Student("Cindy", "HU", "1362498ZJ87Z", "217150313", promos.get(1));
		studentList.add(student13);
		session.persist(student13);

		Student student14 = new Student("Cecile", "SOUSCE", "1462498ZJ87Z", "217150314", promos.get(1));
		studentList.add(student14);
		session.persist(student14);

		Student student15 = new Student("Monis", "BEN NAJIM", "1562498ZJ87Z", "217150315", promos.get(1));
		studentList.add(student15);
		session.persist(student15);

		Student student16 = new Student("Bilal", "GERARD", "1662498ZJ87Z", "217150316", promos.get(1));
		studentList.add(student16);
		session.persist(student16);

		Student student17 = new Student("Celine", "NUKOVSKI", "1762498ZJ87Z", "217150317", promos.get(1));
		studentList.add(student17);
		session.persist(student17);

		Student student18 = new Student("Ishan", "ARSMOUK", "1862498ZJ87Z", "217150318", promos.get(1));
		studentList.add(student18);
		session.persist(student18);

		Student student19 = new Student("Ishak", "AYAD", "1962498ZJ87Z", "217150319", promos.get(1));
		studentList.add(student19);
		session.persist(student19);

		Student student20 = new Student("Emma", "BELAIR", "2062498ZJ87Z", "217150320", promos.get(1));
		studentList.add(student20);
		session.persist(student20);

		Student student21 = new Student("Kevin", "PAKPAK", "2162498ZJ87Z", "217150321", promos.get(1));
		studentList.add(student21);
		session.persist(student21);

		Student student22 = new Student("Amin", "AMARA", "2262498ZJ87Z", "217150322", promos.get(1));
		studentList.add(student22);
		session.persist(student22);

		Student student23 = new Student("Lucas", "DELLEVALE", "2362498ZJ87Z", "217150323", promos.get(1));
		studentList.add(student23);
		session.persist(student23);

		Student student24 = new Student("Floriant", "BOURLAT", "2462498ZJ87Z", "217150324", promos.get(1));
		studentList.add(student24);
		session.persist(student24);

		Student student25 = new Student("Haddis", "RUGA", "2562498ZJ87Z", "217150325", promos.get(1));
		studentList.add(student25);
		session.persist(student25);

		Student student26 = new Student("Sabrina", "LOPES", "2662498ZJ87Z", "217150326", promos.get(1));
		studentList.add(student26);
		session.persist(student26);

		Student student27 = new Student("Valentine", "SARRAZIN", "2762498ZJ87Z", "217150327", promos.get(1));
		studentList.add(student27);
		session.persist(student27);

		Student student28 = new Student("Amir", "MERAKCHI", "2862498ZJ87Z", "217150328", promos.get(1));
		studentList.add(student28);
		session.persist(student28);

		Student student29 = new Student("Claire", "BERANGER", "2962498ZJ87Z", "217150329", promos.get(1));
		studentList.add(student29);
		session.persist(student29);

		Student student30 = new Student("Samira", "ARHAB", "3062498ZJ87Z", "217150330", promos.get(1));
		studentList.add(student30);
		session.persist(student30);

		// Criterion

		Criterion criterion1 = new Criterion("Fonctionnalities", "Well or not ?");
		session.persist(criterion1);
		Criterion criterion2 = new Criterion("Fluidity", "Well or not ?");
		session.persist(criterion2);
		Criterion criterion3 = new Criterion("Usage of the tools imposed", "Well or not ?");
		session.persist(criterion3);
		Criterion criterion4 = new Criterion("Comment", "Well or not ?");
		session.persist(criterion4);
		Criterion criterion5 = new Criterion("Facility of re-use", "Well or not ?");
		session.persist(criterion5);
		Criterion criterion6 = new Criterion("Structure of the code", "Well or not ?");
		session.persist(criterion6);
		Criterion criterion7 = new Criterion("Usage of the course notions", "Well or not ?");
		session.persist(criterion7);
		Criterion criterion8 = new Criterion("Generation of traces", "Well or not ?");
		session.persist(criterion8);
		Criterion criterion9 = new Criterion("Gestion of the exceptions", "Well or not ?");
		session.persist(criterion9);
		Criterion criterion10 = new Criterion("Ergonomic", "Well or not ?");
		session.persist(criterion10);
		Criterion criterion11 = new Criterion("Esthetical", "Well or not ?");
		session.persist(criterion11);
		Criterion criterion12 = new Criterion("Mini demonstration", "Well or not ?");
		session.persist(criterion12);
		Criterion criterion13 = new Criterion("Structure of the report", "Well or not ?");
		session.persist(criterion13);
		Criterion criterion14 = new Criterion("UML Diagram", "Well or not ?");
		session.persist(criterion14);
		Criterion criterion15 = new Criterion("Presence, ponctuality, assiduity & participation", "Well or not ?");
		session.persist(criterion15);

		// Evaluation

		// project1
		Evaluation evaluation11 = new Evaluation(criterion1, 20);
		evaluationList1.add(evaluation11);
		Evaluation evaluation12 = new Evaluation(criterion2, 20);
		evaluationList1.add(evaluation12);
		Evaluation evaluation13 = new Evaluation(criterion3, 20);
		evaluationList1.add(evaluation13);
		Evaluation evaluation14 = new Evaluation(criterion4, 20);
		evaluationList1.add(evaluation14);
		Evaluation evaluation15 = new Evaluation(criterion5, 20);
		evaluationList1.add(evaluation15);

		// project2
		Evaluation evaluation21 = new Evaluation(criterion6, 40);
		evaluationList2.add(evaluation21);
		Evaluation evaluation22 = new Evaluation(criterion7, 30);
		evaluationList3.add(evaluation22);
		Evaluation evaluation23 = new Evaluation(criterion8, 30);
		evaluationList3.add(evaluation23);

		// project3
		Evaluation evaluation31 = new Evaluation(criterion9, 40);
		evaluationList3.add(evaluation31);
		Evaluation evaluation32 = new Evaluation(criterion10, 40);
		evaluationList3.add(evaluation32);

		// Projet
		Project project1 = new Project("Peinture", "Description rapide", course, evaluationList1, 1, 3, 2);
		Project project2 = new Project("Bourse", "Description rapide", course, evaluationList2, 1, 4, 1);
		Project project3 = new Project("Jeu de Go", "Description rapide", course1, evaluationList3, 1, 5, 4);
		session.persist(project1);
		session.persist(project2);
		session.persist(project3);

		// Domain
		Domain domain = new Domain("Info");
		session.persist(domain);
		Domain domain1 = new Domain("Art");
		session.persist(domain1);
		Domain domain2 = new Domain("Economie");
		session.persist(domain2);
		Domain domain3 = new Domain("Jeu de société");
		session.persist(domain3);

		domainList1 = new ArrayList<Domain>();
		domainList1.add(domain);
		domainList1.add(domain1);

		domainList2 = new ArrayList<Domain>();
		domainList2.add(domain);
		domainList2.add(domain2);

		domainList3 = new ArrayList<Domain>();
		domainList3.add(domain);
		domainList3.add(domain3);

		// Language
		Language language = new Language("Java");
		languageList1.add(language);
		session.persist(language);

		Language language1 = new Language("C");
		languageList2.add(language1);
		session.persist(language1);

		Language language2 = new Language("HTML");
		languageList3.add(language2);
		session.persist(language2);

		Language language3 = new Language("PHP");
		languageList3.add(language3);
		session.persist(language3);

		// Tool
		Tool tool = new Tool("IDE");
		session.persist(tool);
		ToolContent toolContent = new ToolContent(tool, "Eclipse");
		session.persist(toolContent);
		toolContentList1.add(toolContent);
		toolContentList2.add(toolContent);
		toolContentList3.add(toolContent);

		Tool tool1 = new Tool("Logiciel de gestion de versions");
		session.persist(tool1);
		ToolContent toolContent1 = new ToolContent(tool1, "Git");
		session.persist(toolContent1);
		toolContentList1.add(toolContent1);
		toolContentList2.add(toolContent1);
		toolContentList3.add(toolContent1);

		Tool tool2 = new Tool("Logiciel de traitement de texte");
		session.persist(tool2);
		ToolContent toolContent2 = new ToolContent(tool2, "Latex");
		session.persist(toolContent2);
		toolContentList1.add(toolContent2);
		toolContentList2.add(toolContent2);
		toolContentList3.add(toolContent2);

		// Project Info
		ProjectInfo projectInfo1 = new ProjectInfo(project1, "Tianxiao Liu", true, domainList1, languageList1,
				toolContentList1, "Detailed Description");
		session.persist(projectInfo1);

		ProjectInfo projectInfo2 = new ProjectInfo(project2, "Tianxiao Liu", true, domainList2, languageList2,
				toolContentList2, "Detailed Description");
		session.persist(projectInfo2);

		ProjectInfo projectInfo3 = new ProjectInfo(project3, "Tianxiao Liu", true, domainList3, languageList3,
				toolContentList3, "Detailed Description");
		session.persist(projectInfo3);

		// Student
		List<Student> studentList1 = new ArrayList<Student>(); // team1
		studentList1.add(student1);
		studentList1.add(student2);
		studentList1.add(student3);

		List<Student> studentList2 = new ArrayList<Student>(); // team2
		studentList2.add(student4);
		studentList2.add(student5);
		studentList2.add(student6);

		List<Student> studentList3 = new ArrayList<Student>(); // team3
		studentList3.add(student7);
		studentList3.add(student8);
		studentList3.add(student9);
		studentList3.add(student10);

		List<Student> studentList4 = new ArrayList<Student>(); // team4
		studentList4.add(student11);
		studentList4.add(student12);
		studentList4.add(student13);
		studentList4.add(student14);
		studentList4.add(student15);

		List<Student> studentList5 = new ArrayList<Student>(); // team5
		studentList5.add(student16);
		studentList5.add(student17);
		studentList5.add(student18);
		studentList5.add(student19);
		studentList5.add(student20);

		List<Student> studentList6 = new ArrayList<Student>(); // team6
		studentList6.add(student21);
		studentList6.add(student22);
		studentList6.add(student23);
		studentList6.add(student24);
		studentList6.add(student25);

		List<Student> studentList7 = new ArrayList<Student>(); // team7
		studentList7.add(student26);
		studentList7.add(student27);
		studentList7.add(student28);
		studentList7.add(student29);
		studentList7.add(student30);

		// Student Score
		List<StudentScore> studentScoreList1 = new ArrayList<StudentScore>(); // team1
		StudentScore studentScore1 = new StudentScore(student1, 18);
		session.persist(studentScore1);
		StudentScore studentScore2 = new StudentScore(student2, 18);
		session.persist(studentScore2);
		StudentScore studentScore3 = new StudentScore(student3, 18);
		session.persist(studentScore3);

		List<StudentScore> studentScoreList2 = new ArrayList<StudentScore>(); // team2
		StudentScore studentScore4 = new StudentScore(student4, 16);
		session.persist(studentScore4);
		StudentScore studentScore5 = new StudentScore(student5, 15);
		session.persist(studentScore5);
		StudentScore studentScore6 = new StudentScore(student6, 18);
		session.persist(studentScore6);

		List<StudentScore> studentScoreList3 = new ArrayList<StudentScore>(); // team3
		StudentScore studentScore7 = new StudentScore(student7, 16);
		session.persist(studentScore7);
		StudentScore studentScore8 = new StudentScore(student8, 15);
		session.persist(studentScore8);
		StudentScore studentScore9 = new StudentScore(student9, 18);
		session.persist(studentScore9);
		StudentScore studentScore10 = new StudentScore(student10, 18);
		session.persist(studentScore10);

		List<StudentScore> studentScoreList4 = new ArrayList<StudentScore>(); // team4
		StudentScore studentScore11 = new StudentScore(student11, 16);
		session.persist(studentScore11);
		StudentScore studentScore12 = new StudentScore(student12, 15);
		session.persist(studentScore12);
		StudentScore studentScore13 = new StudentScore(student13, 18);
		session.persist(studentScore13);
		StudentScore studentScore14 = new StudentScore(student14, 18);
		session.persist(studentScore14);
		StudentScore studentScore15 = new StudentScore(student15, 18);
		session.persist(studentScore15);

		List<StudentScore> studentScoreList5 = new ArrayList<StudentScore>(); // team5
		StudentScore studentScore16 = new StudentScore(student16, 16);
		session.persist(studentScore16);
		StudentScore studentScore17 = new StudentScore(student17, 15);
		session.persist(studentScore17);
		StudentScore studentScore18 = new StudentScore(student18, 18);
		session.persist(studentScore18);
		StudentScore studentScore19 = new StudentScore(student19, 18);
		session.persist(studentScore19);
		StudentScore studentScore20 = new StudentScore(student20, 18);
		session.persist(studentScore20);

		List<StudentScore> studentScoreList6 = new ArrayList<StudentScore>(); // team6
		StudentScore studentScore21 = new StudentScore(student21, 16);
		session.persist(studentScore21);
		StudentScore studentScore22 = new StudentScore(student22, 15);
		session.persist(studentScore22);
		StudentScore studentScore23 = new StudentScore(student23, 18);
		session.persist(studentScore23);
		StudentScore studentScore24 = new StudentScore(student24, 18);
		session.persist(studentScore24);
		StudentScore studentScore25 = new StudentScore(student25, 18);
		session.persist(studentScore25);

		List<StudentScore> studentScoreList7 = new ArrayList<StudentScore>(); // team7
		StudentScore studentScore26 = new StudentScore(student26, 16);
		session.persist(studentScore26);
		StudentScore studentScore27 = new StudentScore(student27, 15);
		session.persist(studentScore27);
		StudentScore studentScore28 = new StudentScore(student28, 18);
		session.persist(studentScore28);
		StudentScore studentScore29 = new StudentScore(student29, 18);
		session.persist(studentScore29);
		StudentScore studentScore30 = new StudentScore(student30, 18);
		session.persist(studentScore30);

		// Evaluation Score
		// project1
		List<EvaluationScore> evaluationScoreList1 = new ArrayList<EvaluationScore>(); // team1
		EvaluationScore evaluationScore11 = new EvaluationScore(evaluation11, 15);
		evaluationScoreList1.add(evaluationScore11);
		EvaluationScore evaluationScore12 = new EvaluationScore(evaluation12, 14);
		evaluationScoreList1.add(evaluationScore12);
		EvaluationScore evaluationScore13 = new EvaluationScore(evaluation13, 17);
		evaluationScoreList1.add(evaluationScore13);
		EvaluationScore evaluationScore14 = new EvaluationScore(evaluation14, 16);
		evaluationScoreList1.add(evaluationScore14);
		EvaluationScore evaluationScore15 = new EvaluationScore(evaluation15, 18);
		evaluationScoreList1.add(evaluationScore15);

		List<EvaluationScore> evaluationScoreList2 = new ArrayList<EvaluationScore>(); // team2
		EvaluationScore evaluationScore21 = new EvaluationScore(evaluation11, 10);
		evaluationScoreList2.add(evaluationScore21);
		EvaluationScore evaluationScore22 = new EvaluationScore(evaluation12, 12);
		evaluationScoreList2.add(evaluationScore22);
		EvaluationScore evaluationScore23 = new EvaluationScore(evaluation13, 18);
		evaluationScoreList2.add(evaluationScore23);
		EvaluationScore evaluationScore24 = new EvaluationScore(evaluation14, 12);
		evaluationScoreList2.add(evaluationScore24);
		EvaluationScore evaluationScore25 = new EvaluationScore(evaluation15, 20);
		evaluationScoreList2.add(evaluationScore25);

		// project2
		List<EvaluationScore> evaluationScoreList3 = new ArrayList<EvaluationScore>(); // team3
		EvaluationScore evaluationScore31 = new EvaluationScore(evaluation21, 13);
		evaluationScoreList3.add(evaluationScore31);
		EvaluationScore evaluationScore32 = new EvaluationScore(evaluation22, 18);
		evaluationScoreList3.add(evaluationScore32);
		EvaluationScore evaluationScore33 = new EvaluationScore(evaluation23, 12);
		evaluationScoreList3.add(evaluationScore33);

		// project3
		List<EvaluationScore> evaluationScoreList4 = new ArrayList<EvaluationScore>(); // team4
		EvaluationScore evaluationScore41 = new EvaluationScore(evaluation31, 15);
		evaluationScoreList4.add(evaluationScore41);
		EvaluationScore evaluationScore42 = new EvaluationScore(evaluation32, 13);
		evaluationScoreList4.add(evaluationScore42);

		List<EvaluationScore> evaluationScoreList5 = new ArrayList<EvaluationScore>(); // team5
		EvaluationScore evaluationScore51 = new EvaluationScore(evaluation31, 14);
		evaluationScoreList5.add(evaluationScore51);
		EvaluationScore evaluationScore52 = new EvaluationScore(evaluation32, 18);
		evaluationScoreList5.add(evaluationScore52);

		List<EvaluationScore> evaluationScoreList6 = new ArrayList<EvaluationScore>(); // team6
		EvaluationScore evaluationScore61 = new EvaluationScore(evaluation31, 15);
		evaluationScoreList6.add(evaluationScore61);
		EvaluationScore evaluationScore62 = new EvaluationScore(evaluation32, 16);
		evaluationScoreList6.add(evaluationScore62);

		List<EvaluationScore> evaluationScoreList7 = new ArrayList<EvaluationScore>(); // team7
		EvaluationScore evaluationScore71 = new EvaluationScore(evaluation31, 15);
		evaluationScoreList7.add(evaluationScore71);
		EvaluationScore evaluationScore72 = new EvaluationScore(evaluation32, 20);
		evaluationScoreList7.add(evaluationScore72);

		// Teams
		Team team1 = new Team(project1, studentList1, evaluationScoreList1, 17, studentScoreList1);
		session.persist(team1);
		Team team2 = new Team(project1, studentList2, evaluationScoreList2, 15, studentScoreList2);
		session.persist(team2);

		Team team3 = new Team(project2, studentList3, evaluationScoreList3, 12, studentScoreList3);
		session.persist(team3);

		Team team4 = new Team(project3, studentList4, evaluationScoreList4, 10, studentScoreList4);
		session.persist(team4);
		Team team5 = new Team(project3, studentList5, evaluationScoreList5, 16, studentScoreList5);
		session.persist(team5);
		Team team6 = new Team(project3, studentList6, evaluationScoreList6, 19, studentScoreList6);
		session.persist(team6);
		Team team7 = new Team(project3, studentList7, evaluationScoreList7, 14, studentScoreList7);
		session.persist(team7);

		persistTransaction1.commit();
		session.close();

	}

	public static void PersistUniversityYear(int startYear, int endYear, Session session, List<UniversityYear> years) {

		for (int i = startYear; i < endYear; i++) {
			UniversityYear newYear = new UniversityYear(i, i + 1);
			years.add(newYear);
		}

		for (UniversityYear universityYear : years) {
			session.persist(universityYear);
		}
	}

	public static void main(String[] args) {

		InitTestData();

		/*
		 * 
		 * createTables();
		 * 
		 * Session session = DBConnection.getSession(); Transaction persistTransaction1
		 * = session.beginTransaction();
		 * 
		 * int START_YEAR = 2000; int END_YEAR = 2100; List<UniversityYear> years = new
		 * ArrayList<UniversityYear>(); for (int i = START_YEAR; i < END_YEAR; i++) {
		 * UniversityYear newYear = new UniversityYear(i, i + 1); years.add(newYear); }
		 * 
		 * for (UniversityYear universityYear : years) {
		 * session.persist(universityYear); }
		 * 
		 * Promotion promotion1 = new Promotion(years.get(0), "Licence", 1); Promotion
		 * promotion2 = new Promotion(years.get(0), "Licence", 2); Promotion promotion3
		 * = new Promotion(years.get(0), "Licence", 3); Promotion promotion4 = new
		 * Promotion(years.get(0), "Master", 1); Promotion promotion5 = new
		 * Promotion(years.get(0), "Master", 2); session.persist(promotion1);
		 * session.persist(promotion2); session.persist(promotion3);
		 * session.persist(promotion4); session.persist(promotion5);
		 * 
		 * int FIRST_LICENCE = 1; int LAST_LICENCE = 3; int FIRST_MASTER = 1; int
		 * LAST_MASTER = 2; List<Promotion> promos = new ArrayList<Promotion>(); for
		 * (int j = 1; j< END_YEAR-START_YEAR; j++) { for (int i = FIRST_LICENCE; i <=
		 * LAST_LICENCE; i++) { Promotion newPromo = new Promotion(years.get(j),
		 * "Licence", i); promos.add(newPromo); } for (int i = FIRST_MASTER; i <=
		 * LAST_MASTER; i++) { Promotion newPromo = new Promotion(years.get(j),
		 * "Master", i); promos.add(newPromo); } } for (Promotion promotion : promos) {
		 * session.persist(promotion); }
		 * 
		 * Course course = new Course(promos.get(0), "GLP"); session.persist(course);
		 * 
		 * Criterion criterion = new Criterion("QualitÃ©", "Bien ou pas bien");
		 * session.persist(criterion);
		 * 
		 * Evaluation evaluation = new Evaluation(criterion,40); List<Evaluation>
		 * evaluationList = new ArrayList<Evaluation>(); evaluationList.add(evaluation);
		 * 
		 * Project project = new Project("Jeu de Go", "Description rapide", course,
		 * evaluationList, 2, 3, 15); session.persist(project);
		 * 
		 * Domain domain = new Domain("Info"); session.persist(domain); List<Domain>
		 * domainList = new ArrayList<Domain>(); domainList.add(domain);
		 * 
		 * Language language = new Language("Java"); session.persist(language);
		 * List<Language> languageList = new ArrayList<Language>();
		 * languageList.add(language);
		 * 
		 * Tool tool = new Tool("IDE"); session.persist(tool);
		 * 
		 * ToolContent toolContent = new ToolContent(tool, "Eclipse");
		 * session.persist(toolContent); List<ToolContent> toolContentList = new
		 * ArrayList<ToolContent>();
		 * 
		 * ProjectInfo projectInfo = new ProjectInfo(project, "Tianxiao Liu", true,
		 * domainList, languageList, toolContentList, "Detailed Description");
		 * session.persist(projectInfo);
		 * 
		 * Student student = new Student("Antoine", "Leguay", "9862498ZJ87Z",
		 * "21616900", promos.get(0)); session.persist(student); List<Student>
		 * studentList = new ArrayList<Student>(); studentList.add(student);
		 * 
		 * StudentScore studentScore = new StudentScore(student, 17);
		 * session.persist(studentScore); List<StudentScore> studentScoreList = new
		 * ArrayList<StudentScore>();
		 * 
		 * EvaluationScore evaluationScore = new EvaluationScore(evaluation, 12);
		 * List<EvaluationScore> evaluationScoreList = new ArrayList<EvaluationScore>();
		 * 
		 * Team team = new Team(project, studentList, evaluationScoreList, 17,
		 * studentScoreList); session.persist(team);
		 * 
		 * persistTransaction1.commit();
		 * 
		 * session.close();
		 */
	}
}
