package beans;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Session;

import business.DBConnection;
import business.ProjectDAO;
import persistence.Project;

@ManagedBean(name="dtFilterView")
@ViewScoped
public class FilterViewBean implements Serializable {
     
	private List<Project> projects;
    private List<Project> filteredProjects;
     
    private ProjectDAO projectDAO = new ProjectDAO();
    
    private Session session = DBConnection.getSession();
 
    @PostConstruct
    public void init() {
    	projects = projectDAO.readAllProject(session);
    }
     
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
     
   
}