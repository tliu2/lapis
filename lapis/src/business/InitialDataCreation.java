package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

	public static void InitTestBigData(Session session) {

		int START_YEAR = 2000;
		int END_YEAR = 2010;
		int nbStudents = 500;

		List<UniversityYear> years = new ArrayList<UniversityYear>();
		List<Promotion> promos = new ArrayList<Promotion>();
		List<Student> studentList = new ArrayList<Student>();
		List<Course> courseList = new ArrayList<Course>();
		List<Criterion> criterionList = new ArrayList<Criterion>();
		List<Project> projectList = new ArrayList<Project>();
		List<Language> languageList = new ArrayList<Language>();
		List<Domain> domainList = new ArrayList<Domain>();
		List<ToolContent> toolContentList = new ArrayList<ToolContent>();
		List<Tool> toolList = new ArrayList<Tool>();
		List<ProjectInfo> projectInfoList = new ArrayList<ProjectInfo>();
		List<EvaluationScore> evaluationScoreList = new ArrayList<EvaluationScore>();
		
		List<ArrayList<Evaluation>> evaluationsList = new ArrayList<ArrayList<Evaluation>>();

		// University Year
		PersistUniversityYear(START_YEAR, END_YEAR, session, years);
		// Promotion
		PersistPromotion(session, promos, years);
		// Students
		PersistStudent(session, promos, studentList, nbStudents);
		// Course
		PersistCourse(session, promos, courseList);
		// Criterion
		PersistCriterion(session, criterionList);
		//Evaluation
		PersistEvaluation(session, evaluationsList, criterionList);
		// project
		PersistProject(session, projectList, courseList, evaluationsList);
		// Language
		PersistLanguage(session, languageList);
		// Domain
		PersistDomain(session, domainList);
		// Tool
		PersistTool(session, toolContentList, toolList);
		// projectinfo
		PersistProjectInfo(session, projectList, domainList, toolContentList, languageList, projectInfoList);
		//evaluationScore
		PersistEvaluationScore(session,evaluationScoreList, evaluationsList);
	}
	
	public static void PersistEvaluationScore(Session session,List<EvaluationScore> evaluationScoreList, List<ArrayList<Evaluation>> evaluationsList) {
		for(int index=0; index < evaluationsList.size()*evaluationsList.get(0).size(); index++) {
			for(int j = 0; j<10; j++) {
				Random rand = new Random(); int nombreAleatoire = rand.nextInt(21);
				EvaluationScore evaluationScore = new EvaluationScore(evaluationsList.get(index/5).get(index%5), nombreAleatoire, "Description");
				evaluationScoreList.add(evaluationScore);
			}
		}
		
		for(EvaluationScore evaluationScore : evaluationScoreList) {
			session.persist(evaluationScore);
		}
	}
	
	public static void PersistEvaluation(Session session, List<ArrayList<Evaluation>> evaluationsList, List<Criterion> criterionList) {
		
		for(int index=0; index <100; index++ ) {
			ArrayList<Evaluation> evaluationList = new ArrayList<Evaluation>();
			for(int j=0; j<5; j++) {
				Evaluation eval = new Evaluation(criterionList.get(j), 20);
				evaluationList.add(eval);
			}
			evaluationsList.add(evaluationList);
		}
		
		for(ArrayList<Evaluation> evaluations: evaluationsList) {
			for(Evaluation evaluation: evaluations) {
				session.persist(evaluation);
			}
		}
	}
	
	private static void PersistProjectInfo(Session session, List<Project> projectList, List<Domain> domainList,
			List<ToolContent> toolContentList, List<Language> languageList, List<ProjectInfo> projectInfoList) {

		for (int index = 0; index < projectList.size(); index++) {
			ProjectInfo projectInfo = new ProjectInfo(projectList.get(index), "Tianxiao Liu", true, domainList,
					languageList, toolContentList, "Detailed Description");
			projectInfoList.add(projectInfo);
		}

		for (ProjectInfo pinfo : projectInfoList)
			session.persist(pinfo);

	}

	private static void PersistStudent(Session session, List<Promotion> promos, List<Student> studentList,
			int nbStudents) {
		String[] firstNameArray = { "Sheena", "Michelle", "Aubrey", "Autumn", "Marlon", "Jani",
				"Bettina", "Maribel", "Jeneva", "Luna", "Ulysses", "Jerri", "Amos", "Stephane", "Annelle", "Karry",
				"Dahlia", "Isabelle", "Jimmie", "Haley", "Talia", "Vivian", "Maryjane", "Mabel", "Willy", "Earleen",
				"Kaylee", "Deanne", "Dayle", "Ellan", "Boris", "Magen", "Contessa", "Babara", "Rosio", "Arlie",
				"Lauryn", "Wanita", "Una", "Lanelle", "Tula", "Corrin", "Kasi", "Jarvis", "January", "Liane", "Bebe",
				"Tanisha", "Lavon", "Hedwig", "Marcelle", "Florentino", "Efrain" };

		String[] lastNameArray = { "Botkin", "Engles", "Ceniceros", "Borg", "Mariner", "Labrum", "Sidener", "Newberg",
				"Rosamond", "Uresti" };

		List<String> firstNamelist = Arrays.asList(firstNameArray);
		List<String> lastNamelist = Arrays.asList(lastNameArray);

		for (int index = 0; index < nbStudents; index++) {
			Student student = new Student(firstNamelist.get(index % firstNamelist.size()),
					lastNamelist.get(index / firstNamelist.size()), (1000000 + index) + "ZJ87Z",
					Integer.toString(217000000 + index), promos.get(index % promos.size()));
			studentList.add(student); 
		}

		for (Student student : studentList) {
			session.persist(student);
		}

	}

	public static void PersistPromotion(Session session, List<Promotion> promos, List<UniversityYear> years) {
		for (int index = 0; index < years.size(); index++) {
			for (int licenceIndex = 1; licenceIndex <= 3; licenceIndex++) {
				Promotion promotion = new Promotion(years.get(index), "Licence", licenceIndex);
				promos.add(promotion);
			}
			for (int masterIndex = 1; masterIndex <= 2; masterIndex++) {
				Promotion promotion = new Promotion(years.get(index), "Master", masterIndex);
				promos.add(promotion);
			}

		}

		for (Promotion promotion : promos) {
			session.persist(promotion);
		}
	}

	public static void PersistCourse(Session session, List<Promotion> promos, List<Course> courseList) {

		for (int index = 0; index < promos.size(); index++) {
			if (promos.get(index).getDiplomaName() == "Licence" && promos.get(index).getLevel() == 1) {
				Course course = new Course(promos.get(index), "Math");
				courseList.add(course);
				course = new Course(promos.get(index), "Physique");
				courseList.add(course);
				course = new Course(promos.get(index), "Chimie");
				courseList.add(course);

			} else if (promos.get(index).getDiplomaName() == "Licence" && promos.get(index).getLevel() == 2) {
				Course course = new Course(promos.get(index), "GLP");
				courseList.add(course);
			} else if (promos.get(index).getDiplomaName() == "Licence" && promos.get(index).getLevel() == 3) {
				Course course = new Course(promos.get(index), "GPI");
				courseList.add(course);
				course = new Course(promos.get(index), "Architecture");
				courseList.add(course);
				course = new Course(promos.get(index), "OS");
				courseList.add(course);
			} else if (promos.get(index).getDiplomaName() == "Master" && promos.get(index).getLevel() == 1) {
				Course course = new Course(promos.get(index), "COO");
				courseList.add(course);
			} else if (promos.get(index).getDiplomaName() == "Master" && promos.get(index).getLevel() == 2) {
				Course course = new Course(promos.get(index), "GP");
				courseList.add(course);
			} else {
				System.out.println("persist course bug\n");
			}
		}
		for (Course course : courseList) {
			session.persist(course);
		}

	}

	public static void PersistCriterion(Session session, List<Criterion> criterionList) {
		Criterion criterion1 = new Criterion("Fonctionnalities", "Well or not ?");
		criterionList.add(criterion1);
		Criterion criterion2 = new Criterion("Fluidity", "Well or not ?");
		criterionList.add(criterion2);
		Criterion criterion3 = new Criterion("Usage of the tools imposed", "Well or not ?");
		criterionList.add(criterion3);
		Criterion criterion4 = new Criterion("Comment", "Well or not ?");
		criterionList.add(criterion4);
		Criterion criterion5 = new Criterion("Facility of re-use", "Well or not ?");
		criterionList.add(criterion5);
		Criterion criterion6 = new Criterion("Structure of the code", "Well or not ?");
		criterionList.add(criterion6);
		Criterion criterion7 = new Criterion("Usage of the course notions", "Well or not ?");
		criterionList.add(criterion7);
		Criterion criterion8 = new Criterion("Generation of traces", "Well or not ?");
		criterionList.add(criterion8);
		Criterion criterion9 = new Criterion("Gestion of the exceptions", "Well or not ?");
		criterionList.add(criterion9);
		Criterion criterion10 = new Criterion("Ergonomic", "Well or not ?");
		criterionList.add(criterion10);
		Criterion criterion11 = new Criterion("Esthetical", "Well or not ?");
		criterionList.add(criterion11);
		Criterion criterion12 = new Criterion("Mini demonstration", "Well or not ?");
		criterionList.add(criterion12);
		Criterion criterion13 = new Criterion("Structure of the report", "Well or not ?");
		criterionList.add(criterion13);
		Criterion criterion14 = new Criterion("UML Diagram", "Well or not ?");
		criterionList.add(criterion14);
		Criterion criterion15 = new Criterion("Presence, ponctuality, assiduity & participation", "Well or not ?");
		criterionList.add(criterion15);

		for (Criterion criterion : criterionList)
			session.persist(criterion);
	}

	public static void PersistProject(Session session, List<Project> projectList, List<Course> courseList,
			List<ArrayList<Evaluation>> evaluationsList) {
		
		for (int index = 0; index < courseList.size(); index++) {
			Project project = new Project("Random Name", "Description rapide", courseList.get(index), evaluationsList.get(index), 1,
					3, 30);
			projectList.add(project);
		}
		
		for(int index = 0; index<10; index++) {
			Project project = new Project("Random Name", "Description rapide", courseList.get(index), evaluationsList.get(index), 1,
					3, 30);
			projectList.add(project);
		}

		for (Project project : projectList)
			session.persist(project);
	}

	public static void PersistLanguage(Session session, List<Language> languageList) {
		Language language = new Language("Java");
		languageList.add(language);

		Language language1 = new Language("C");
		languageList.add(language1);

		Language language2 = new Language("HTML");
		languageList.add(language2);

		Language language3 = new Language("PHP");
		languageList.add(language3);

		for (Language language4 : languageList)
			session.persist(language4);
	}

	public static void PersistDomain(Session session, List<Domain> domainList) {
		// Domain
		Domain domain = new Domain("Info");
		Domain domain1 = new Domain("Art");
		Domain domain2 = new Domain("Economie");
		Domain domain3 = new Domain("Jeu de societe");

		domainList.add(domain);
		domainList.add(domain1);
		domainList.add(domain2);
		domainList.add(domain3);

		for (Domain domains : domainList)
			session.persist(domains);
	}

	public static void PersistTool(Session session, List<ToolContent> toolContentList, List<Tool> toolList) {
		Tool tool = new Tool("IDE");
		ToolContent toolContent = new ToolContent(tool, "Eclipse");
		toolList.add(tool);
		toolContentList.add(toolContent);

		tool = new Tool("Logiciel de gestion de versions");
		toolContent = new ToolContent(tool, "Git");
		toolList.add(tool);
		toolContentList.add(toolContent);

		tool = new Tool("Logiciel de traitement de texte");
		toolContent = new ToolContent(tool, "Latex");
		toolList.add(tool);
		toolContentList.add(toolContent);

		for (Tool tools : toolList)
			session.persist(tools);

		for (ToolContent toolContents : toolContentList)
			session.persist(toolContents);
	}

	////////////////////////////////////////////////////////////////////////////

	public static void InitTestData(Session session) {

		int START_YEAR = 2000;
		int END_YEAR = 2003;

		List<UniversityYear> years = new ArrayList<UniversityYear>();
		List<Promotion> promos = new ArrayList<Promotion>();
		List<Student> studentList = new ArrayList<Student>();
		List<Evaluation> evaluationList1 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList2 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList3 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList4 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList5 = new ArrayList<Evaluation>();
		List<Evaluation> evaluationList6 = new ArrayList<Evaluation>();
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
		Course course3 = new Course(promos.get(0), "OS");
		Course course4 = new Course(promos.get(0), "Archi");
		Course course5 = new Course(promos.get(0), "Math");

		session.persist(course);
		session.persist(course1);
		session.persist(course2);
		session.persist(course3);
		session.persist(course4);
		session.persist(course5);

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

		Student student7 = new Student("Mathieu", "VOISIN", "7862498ZJ87Z", "217150367", promos.get(0));
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

		// project4
		Evaluation evaluation41 = new Evaluation(criterion6, 40);
		evaluationList4.add(evaluation41);
		Evaluation evaluation42 = new Evaluation(criterion2, 30);
		evaluationList4.add(evaluation42);
		Evaluation evaluation43 = new Evaluation(criterion3, 30);
		evaluationList4.add(evaluation43);

		// project5
		Evaluation evaluation51 = new Evaluation(criterion1, 40);
		evaluationList5.add(evaluation51);
		Evaluation evaluation52 = new Evaluation(criterion6, 30);
		evaluationList5.add(evaluation52);
		Evaluation evaluation53 = new Evaluation(criterion7, 30);
		evaluationList5.add(evaluation53);

		// project6
		Evaluation evaluation61 = new Evaluation(criterion3, 40);
		evaluationList6.add(evaluation61);
		Evaluation evaluation62 = new Evaluation(criterion5, 30);
		evaluationList6.add(evaluation62);
		Evaluation evaluation63 = new Evaluation(criterion1, 30);
		evaluationList6.add(evaluation63);

		// Projet
		Project project1 = new Project("Peinture", "Description rapide", course, evaluationList1, 1, 3, 2);
		Project project2 = new Project("Bourse", "Description rapide", course, evaluationList2, 1, 4, 1);
		Project project3 = new Project("Jeu de Go", "Description rapide", course1, evaluationList3, 1, 5, 4);
		Project project4 = new Project("Mini Shell", "Description rapide", course3, evaluationList4, 1, 5, 4);
		Project project5 = new Project("MicroProcesseur", "Description rapide", course4, evaluationList5, 1, 5, 4);
		Project project6 = new Project("R program", "Description rapide", course5, evaluationList6, 1, 5, 4);
		session.persist(project1);
		session.persist(project2);
		session.persist(project3);
		session.persist(project4);
		session.persist(project5);
		session.persist(project6);

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

		List<Student> studentList8 = new ArrayList<Student>(); // team8
		studentList8.add(student10);
		studentList8.add(student5);
		studentList8.add(student6);
		studentList8.add(student7);

		List<Student> studentList9 = new ArrayList<Student>(); // team9
		studentList9.add(student6);
		studentList9.add(student7);

		List<Student> studentList10 = new ArrayList<Student>(); // team10
		studentList10.add(student1);
		studentList10.add(student2);
		studentList10.add(student7);

		// Evaluation Score
		// project1
		// team1
		// student1
		List<EvaluationScore> evaluationScoreList11 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore111 = new EvaluationScore(evaluation11, 15, "Tres	 Bien.");
		evaluationScoreList11.add(evaluationScore111);
		session.persist(evaluationScore111);
		EvaluationScore evaluationScore112 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList11.add(evaluationScore112);
		session.persist(evaluationScore112);
		EvaluationScore evaluationScore113 = new EvaluationScore(evaluation13, 17, "Tres Bien.");
		evaluationScoreList11.add(evaluationScore113);
		session.persist(evaluationScore113);
		EvaluationScore evaluationScore114 = new EvaluationScore(evaluation14, 16, "Tres Bien.");
		evaluationScoreList11.add(evaluationScore114);
		session.persist(evaluationScore114);
		EvaluationScore evaluationScore115 = new EvaluationScore(evaluation15, 18, "Tres Bien.");
		evaluationScoreList11.add(evaluationScore115);
		session.persist(evaluationScore115);
		// student2
		List<EvaluationScore> evaluationScoreList12 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore121 = new EvaluationScore(evaluation11, 15, "Tres Bien.");
		evaluationScoreList12.add(evaluationScore121);
		session.persist(evaluationScore121);
		EvaluationScore evaluationScore122 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList12.add(evaluationScore122);
		session.persist(evaluationScore122);
		EvaluationScore evaluationScore123 = new EvaluationScore(evaluation13, 17, "Tres Bien.");
		evaluationScoreList12.add(evaluationScore123);
		session.persist(evaluationScore123);
		EvaluationScore evaluationScore124 = new EvaluationScore(evaluation14, 16, "Tres Bien.");
		evaluationScoreList12.add(evaluationScore124);
		session.persist(evaluationScore124);
		EvaluationScore evaluationScore125 = new EvaluationScore(evaluation15, 18, "Tres Bien.");
		evaluationScoreList12.add(evaluationScore125);
		session.persist(evaluationScore125);
		// student3
		List<EvaluationScore> evaluationScoreList13 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore131 = new EvaluationScore(evaluation11, 15, "Tres Bien.");
		evaluationScoreList13.add(evaluationScore131);
		session.persist(evaluationScore131);
		EvaluationScore evaluationScore132 = new EvaluationScore(evaluation12, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList13.add(evaluationScore132);
		session.persist(evaluationScore132);
		EvaluationScore evaluationScore133 = new EvaluationScore(evaluation13, 17, "Tres Bien.");
		evaluationScoreList13.add(evaluationScore133);
		session.persist(evaluationScore133);
		EvaluationScore evaluationScore134 = new EvaluationScore(evaluation14, 16, "Tres Bien.");
		evaluationScoreList13.add(evaluationScore134);
		session.persist(evaluationScore134);
		EvaluationScore evaluationScore135 = new EvaluationScore(evaluation15, 18, "Tres Bien.");
		evaluationScoreList13.add(evaluationScore135);
		session.persist(evaluationScore135);

		// team2
		// student1
		List<EvaluationScore> evaluationScoreList21 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore211 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList21.add(evaluationScore211);
		session.persist(evaluationScore211);
		EvaluationScore evaluationScore212 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList21.add(evaluationScore212);
		session.persist(evaluationScore212);
		EvaluationScore evaluationScore213 = new EvaluationScore(evaluation13, 18, "Tres Bien.");
		evaluationScoreList21.add(evaluationScore213);
		session.persist(evaluationScore213);
		EvaluationScore evaluationScore214 = new EvaluationScore(evaluation14, 12, "Peut mieux faire.");
		evaluationScoreList21.add(evaluationScore214);
		session.persist(evaluationScore214);
		EvaluationScore evaluationScore215 = new EvaluationScore(evaluation15, 20, "Excellent.");
		evaluationScoreList21.add(evaluationScore215);
		session.persist(evaluationScore215);
		// student2
		List<EvaluationScore> evaluationScoreList22 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore221 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList22.add(evaluationScore221);
		session.persist(evaluationScore221);
		EvaluationScore evaluationScore222 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList22.add(evaluationScore222);
		session.persist(evaluationScore222);
		EvaluationScore evaluationScore223 = new EvaluationScore(evaluation13, 18, "Tres Bien.");
		evaluationScoreList22.add(evaluationScore223);
		session.persist(evaluationScore223);
		EvaluationScore evaluationScore224 = new EvaluationScore(evaluation14, 12, "Peut mieux faire.");
		evaluationScoreList22.add(evaluationScore224);
		session.persist(evaluationScore224);
		EvaluationScore evaluationScore225 = new EvaluationScore(evaluation15, 20, "Excellent.");
		evaluationScoreList22.add(evaluationScore225);
		session.persist(evaluationScore225);
		// student3
		List<EvaluationScore> evaluationScoreList23 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore231 = new EvaluationScore(evaluation11, 10, "Moyen.");
		evaluationScoreList23.add(evaluationScore231);
		session.persist(evaluationScore231);
		EvaluationScore evaluationScore232 = new EvaluationScore(evaluation12, 12, "Passable.");
		evaluationScoreList23.add(evaluationScore232);
		session.persist(evaluationScore232);
		EvaluationScore evaluationScore233 = new EvaluationScore(evaluation13, 18, "Tres Bien.");
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
		// student1
		List<EvaluationScore> evaluationScoreList31 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore311 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList31.add(evaluationScore311);
		session.persist(evaluationScore311);
		EvaluationScore evaluationScore312 = new EvaluationScore(evaluation22, 18, "Tres Bien.");
		evaluationScoreList31.add(evaluationScore312);
		session.persist(evaluationScore312);
		EvaluationScore evaluationScore313 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList31.add(evaluationScore313);
		session.persist(evaluationScore313);
		// student2
		List<EvaluationScore> evaluationScoreList32 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore321 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList32.add(evaluationScore321);
		session.persist(evaluationScore321);
		EvaluationScore evaluationScore322 = new EvaluationScore(evaluation22, 18, "Tres Bien.");
		evaluationScoreList32.add(evaluationScore322);
		session.persist(evaluationScore322);
		EvaluationScore evaluationScore323 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList32.add(evaluationScore323);
		session.persist(evaluationScore323);
		// student3
		List<EvaluationScore> evaluationScoreList33 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore331 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList33.add(evaluationScore331);
		session.persist(evaluationScore331);
		EvaluationScore evaluationScore332 = new EvaluationScore(evaluation22, 18, "Tres Bien.");
		evaluationScoreList33.add(evaluationScore332);
		session.persist(evaluationScore332);
		EvaluationScore evaluationScore333 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList33.add(evaluationScore333);
		session.persist(evaluationScore333);
		// student4
		List<EvaluationScore> evaluationScoreList34 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore341 = new EvaluationScore(evaluation21, 13, "Bien.");
		evaluationScoreList34.add(evaluationScore341);
		session.persist(evaluationScore341);
		EvaluationScore evaluationScore342 = new EvaluationScore(evaluation22, 18, "Tres Bien.");
		evaluationScoreList34.add(evaluationScore342);
		session.persist(evaluationScore342);
		EvaluationScore evaluationScore343 = new EvaluationScore(evaluation23, 12, "Bof.");
		evaluationScoreList34.add(evaluationScore343);
		session.persist(evaluationScore343);

		// project3
		// team4
		// student1
		List<EvaluationScore> evaluationScoreList41 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore411 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList41.add(evaluationScore411);
		session.persist(evaluationScore411);
		EvaluationScore evaluationScore412 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList41.add(evaluationScore412);
		session.persist(evaluationScore412);
		// student2
		List<EvaluationScore> evaluationScoreList42 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore421 = new EvaluationScore(evaluation31, 15, "Bien.");
		evaluationScoreList42.add(evaluationScore421);
		session.persist(evaluationScore421);
		EvaluationScore evaluationScore422 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList42.add(evaluationScore422);
		session.persist(evaluationScore422);
		// student3
		List<EvaluationScore> evaluationScoreList43 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore431 = new EvaluationScore(evaluation31, 15, "Bien.");
		evaluationScoreList43.add(evaluationScore431);
		session.persist(evaluationScore431);
		EvaluationScore evaluationScore432 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList43.add(evaluationScore432);
		session.persist(evaluationScore432);
		// student4
		List<EvaluationScore> evaluationScoreList44 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore441 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList44.add(evaluationScore441);
		session.persist(evaluationScore441);
		EvaluationScore evaluationScore442 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList44.add(evaluationScore442);
		session.persist(evaluationScore442);
		// student5
		List<EvaluationScore> evaluationScoreList45 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore451 = new EvaluationScore(evaluation31, 15, "Bien.");
		evaluationScoreList45.add(evaluationScore451);
		session.persist(evaluationScore451);
		EvaluationScore evaluationScore452 = new EvaluationScore(evaluation32, 13, "Bien.");
		evaluationScoreList45.add(evaluationScore452);
		session.persist(evaluationScore452);

		// team5
		// student1
		List<EvaluationScore> evaluationScoreList51 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore511 = new EvaluationScore(evaluation31, 14, "Bien.");
		evaluationScoreList51.add(evaluationScore511);
		session.persist(evaluationScore511);
		EvaluationScore evaluationScore512 = new EvaluationScore(evaluation32, 18, "Tres Bien.");
		evaluationScoreList51.add(evaluationScore512);
		session.persist(evaluationScore512);
		// student2
		List<EvaluationScore> evaluationScoreList52 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore521 = new EvaluationScore(evaluation31, 14, "Bien.");
		evaluationScoreList52.add(evaluationScore521);
		session.persist(evaluationScore521);
		EvaluationScore evaluationScore522 = new EvaluationScore(evaluation32, 18, "Tres Bien.");
		evaluationScoreList52.add(evaluationScore522);
		session.persist(evaluationScore522);
		// student3
		List<EvaluationScore> evaluationScoreList53 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore531 = new EvaluationScore(evaluation31, 14, "Bien.");
		evaluationScoreList53.add(evaluationScore531);
		session.persist(evaluationScore531);
		EvaluationScore evaluationScore532 = new EvaluationScore(evaluation32, 18, "Tres Bien.");
		evaluationScoreList53.add(evaluationScore532);
		session.persist(evaluationScore532);
		// student4
		List<EvaluationScore> evaluationScoreList54 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore541 = new EvaluationScore(evaluation31, 14, "Bien.");
		evaluationScoreList54.add(evaluationScore541);
		session.persist(evaluationScore541);
		EvaluationScore evaluationScore542 = new EvaluationScore(evaluation32, 18, "Tres Bien.");
		evaluationScoreList54.add(evaluationScore542);
		session.persist(evaluationScore542);
		// student5
		List<EvaluationScore> evaluationScoreList55 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore551 = new EvaluationScore(evaluation31, 14, "Tres Bien.");
		evaluationScoreList55.add(evaluationScore551);
		session.persist(evaluationScore551);
		EvaluationScore evaluationScore552 = new EvaluationScore(evaluation32, 18, "Tres Bien.");
		evaluationScoreList55.add(evaluationScore552);
		session.persist(evaluationScore552);

		// team6
		// student1
		List<EvaluationScore> evaluationScoreList61 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore611 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList61.add(evaluationScore611);
		session.persist(evaluationScore611);
		EvaluationScore evaluationScore612 = new EvaluationScore(evaluation32, 16, "Tres Bien.");
		evaluationScoreList61.add(evaluationScore612);
		session.persist(evaluationScore612);
		// student2
		List<EvaluationScore> evaluationScoreList62 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore621 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList62.add(evaluationScore621);
		session.persist(evaluationScore621);
		EvaluationScore evaluationScore622 = new EvaluationScore(evaluation32, 16, "Tres Bien.");
		evaluationScoreList62.add(evaluationScore622);
		session.persist(evaluationScore622);
		// stuednt3
		List<EvaluationScore> evaluationScoreList63 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore631 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList63.add(evaluationScore631);
		session.persist(evaluationScore631);
		EvaluationScore evaluationScore632 = new EvaluationScore(evaluation32, 16, "Tres Bien.");
		evaluationScoreList63.add(evaluationScore632);
		session.persist(evaluationScore632);
		// student4
		List<EvaluationScore> evaluationScoreList64 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore641 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList64.add(evaluationScore641);
		session.persist(evaluationScore641);
		EvaluationScore evaluationScore642 = new EvaluationScore(evaluation32, 16, "Tres Bien.");
		evaluationScoreList64.add(evaluationScore642);
		session.persist(evaluationScore642);
		// student5
		List<EvaluationScore> evaluationScoreList65 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore651 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList65.add(evaluationScore651);
		session.persist(evaluationScore651);
		EvaluationScore evaluationScore652 = new EvaluationScore(evaluation32, 16, "Tres Bien.");
		evaluationScoreList65.add(evaluationScore652);
		session.persist(evaluationScore652);

		// team7
		// student1
		List<EvaluationScore> evaluationScoreList71 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore711 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList71.add(evaluationScore711);
		session.persist(evaluationScore711);
		EvaluationScore evaluationScore712 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList71.add(evaluationScore712);
		session.persist(evaluationScore712);
		// student2
		List<EvaluationScore> evaluationScoreList72 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore721 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList72.add(evaluationScore721);
		session.persist(evaluationScore721);
		EvaluationScore evaluationScore722 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList72.add(evaluationScore722);
		session.persist(evaluationScore722);
		// student3
		List<EvaluationScore> evaluationScoreList73 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore731 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList73.add(evaluationScore731);
		session.persist(evaluationScore731);
		EvaluationScore evaluationScore732 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList73.add(evaluationScore732);
		session.persist(evaluationScore732);
		// student4
		List<EvaluationScore> evaluationScoreList74 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore741 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList74.add(evaluationScore741);
		session.persist(evaluationScore741);
		EvaluationScore evaluationScore742 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList74.add(evaluationScore742);
		session.persist(evaluationScore742);
		// student5
		List<EvaluationScore> evaluationScoreList75 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore751 = new EvaluationScore(evaluation31, 15, "Tres Bien.");
		evaluationScoreList75.add(evaluationScore751);
		session.persist(evaluationScore751);
		EvaluationScore evaluationScore752 = new EvaluationScore(evaluation32, 20, "Excellent.");
		evaluationScoreList75.add(evaluationScore752);
		session.persist(evaluationScore752);

		// project4
		// team8
		// student1
		List<EvaluationScore> evaluationScoreList81 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore811 = new EvaluationScore(evaluation41, 15, "Tres Bien.");
		evaluationScoreList81.add(evaluationScore811);
		session.persist(evaluationScore811);
		EvaluationScore evaluationScore812 = new EvaluationScore(evaluation42, 20, "Excellent.");
		evaluationScoreList81.add(evaluationScore812);
		session.persist(evaluationScore812);
		EvaluationScore evaluationScore813 = new EvaluationScore(evaluation43, 20, "Excellent.");
		evaluationScoreList81.add(evaluationScore813);
		session.persist(evaluationScore813);
		// student2
		List<EvaluationScore> evaluationScoreList82 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore821 = new EvaluationScore(evaluation41, 15, "Tres Bien.");
		evaluationScoreList82.add(evaluationScore821);
		session.persist(evaluationScore821);
		EvaluationScore evaluationScore822 = new EvaluationScore(evaluation42, 20, "Excellent.");
		evaluationScoreList82.add(evaluationScore822);
		session.persist(evaluationScore822);
		EvaluationScore evaluationScore823 = new EvaluationScore(evaluation43, 20, "Excellent.");
		evaluationScoreList81.add(evaluationScore823);
		session.persist(evaluationScore823);
		// student3
		List<EvaluationScore> evaluationScoreList83 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore831 = new EvaluationScore(evaluation41, 15, "Tres Bien.");
		evaluationScoreList83.add(evaluationScore831);
		session.persist(evaluationScore831);
		EvaluationScore evaluationScore832 = new EvaluationScore(evaluation42, 20, "Excellent.");
		evaluationScoreList83.add(evaluationScore832);
		session.persist(evaluationScore832);
		EvaluationScore evaluationScore833 = new EvaluationScore(evaluation43, 20, "Excellent.");
		evaluationScoreList81.add(evaluationScore833);
		session.persist(evaluationScore833);
		// student4
		List<EvaluationScore> evaluationScoreList84 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore841 = new EvaluationScore(evaluation41, 15, "Tres Bien.");
		evaluationScoreList84.add(evaluationScore841);
		session.persist(evaluationScore841);
		EvaluationScore evaluationScore842 = new EvaluationScore(evaluation42, 20, "Excellent.");
		evaluationScoreList84.add(evaluationScore842);
		session.persist(evaluationScore842);
		EvaluationScore evaluationScore843 = new EvaluationScore(evaluation43, 20, "Excellent.");
		evaluationScoreList81.add(evaluationScore843);
		session.persist(evaluationScore843);

		// project5
		// team9
		// student1
		List<EvaluationScore> evaluationScoreList91 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore911 = new EvaluationScore(evaluation51, 15, "Tres Bien.");
		evaluationScoreList91.add(evaluationScore911);
		session.persist(evaluationScore911);
		EvaluationScore evaluationScore912 = new EvaluationScore(evaluation52, 20, "Excellent.");
		evaluationScoreList91.add(evaluationScore912);
		session.persist(evaluationScore912);
		EvaluationScore evaluationScore913 = new EvaluationScore(evaluation52, 20, "Excellent.");
		evaluationScoreList91.add(evaluationScore913);
		session.persist(evaluationScore913);
		// student2
		List<EvaluationScore> evaluationScoreList92 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore921 = new EvaluationScore(evaluation51, 15, "Tres Bien.");
		evaluationScoreList92.add(evaluationScore921);
		session.persist(evaluationScore921);
		EvaluationScore evaluationScore922 = new EvaluationScore(evaluation52, 16, "Tres Bien.");
		evaluationScoreList92.add(evaluationScore922);
		session.persist(evaluationScore922);
		EvaluationScore evaluationScore923 = new EvaluationScore(evaluation52, 20, "Excellent.");
		evaluationScoreList91.add(evaluationScore923);
		session.persist(evaluationScore923);

		// project6
		// team10
		// student1
		List<EvaluationScore> evaluationScoreList101 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore1011 = new EvaluationScore(evaluation61, 15, "Tres Bien.");
		evaluationScoreList101.add(evaluationScore1011);
		session.persist(evaluationScore1011);
		EvaluationScore evaluationScore1012 = new EvaluationScore(evaluation62, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList101.add(evaluationScore1012);
		session.persist(evaluationScore1012);
		EvaluationScore evaluationScore1013 = new EvaluationScore(evaluation63, 17, "Tres Bien.");
		evaluationScoreList101.add(evaluationScore1013);
		session.persist(evaluationScore1013);
		// student2
		List<EvaluationScore> evaluationScoreList102 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore1021 = new EvaluationScore(evaluation61, 16, "Tres Bien.");
		evaluationScoreList102.add(evaluationScore1021);
		session.persist(evaluationScore1021);
		EvaluationScore evaluationScore1022 = new EvaluationScore(evaluation62, 18, "Tres Bien.");
		evaluationScoreList102.add(evaluationScore1022);
		session.persist(evaluationScore1022);
		EvaluationScore evaluationScore1023 = new EvaluationScore(evaluation63, 17, "Tres Bien.");
		evaluationScoreList102.add(evaluationScore1023);
		session.persist(evaluationScore1023);
		// student3
		List<EvaluationScore> evaluationScoreList103 = new ArrayList<EvaluationScore>();
		EvaluationScore evaluationScore1031 = new EvaluationScore(evaluation61, 15, "Tres Bien.");
		evaluationScoreList103.add(evaluationScore1031);
		session.persist(evaluationScore1031);
		EvaluationScore evaluationScore1032 = new EvaluationScore(evaluation62, 14, "Bien, mais peut mieux faire.");
		evaluationScoreList103.add(evaluationScore1032);
		session.persist(evaluationScore1032);
		EvaluationScore evaluationScore1033 = new EvaluationScore(evaluation63, 17, "Tres Bien.");
		evaluationScoreList103.add(evaluationScore1033);
		session.persist(evaluationScore1033);

		// Student Score
		List<StudentScore> studentScoreList1 = new ArrayList<StudentScore>(); // team1
		StudentScore studentScore1 = new StudentScore(student1, 8, evaluationScoreList11);
		studentScoreList1.add(studentScore1);
		session.persist(studentScore1);
		StudentScore studentScore2 = new StudentScore(student2, 12, evaluationScoreList12);
		studentScoreList1.add(studentScore2);
		session.persist(studentScore2);
		StudentScore studentScore3 = new StudentScore(student3, 11, evaluationScoreList13);
		studentScoreList1.add(studentScore3);
		session.persist(studentScore3);

		List<StudentScore> studentScoreList2 = new ArrayList<StudentScore>(); // team2
		StudentScore studentScore4 = new StudentScore(student4, 16, evaluationScoreList21);
		studentScoreList2.add(studentScore4);
		session.persist(studentScore4);
		StudentScore studentScore5 = new StudentScore(student5, 15, evaluationScoreList22);
		studentScoreList2.add(studentScore5);
		session.persist(studentScore5);
		StudentScore studentScore6 = new StudentScore(student6, 18, evaluationScoreList23);
		studentScoreList2.add(studentScore6);
		session.persist(studentScore6);

		List<StudentScore> studentScoreList3 = new ArrayList<StudentScore>(); // team3
		StudentScore studentScore7 = new StudentScore(student7, 6, evaluationScoreList31);
		studentScoreList3.add(studentScore7);
		session.persist(studentScore7);
		StudentScore studentScore8 = new StudentScore(student8, 15, evaluationScoreList32);
		studentScoreList3.add(studentScore8);
		session.persist(studentScore8);
		StudentScore studentScore9 = new StudentScore(student9, 9, evaluationScoreList33);
		studentScoreList3.add(studentScore9);
		session.persist(studentScore9);
		StudentScore studentScore10 = new StudentScore(student10, 13, evaluationScoreList34);
		studentScoreList3.add(studentScore10);
		session.persist(studentScore10);

		List<StudentScore> studentScoreList4 = new ArrayList<StudentScore>(); // team4
		StudentScore studentScore11 = new StudentScore(student11, 16, evaluationScoreList41);
		studentScoreList4.add(studentScore11);
		session.persist(studentScore11);
		StudentScore studentScore12 = new StudentScore(student12, 13, evaluationScoreList42);
		studentScoreList4.add(studentScore12);
		session.persist(studentScore12);
		StudentScore studentScore13 = new StudentScore(student13, 18, evaluationScoreList43);
		studentScoreList4.add(studentScore13);
		session.persist(studentScore13);
		StudentScore studentScore14 = new StudentScore(student14, 12, evaluationScoreList44);
		studentScoreList4.add(studentScore14);
		session.persist(studentScore14);
		StudentScore studentScore15 = new StudentScore(student15, 11, evaluationScoreList45);
		studentScoreList4.add(studentScore15);
		session.persist(studentScore15);

		List<StudentScore> studentScoreList5 = new ArrayList<StudentScore>(); // team5
		StudentScore studentScore16 = new StudentScore(student16, 16, evaluationScoreList51);
		studentScoreList5.add(studentScore16);
		session.persist(studentScore16);
		StudentScore studentScore17 = new StudentScore(student17, 15, evaluationScoreList52);
		studentScoreList5.add(studentScore17);
		session.persist(studentScore17);
		StudentScore studentScore18 = new StudentScore(student18, 18, evaluationScoreList53);
		studentScoreList5.add(studentScore18);
		session.persist(studentScore18);
		StudentScore studentScore19 = new StudentScore(student19, 18, evaluationScoreList54);
		studentScoreList5.add(studentScore19);
		session.persist(studentScore19);
		StudentScore studentScore20 = new StudentScore(student20, 18, evaluationScoreList55);
		studentScoreList5.add(studentScore20);
		session.persist(studentScore20);

		List<StudentScore> studentScoreList6 = new ArrayList<StudentScore>(); // team6
		StudentScore studentScore21 = new StudentScore(student21, 12, evaluationScoreList61);
		studentScoreList6.add(studentScore21);
		session.persist(studentScore21);
		StudentScore studentScore22 = new StudentScore(student22, 19, evaluationScoreList62);
		studentScoreList6.add(studentScore22);
		session.persist(studentScore22);
		StudentScore studentScore23 = new StudentScore(student23, 17, evaluationScoreList63);
		studentScoreList6.add(studentScore23);
		session.persist(studentScore23);
		StudentScore studentScore24 = new StudentScore(student24, 17, evaluationScoreList64);
		studentScoreList6.add(studentScore24);
		session.persist(studentScore24);
		StudentScore studentScore25 = new StudentScore(student25, 14, evaluationScoreList65);
		studentScoreList6.add(studentScore25);
		session.persist(studentScore25);

		List<StudentScore> studentScoreList7 = new ArrayList<StudentScore>(); // team7
		StudentScore studentScore26 = new StudentScore(student26, 16, evaluationScoreList71);
		studentScoreList7.add(studentScore26);
		session.persist(studentScore26);
		StudentScore studentScore27 = new StudentScore(student27, 15, evaluationScoreList72);
		studentScoreList7.add(studentScore27);
		session.persist(studentScore27);
		StudentScore studentScore28 = new StudentScore(student28, 10, evaluationScoreList73);
		studentScoreList7.add(studentScore28);
		session.persist(studentScore28);
		StudentScore studentScore29 = new StudentScore(student29, 13, evaluationScoreList74);
		studentScoreList7.add(studentScore29);
		session.persist(studentScore29);
		StudentScore studentScore30 = new StudentScore(student30, 12, evaluationScoreList75);
		studentScoreList7.add(studentScore30);
		session.persist(studentScore30);

		List<StudentScore> studentScoreList8 = new ArrayList<StudentScore>(); // team8
		StudentScore studentScore31 = new StudentScore(student10, 16, evaluationScoreList81);
		studentScoreList8.add(studentScore31);
		session.persist(studentScore31);
		StudentScore studentScore32 = new StudentScore(student5, 15, evaluationScoreList82);
		studentScoreList8.add(studentScore32);
		session.persist(studentScore32);
		StudentScore studentScore33 = new StudentScore(student6, 10, evaluationScoreList83);
		studentScoreList8.add(studentScore33);
		session.persist(studentScore33);
		StudentScore studentScore34 = new StudentScore(student7, 13, evaluationScoreList84);
		studentScoreList8.add(studentScore34);
		session.persist(studentScore34);

		List<StudentScore> studentScoreList9 = new ArrayList<StudentScore>(); // team9
		StudentScore studentScore35 = new StudentScore(student6, 17, evaluationScoreList91);
		studentScoreList9.add(studentScore35);
		session.persist(studentScore35);
		StudentScore studentScore36 = new StudentScore(student7, 16, evaluationScoreList92);
		studentScoreList9.add(studentScore36);
		session.persist(studentScore36);

		List<StudentScore> studentScoreList10 = new ArrayList<StudentScore>(); // team10
		StudentScore studentScore37 = new StudentScore(student1, 15, evaluationScoreList101);
		studentScoreList8.add(studentScore37);
		session.persist(studentScore37);
		StudentScore studentScore38 = new StudentScore(student2, 10, evaluationScoreList102);
		studentScoreList8.add(studentScore38);
		session.persist(studentScore38);
		StudentScore studentScore39 = new StudentScore(student7, 13, evaluationScoreList103);
		studentScoreList8.add(studentScore39);
		session.persist(studentScore39);

		// Teams

		Team team1 = new Team(project1, studentList1, 17, studentScoreList1, "");
		session.persist(team1);
		Team team2 = new Team(project1, studentList2, 15, studentScoreList2, "FunGroup");
		session.persist(team2);

		Team team3 = new Team(project2, studentList3, 12, studentScoreList3, "");
		session.persist(team3);

		Team team4 = new Team(project3, studentList4, 10, studentScoreList4, "BestGroup");
		session.persist(team4);
		Team team5 = new Team(project3, studentList5, 16, studentScoreList5, "");
		session.persist(team5);
		Team team6 = new Team(project3, studentList6, 19, studentScoreList6, "");
		session.persist(team6);
		Team team7 = new Team(project3, studentList7, 14, studentScoreList7, "");
		session.persist(team7);

		Team team8 = new Team(project4, studentList8, 17, studentScoreList8, "");
		session.persist(team8);
		Team team9 = new Team(project5, studentList9, 14, studentScoreList9, "");
		session.persist(team9);
		Team team10 = new Team(project6, studentList10, 14, studentScoreList10, "");
		session.persist(team10);

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

		// InitTestData(session);
		InitTestBigData(session);

		persistTransaction1.commit();
		session.close();
	}
}
