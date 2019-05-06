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
		List<Domain> domainList = new ArrayList<Domain>();
		List<ToolContent> toolContentList = new ArrayList<ToolContent>();
		List<StudentScore> studentScoreList = new ArrayList<StudentScore>();
		List<EvaluationScore> evaluationScoreList = new ArrayList<EvaluationScore>();
		List<Language> languageList = new ArrayList<Language>();
		
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
			
		//student of 2nd promotion
		
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
		
		
		//Criterion
		for(int i=0; i<15; i++) {
			Criterion criterion = new Criterion("Critere"+i, "Bien ou pas ? gros.");
			session.persist(criterion);
			if(i<6) {
				Evaluation evaluation = new Evaluation(criterion,20);
				evaluationList1.add(evaluation);
			}
			if(i<3) {
				Evaluation evaluation = new Evaluation(criterion,50);
				evaluationList2.add(evaluation);
				
				Evaluation evaluation1 = new Evaluation(criterion,30);
				evaluationList3.add(evaluation1);
			}
			if(i==3) {
				Evaluation evaluation = new Evaluation(criterion,40);
				evaluationList3.add(evaluation);
			}
		}

		// Projet
		Project project1 = new Project("Peinture", "Description rapide", course, evaluationList1, 1, 3, 2);
		Project project2 = new Project("Ecriture", "Description rapide", course, evaluationList2, 1, 4, 1);
		Project project3 = new Project("Jeu de Go", "Description rapide", course1, evaluationList3, 1, 5, 4);
		session.persist(project1);
		session.persist(project2);
		session.persist(project3);
        
		Domain domain = new Domain("Info");
		session.persist(domain);
		domainList = new ArrayList<Domain>();
		domainList.add(domain);

		Language language = new Language("Java");
		session.persist(language);
		languageList.add(language);
		
		Tool tool = new Tool("IDE");
		session.persist(tool);
		
		ToolContent toolContent = new ToolContent(tool, "Eclipse");
		session.persist(toolContent);

		ProjectInfo projectInfo1 = new ProjectInfo(project1, "Tianxiao Liu", true, domainList, languageList, toolContentList, "Detailed Description");
		session.persist(projectInfo1);
		
		ProjectInfo projectInfo2 = new ProjectInfo(project2, "Tianxiao Liu", true, domainList, languageList, toolContentList, "Detailed Description");
		session.persist(projectInfo2);
		
		ProjectInfo projectInfo3 = new ProjectInfo(project3, "Tianxiao Liu", true, domainList, languageList, toolContentList, "Detailed Description");
		session.persist(projectInfo3);
		
		
		//Student
		
		List<Student> studentList1 = new ArrayList<Student>();
		List<Student> studentList2 = new ArrayList<Student>();
		List<Student> studentList3 = new ArrayList<Student>();
		
		for(Student s : studentList) {
			StudentScore studentScore = new StudentScore(s, 17);
			session.persist(studentScore);
		}
		
		
		//EvaluationScore evaluationScore = new EvaluationScore(evaluation, 15);
		
		Team team1 = new Team(project1, studentList1, evaluationScoreList, 17, studentScoreList);
		session.persist(team1);
		
		Team team2 = new Team(project2, studentList2, evaluationScoreList, 15, studentScoreList);
		session.persist(team2);
		
		Team team3 = new Team(project3, studentList3, evaluationScoreList, 18, studentScoreList);
		session.persist(team3);
		
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
