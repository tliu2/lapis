package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import business.DBConnection;
import business.ProjectDAO;
import persistence.Project;

@ManagedBean(name = "dtFilterView")
@ViewScoped
public class FilterViewBean implements Serializable {

	private List<Project> projects;
	private List<Project> filteredProjects;
	private Project selectedProject;

	private ProjectDAO projectDAO = new ProjectDAO();

	@ManagedProperty(value = "#{SearchProjectToDetailedProject}")
	private SearchProjectToDetailedProjectBean navig;

	private Session session = DBConnection.getSession();

	@PostConstruct
	public void init() {
		projects = projectDAO.readAllProject(session);
	}

	public String transitionToDetailledProject() {
		//System.out.println(selectedProject.toString());
		navig.setProject(selectedProject);
		return "detailedProject";
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<Project> getFilteredProjects() {
		return filteredProjects;
	}

	public void setFilteredProjects(List<Project> filteredProjects) {
		this.filteredProjects = filteredProjects;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public SearchProjectToDetailedProjectBean getNavig() {
		return navig;
	}

	public void setNavig(SearchProjectToDetailedProjectBean navig) {
		this.navig = navig;
	}

}