package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.Domain;
import persistence.Evaluation;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.ToolContent;

public class ProjectInfoDAO {

	public void createProjectInfo(Project project, String supervisorName, boolean isHof, List<Domain> domaines, List<Language> languages, List<ToolContent> toolContents, String detailedDescription) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		ProjectInfo projectInfo = new ProjectInfo(project, supervisorName, isHof, domaines, languages, toolContents, detailedDescription);
		System.out.println(project.toString());
		session.persist(projectInfo);
		readTransaction.commit();

	}
	
}
