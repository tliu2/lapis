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
	
	public List<ProjectInfo> getProjectInfoByProjectID(int projectID) {
		Session session = DBConnection.getSession();
		
		Transaction readTransaction = session.beginTransaction();
		Query readQuery = session.createQuery("from ProjectInfo pi where pi.project.id = :id");
		readQuery.setInteger("id", projectID);
		List result = readQuery.list();
		readTransaction.commit();
		return result;
	}
	
	public void updateInfo(int projectID, String supervisorName, boolean isHof, List<Domain> domaines, List<Language> languages, List<ToolContent> toolContents, String detailedDescription) {
		Session session = DBConnection.getSession();	
		Transaction updateTransaction = session.beginTransaction();
		Query updateQuery = session.createQuery("update ProjectInfo pi set pi.detailedDescription = :detailedDescription, pi.supervisorName =:supervisorName, pi.isHof =:isHof where pi.project.id =:id");
		updateQuery.setInteger("id", projectID);
		updateQuery.setString("supervisorName", supervisorName);
		updateQuery.setBoolean("isHof", isHof);
		//updateQuery.setParameterList("domaines", domaines);
		//updateQuery.setParameterList("languages", languages);
		//updateQuery.setParameterList("toolContents",toolContents);
		updateQuery.setString("detailedDescription",detailedDescription);
		updateQuery.executeUpdate();
		updateTransaction.commit();
		
	}
}
