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

	public static void InitTestData(Session session) {

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
		Domain domain3 = new Domain("Jeu de societe");
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
		

		// Evaluation Score
			// project1
				// team1
					//student1
		List<EvaluationScore> evaluationScoreList11 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore111 = new EvaluationScore(evaluation11, 15,"Très Bien.");
		evaluationScoreList11.add(evaluationScore111);
		session.persist(evaluationScore111);
		EvaluationScore evaluationScore112 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList11.add(evaluationScore112);
		session.persist(evaluationScore112);
		EvaluationScore evaluationScore113 = new EvaluationScore(evaluation13, 17, "Très Bien.");
		evaluationScoreList11.add(evaluationScore113);
		session.persist(evaluationScore113);
		EvaluationScore evaluationScore114 = new EvaluationScore(evaluation14, 16, "Très Bien.");
		evaluationScoreList11.add(evaluationScore114);
		session.persist(evaluationScore114);
		EvaluationScore evaluationScore115 = new EvaluationScore(evaluation15, 18, "Très Bien.");
		evaluationScoreList11.add(evaluationScore115);
		session.persist(evaluationScore115);
					//student2
		List<EvaluationScore> evaluationScoreList12 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore121 = new EvaluationScore(evaluation11, 15,"Très Bien.");
		evaluationScoreList12.add(evaluationScore121);
		session.persist(evaluationScore121);
		EvaluationScore evaluationScore122 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList12.add(evaluationScore122);
		session.persist(evaluationScore122);
		EvaluationScore evaluationScore123 = new EvaluationScore(evaluation13, 17, "Très Bien.");
		evaluationScoreList12.add(evaluationScore123);
		session.persist(evaluationScore123);
		EvaluationScore evaluationScore124 = new EvaluationScore(evaluation14, 16, "Très Bien.");
		evaluationScoreList12.add(evaluationScore124);
		session.persist(evaluationScore124);
		EvaluationScore evaluationScore125 = new EvaluationScore(evaluation15, 18, "Très Bien.");
		evaluationScoreList12.add(evaluationScore125);
		session.persist(evaluationScore125);
					//student3
		List<EvaluationScore> evaluationScoreList13 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore131 = new EvaluationScore(evaluation11, 15,"Très Bien.");
		evaluationScoreList13.add(evaluationScore131);
		session.persist(evaluationScore131);
		EvaluationScore evaluationScore132 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList13.add(evaluationScore132);
		session.persist(evaluationScore132);
		EvaluationScore evaluationScore133 = new EvaluationScore(evaluation13, 17, "Très Bien.");
		evaluationScoreList13.add(evaluationScore133);
		session.persist(evaluationScore133);
		EvaluationScore evaluationScore134 = new EvaluationScore(evaluation14, 16, "Très Bien.");
		evaluationScoreList13.add(evaluationScore134);
		session.persist(evaluationScore134);
		EvaluationScore evaluationScore135 = new EvaluationScore(evaluation15, 18, "Très Bien.");
		evaluationScoreList13.add(evaluationScore135);
		session.persist(evaluationScore135);
		

			// team2
				//student1
		List<EvaluationScore> evaluationScoreList21 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore211 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList21.add(evaluationScore211);
		session.persist(evaluationScore211);
		EvaluationScore evaluationScore212 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList21.add(evaluationScore212);
		session.persist(evaluationScore212);
		EvaluationScore evaluationScore213 = new EvaluationScore(evaluation13, 18, "Très Bien.");
		evaluationScoreList21.add(evaluationScore213);
		session.persist(evaluationScore213);
		EvaluationScore evaluationScore214 = new EvaluationScore(evaluation14, 12, "Peut mieux faire.");
		evaluationScoreList21.add(evaluationScore214);
		session.persist(evaluationScore214);
		EvaluationScore evaluationScore215 = new EvaluationScore(evaluation15, 20, "Excellent.");
		evaluationScoreList21.add(evaluationScore215);
		session.persist(evaluationScore215);
					//student2
		List<EvaluationScore> evaluationScoreList22 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore221 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList22.add(evaluationScore221);
		session.persist(evaluationScore221);
		EvaluationScore evaluationScore222 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList22.add(evaluationScore222);
		session.persist(evaluationScore222);
		EvaluationScore evaluationScore223 = new EvaluationScore(evaluation13, 18, "Très Bien.");
		evaluationScoreList22.add(evaluationScore223);
		session.persist(evaluationScore223);
		EvaluationScore evaluationScore224 = new EvaluationScore(evaluation14, 12, "Peut mieux faire.");
		evaluationScoreList22.add(evaluationScore224);
		session.persist(evaluationScore224);
		EvaluationScore evaluationScore225 = new EvaluationScore(evaluation15, 20, "Excellent.");
		evaluationScoreList22.add(evaluationScore225);
		session.persist(evaluationScore225);
					//student3
		List<EvaluationScore> evaluationScoreList23 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore231 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList23.add(evaluationScore231);
		session.persist(evaluationScore231);
		EvaluationScore evaluationScore232 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList23.add(evaluationScore232);
		session.persist(evaluationScore232);
		EvaluationScore evaluationScore233 = new EvaluationScore(evaluation13, 18, "Très Bien.");
		evaluationScoreList23.add(evaluationScore233);
		session.persist(evaluationScore233);
		EvaluationScore evaluationScore234 = new EvaluationScore(evaluation14, 12, "Peut mieux faire.");
		evaluationScoreList23.add(evaluationScore234);
		session.persist(evaluationScore234);
		EvaluationScore evaluationScore235 = new EvaluationScore(evaluation15, 20, "Excellent.");
		evaluationScoreList23.add(evaluationScore235);
		session.persist(evaluationScore235);
		
		
		
		
			// project2
		 		// team3
					//student1
		List<EvaluationScore> evaluationScoreList31 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore311 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList31.add(evaluationScore311);
		session.persist(evaluationScore311);
		EvaluationScore evaluationScore312 = new EvaluationScore(evaluation22, 18, "Très Bien.");
		evaluationScoreList31.add(evaluationScore312);
		session.persist(evaluationScore312);
		EvaluationScore evaluationScore313 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList31.add(evaluationScore313);
		session.persist(evaluationScore313);
					//student2
		List<EvaluationScore> evaluationScoreList32 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore321 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList32.add(evaluationScore321);
		session.persist(evaluationScore321);
		EvaluationScore evaluationScore322 = new EvaluationScore(evaluation22, 18, "Très Bien.");
		evaluationScoreList32.add(evaluationScore322);
		session.persist(evaluationScore322);
		EvaluationScore evaluationScore323 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList32.add(evaluationScore323);
		session.persist(evaluationScore323);
					//student3
		List<EvaluationScore> evaluationScoreList33 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore331 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList33.add(evaluationScore331);
		session.persist(evaluationScore331);
		EvaluationScore evaluationScore332 = new EvaluationScore(evaluation22, 18, "Très Bien.");
		evaluationScoreList33.add(evaluationScore332);
		session.persist(evaluationScore332);
		EvaluationScore evaluationScore333 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList33.add(evaluationScore333);
		session.persist(evaluationScore333);
					//student4
		List<EvaluationScore> evaluationScoreList34 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore341 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList34.add(evaluationScore341);
		session.persist(evaluationScore341);
		EvaluationScore evaluationScore342 = new EvaluationScore(evaluation22, 18, "Très Bien.");
		evaluationScoreList34.add(evaluationScore342);
		session.persist(evaluationScore342);
		EvaluationScore evaluationScore343 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList34.add(evaluationScore343);
		session.persist(evaluationScore343);

			// project3
				// team4
					//student1
		List<EvaluationScore> evaluationScoreList41 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore411 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList41.add(evaluationScore411);
		session.persist(evaluationScore411);
		EvaluationScore evaluationScore412 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList41.add(evaluationScore412);
		session.persist(evaluationScore412);
					//student2
		List<EvaluationScore> evaluationScoreList42 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore421 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList42.add(evaluationScore421);
		session.persist(evaluationScore421);
		EvaluationScore evaluationScore422 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList42.add(evaluationScore422);
		session.persist(evaluationScore422);
					//student3
		List<EvaluationScore> evaluationScoreList43 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore431 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList43.add(evaluationScore431);
		session.persist(evaluationScore431);
		EvaluationScore evaluationScore432 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList43.add(evaluationScore432);
		session.persist(evaluationScore432);
					//student4
		List<EvaluationScore> evaluationScoreList44 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore441 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList44.add(evaluationScore441);
		session.persist(evaluationScore441);
		EvaluationScore evaluationScore442 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList44.add(evaluationScore442);
		session.persist(evaluationScore442);
					//student5
		List<EvaluationScore> evaluationScoreList45 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore451 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList45.add(evaluationScore451);
		session.persist(evaluationScore451);
		EvaluationScore evaluationScore452 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList45.add(evaluationScore452);
		session.persist(evaluationScore452);
		
				// team5
					//student1
		List<EvaluationScore> evaluationScoreList51 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore511 = new EvaluationScore(evaluation31, 14, "Très Bien.");
		evaluationScoreList51.add(evaluationScore511);
		session.persist(evaluationScore511);
		EvaluationScore evaluationScore512 = new EvaluationScore(evaluation32, 18, "Très Bien.");
		evaluationScoreList51.add(evaluationScore512);
		session.persist(evaluationScore512);
					//student2
		List<EvaluationScore> evaluationScoreList52 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore521 = new EvaluationScore(evaluation31, 14, "Très Bien.");
		evaluationScoreList52.add(evaluationScore521);
		session.persist(evaluationScore521);
		EvaluationScore evaluationScore522 = new EvaluationScore(evaluation32, 18, "Très Bien.");
		evaluationScoreList52.add(evaluationScore522);
		session.persist(evaluationScore522);
					//student3
		List<EvaluationScore> evaluationScoreList53 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore531 = new EvaluationScore(evaluation31, 14, "Très Bien.");
		evaluationScoreList53.add(evaluationScore531);
		session.persist(evaluationScore531);
		EvaluationScore evaluationScore532 = new EvaluationScore(evaluation32, 18, "Très Bien.");
		evaluationScoreList53.add(evaluationScore532);
		session.persist(evaluationScore532);
					//student4
		List<EvaluationScore> evaluationScoreList54 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore541 = new EvaluationScore(evaluation31, 14, "Très Bien.");
		evaluationScoreList54.add(evaluationScore541);
		session.persist(evaluationScore541);
		EvaluationScore evaluationScore542 = new EvaluationScore(evaluation32, 18, "Très Bien.");
		evaluationScoreList54.add(evaluationScore542);
		session.persist(evaluationScore542);
					//student5
		List<EvaluationScore> evaluationScoreList55 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore551 = new EvaluationScore(evaluation31, 14, "Très Bien.");
		evaluationScoreList55.add(evaluationScore551);
		session.persist(evaluationScore551);
		EvaluationScore evaluationScore552 = new EvaluationScore(evaluation32, 18, "Très Bien.");
		evaluationScoreList55.add(evaluationScore552);
		session.persist(evaluationScore552);

				//team6
					//student1
		List<EvaluationScore> evaluationScoreList61 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore611 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList61.add(evaluationScore611);
		session.persist(evaluationScore611);
		EvaluationScore evaluationScore612 = new EvaluationScore(evaluation32, 16, "Très Bien.");
		evaluationScoreList61.add(evaluationScore612);
		session.persist(evaluationScore612);
					//student2
		List<EvaluationScore> evaluationScoreList62 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore621 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList62.add(evaluationScore621);
		session.persist(evaluationScore621);
		EvaluationScore evaluationScore622 = new EvaluationScore(evaluation32, 16, "Très Bien.");
		evaluationScoreList62.add(evaluationScore622);
		session.persist(evaluationScore622);
					//stuednt3
		List<EvaluationScore> evaluationScoreList63 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore631 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList63.add(evaluationScore631);
		session.persist(evaluationScore631);
		EvaluationScore evaluationScore632 = new EvaluationScore(evaluation32, 16, "Très Bien.");
		evaluationScoreList63.add(evaluationScore632);
		session.persist(evaluationScore632);
					//student4
		List<EvaluationScore> evaluationScoreList64 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore641 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList64.add(evaluationScore641);
		session.persist(evaluationScore641);
		EvaluationScore evaluationScore642 = new EvaluationScore(evaluation32, 16, "Très Bien.");
		evaluationScoreList64.add(evaluationScore642);
		session.persist(evaluationScore642);
					//student5
		List<EvaluationScore> evaluationScoreList65 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore651 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList65.add(evaluationScore651);
		session.persist(evaluationScore651);
		EvaluationScore evaluationScore652 = new EvaluationScore(evaluation32, 16, "Très Bien.");
		evaluationScoreList65.add(evaluationScore652);
		session.persist(evaluationScore652);
		
		
				//team7
					//student1
		List<EvaluationScore> evaluationScoreList71 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore711 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList71.add(evaluationScore711);
		session.persist(evaluationScore711);
		EvaluationScore evaluationScore712 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList71.add(evaluationScore712);
		session.persist(evaluationScore712);
					//student2
		List<EvaluationScore> evaluationScoreList72 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore721 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList72.add(evaluationScore721);
		session.persist(evaluationScore721);
		EvaluationScore evaluationScore722 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList72.add(evaluationScore722);
		session.persist(evaluationScore722);
					//student3
		List<EvaluationScore> evaluationScoreList73 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore731 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList73.add(evaluationScore731);
		session.persist(evaluationScore731);
		EvaluationScore evaluationScore732 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList73.add(evaluationScore732);
		session.persist(evaluationScore732);
					//student4
		List<EvaluationScore> evaluationScoreList74 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore741 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList74.add(evaluationScore741);
		session.persist(evaluationScore741);
		EvaluationScore evaluationScore742 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList74.add(evaluationScore742);
		session.persist(evaluationScore742);
					//student5
		List<EvaluationScore> evaluationScoreList75 = new ArrayList<EvaluationScore>(); 
		EvaluationScore evaluationScore751 = new EvaluationScore(evaluation31, 15, "Très Bien.");
		evaluationScoreList75.add(evaluationScore751);
		session.persist(evaluationScore751);
		EvaluationScore evaluationScore752 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList75.add(evaluationScore752);
		session.persist(evaluationScore752);
		
		// Student Score
		List<StudentScore> studentScoreList1 = new ArrayList<StudentScore>(); // team1
		StudentScore studentScore1 = new StudentScore(student1, 18 ,evaluationScoreList11);
		studentScoreList1.add(studentScore1);
		session.persist(studentScore1);
		StudentScore studentScore2 = new StudentScore(student2, 18 ,evaluationScoreList12);
		studentScoreList1.add(studentScore2);
		session.persist(studentScore2);
		StudentScore studentScore3 = new StudentScore(student3, 18 ,evaluationScoreList13);
		studentScoreList1.add(studentScore3);
		session.persist(studentScore3);

		List<StudentScore> studentScoreList2 = new ArrayList<StudentScore>(); // team2
		StudentScore studentScore4 = new StudentScore(student4, 16 ,evaluationScoreList21);
		studentScoreList2.add(studentScore4);
		session.persist(studentScore4);
		StudentScore studentScore5 = new StudentScore(student5, 15 ,evaluationScoreList22);
		studentScoreList2.add(studentScore5);
		session.persist(studentScore5);
		StudentScore studentScore6 = new StudentScore(student6, 18 ,evaluationScoreList23);
		studentScoreList2.add(studentScore6);
		session.persist(studentScore6);

		List<StudentScore> studentScoreList3 = new ArrayList<StudentScore>(); // team3
		StudentScore studentScore7 = new StudentScore(student7, 16 ,evaluationScoreList31);
		studentScoreList3.add(studentScore7);
		session.persist(studentScore7);
		StudentScore studentScore8 = new StudentScore(student8, 15 ,evaluationScoreList32);
		studentScoreList3.add(studentScore8);
		session.persist(studentScore8);
		StudentScore studentScore9 = new StudentScore(student9, 18 ,evaluationScoreList33);
		studentScoreList3.add(studentScore9);
		session.persist(studentScore9);
		StudentScore studentScore10 = new StudentScore(student10, 18 ,evaluationScoreList34);
		studentScoreList3.add(studentScore10);
		session.persist(studentScore10);

		List<StudentScore> studentScoreList4 = new ArrayList<StudentScore>(); // team4
		StudentScore studentScore11 = new StudentScore(student11, 16 ,evaluationScoreList41);
		studentScoreList4.add(studentScore11);
		session.persist(studentScore11);
		StudentScore studentScore12 = new StudentScore(student12, 15 ,evaluationScoreList42);
		studentScoreList4.add(studentScore12);
		session.persist(studentScore12);
		StudentScore studentScore13 = new StudentScore(student13, 18 ,evaluationScoreList43);
		studentScoreList4.add(studentScore13);
		session.persist(studentScore13);
		StudentScore studentScore14 = new StudentScore(student14, 18, evaluationScoreList44);
		studentScoreList4.add(studentScore14);
		session.persist(studentScore14);
		StudentScore studentScore15 = new StudentScore(student15, 18 ,evaluationScoreList45);
		studentScoreList4.add(studentScore15);
		session.persist(studentScore15);

		List<StudentScore> studentScoreList5 = new ArrayList<StudentScore>(); // team5
		StudentScore studentScore16 = new StudentScore(student16, 16 ,evaluationScoreList51);
		studentScoreList5.add(studentScore16);
		session.persist(studentScore16);
		StudentScore studentScore17 = new StudentScore(student17, 15 ,evaluationScoreList52);
		studentScoreList5.add(studentScore17);
		session.persist(studentScore17);
		StudentScore studentScore18 = new StudentScore(student18, 18 ,evaluationScoreList53);
		studentScoreList5.add(studentScore18);
		session.persist(studentScore18);
		StudentScore studentScore19 = new StudentScore(student19, 18 ,evaluationScoreList54);
		studentScoreList5.add(studentScore19);
		session.persist(studentScore19);
		StudentScore studentScore20 = new StudentScore(student20, 18 ,evaluationScoreList55);
		studentScoreList5.add(studentScore20);
		session.persist(studentScore20);

		List<StudentScore> studentScoreList6 = new ArrayList<StudentScore>(); // team6
		StudentScore studentScore21 = new StudentScore(student21, 16 ,evaluationScoreList61);
		studentScoreList6.add(studentScore21);
		session.persist(studentScore21);
		StudentScore studentScore22 = new StudentScore(student22, 15 ,evaluationScoreList62);
		studentScoreList6.add(studentScore22);
		session.persist(studentScore22);
		StudentScore studentScore23 = new StudentScore(student23, 18 ,evaluationScoreList63);
		studentScoreList6.add(studentScore23);
		session.persist(studentScore23);
		StudentScore studentScore24 = new StudentScore(student24, 18 ,evaluationScoreList64);
		studentScoreList6.add(studentScore24);
		session.persist(studentScore24);
		StudentScore studentScore25 = new StudentScore(student25, 18 ,evaluationScoreList65);
		studentScoreList6.add(studentScore25);
		session.persist(studentScore25);

		List<StudentScore> studentScoreList7 = new ArrayList<StudentScore>(); // team7
		StudentScore studentScore26 = new StudentScore(student26, 16 ,evaluationScoreList71);
		studentScoreList7.add(studentScore26);
		session.persist(studentScore26);
		StudentScore studentScore27 = new StudentScore(student27, 15 ,evaluationScoreList72);
		studentScoreList7.add(studentScore27);
		session.persist(studentScore27);
		StudentScore studentScore28 = new StudentScore(student28, 18 ,evaluationScoreList73);
		studentScoreList7.add(studentScore28);
		session.persist(studentScore28);
		StudentScore studentScore29 = new StudentScore(student29, 18 ,evaluationScoreList74);
		studentScoreList7.add(studentScore29);
		session.persist(studentScore29);
		StudentScore studentScore30 = new StudentScore(student30, 18 ,evaluationScoreList75);
		studentScoreList7.add(studentScore30);
		session.persist(studentScore30);

		
		// Teams  
		
		Team team1 = new Team(project1, studentList1, 17, studentScoreList1,"");		
		session.persist(team1);
		Team team2 = new Team(project1, studentList2, 15, studentScoreList2,"FunGroup");
		session.persist(team2);

		Team team3 = new Team(project2, studentList3, 12, studentScoreList3,"");
		session.persist(team3);

		Team team4 = new Team(project3, studentList4, 10, studentScoreList4,"BestGroup");
		session.persist(team4);
		Team team5 = new Team(project3, studentList5, 16, studentScoreList5, "");
		session.persist(team5);
		Team team6 = new Team(project3, studentList6, 19, studentScoreList6, "");
		session.persist(team6);
		Team team7 = new Team(project3, studentList7, 14, studentScoreList7, "");
		session.persist(team7);

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

		InitTestData(session);

		persistTransaction1.commit();
		session.close();

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
