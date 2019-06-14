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

	public void createProjectInfo(Project project, String supervisorName, boolean isHof, List<Domain> domaines,
			List<Language> languages, List<ToolContent> toolContents, String detailedDescription, Session session) {
		Transaction readTransaction = session.beginTransaction();
		ProjectInfo projectInfo = new ProjectInfo(project, supervisorName, isHof, domaines, languages, toolContents,
				detailedDescription);
		System.out.println(project.toString());
		session.persist(projectInfo);
		readTransaction.commit();
	}

	public List<ProjectInfo> getProjectInfoByProjectID(int projectID, Session session) {

		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from ProjectInfo pi where pi.project.id = :id");
		readQuery.setInteger("id", projectID);
		List result = readQuery.list();
		readTransaction.commit();
		return result;
	}
	
	public List<ProjectInfo> getProjectInfoHofByYear(int year) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from ProjectInfo pi where pi.isHof = true and pi.project.course.promotion.year.first = :year");
		readQuery.setInteger("year", year);
		List result = readQuery.list();
		readTransaction.commit();
		return result;
	}

	public void updateInfo(ProjectInfo projectInfo, Session session) {
		Transaction updateTransaction = session.beginTransaction();
		session.update(projectInfo);
		session.flush();
		updateTransaction.commit();
		session.close();
	}
}
