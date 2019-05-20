package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import persistence.Project;

@ManagedBean(name = "SearchProjectToDetailedProject", eager = true)
@ApplicationScoped
public class SearchProjectToDetailedProjectBean {
	private Project project;

	public SearchProjectToDetailedProjectBean() {
	}
	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	
}
