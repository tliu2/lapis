package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import business.DetailedProjectDAO;
import persistence.Domain;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.Team;
import persistence.Tool;
import persistence.ToolContent;

@ManagedBean
public class DetailedProjectBean {
	
	Project selectedProject;
	String sujet;
	String description;
	String supervisorName;
	ProjectInfo projectInfo;
	List<Domain> domains;
	List<ToolContent> toolContents;
	List<Tool> tools;
	List<Language> languages;
	List<Team> teams;
	Boolean hof;
	
	public DetailedProjectDAO detailedProject = new DetailedProjectDAO();
	
	@ManagedProperty("#{searchProjectToDetailedProject}")
	private SearchProjectToDetailedProjectBean searchProjectToDetailedProject;

	public DetailedProjectBean() {
		
	}
	
	@PostConstruct
	public void init() {
		selectedProject = searchProjectToDetailedProject.getProject();
		sujet = selectedProject.getSubject();
		projectInfo = detailedProject.readProjectInfo(selectedProject);
		description = projectInfo.getDetailedDescription();
		domains = projectInfo.getDomaines();
		toolContents = projectInfo.getToolContents();
		for (ToolContent toolContent : toolContents) {
			tools.add(toolContent.getTool());
		}
		languages = projectInfo.getLanguages();
		teams = detailedProject.readTeam(selectedProject);
		hof = projectInfo.isHof();
	}
	
}
