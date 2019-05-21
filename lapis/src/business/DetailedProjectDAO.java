package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Project;
import persistence.ProjectInfo;
import persistence.Team;

public class DetailedProjectDAO {
	
	public ProjectInfo readProjectInfo(Project selectedProject) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from ProjectInfo pi where pi.project = :selectedProject ");
		readQuery.setEntity("selectedProject", selectedProject);
		List resultList = readQuery.list();
		ProjectInfo result = (ProjectInfo) resultList.get(0);
		readTransaction.commit();

		return result;
	}
	
	public List<Team> readTeam(Project selectedProject) {
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from Team t where t.project = :selectedProject ");
		readQuery.setEntity("selectedProject", selectedProject);
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

}
