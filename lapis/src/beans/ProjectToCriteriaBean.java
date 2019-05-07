package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "projectToCriteria", eager = true)
@ApplicationScoped
public class ProjectToCriteriaBean {

	private String project;

	public ProjectToCriteriaBean() {
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
