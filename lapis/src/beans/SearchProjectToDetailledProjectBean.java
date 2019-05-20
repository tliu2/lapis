package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import persistence.Project;

@ManagedBean(name = "SearchProjectToDetailledProject", eager = true)
@ApplicationScoped
public class SearchProjectToDetailledProjectBean {
	private Project project;

	public SearchProjectToDetailledProjectBean() {
	}
	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	
}
