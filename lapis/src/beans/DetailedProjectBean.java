package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
public class DetailedProjectBean {
	
	@ManagedProperty("#{searchProjectToDetailedProject}")
	private SearchProjectToDetailedProjectBean searchProjectToDetailedProject;

	public DetailedProjectBean() {
		
	}
	
	
	
}
