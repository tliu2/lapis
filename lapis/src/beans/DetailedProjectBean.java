package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import business.DetailedProjectDAO;
import persistence.Domain;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.Team;
import persistence.Tool;
import persistence.ToolContent;

@ManagedBean(name = "detailedProject")
@ViewScoped
public class DetailedProjectBean {
	
	Project selectedProject;
	String subject;
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
		subject = selectedProject.getSubject();
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

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public List<ToolContent> getToolContents() {
		return toolContents;
	}

	public void setToolContents(List<ToolContent> toolContents) {
		this.toolContents = toolContents;
	}

	public List<Tool> getTools() {
		return tools;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Boolean getHof() {
		return hof;
	}

	public void setHof(Boolean hof) {
		this.hof = hof;
	}

	public DetailedProjectDAO getDetailedProject() {
		return detailedProject;
	}

	public void setDetailedProject(DetailedProjectDAO detailedProject) {
		this.detailedProject = detailedProject;
	}

	public SearchProjectToDetailedProjectBean getSearchProjectToDetailedProject() {
		return searchProjectToDetailedProject;
	}

	public void setSearchProjectToDetailedProject(SearchProjectToDetailedProjectBean searchProjectToDetailedProject) {
		this.searchProjectToDetailedProject = searchProjectToDetailedProject;
	}
	
}
