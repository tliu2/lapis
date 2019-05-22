package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import business.DetailedProjectDAO;
import persistence.Domain;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.Student;
import persistence.Team;
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
	List<String> domainsName = new ArrayList<String>();
	List<ToolContent> toolContents;
	List<String> toolContentsName = new ArrayList<String>();
	List<Language> languages;
	List<String> languagesName = new ArrayList<String>();
	List<Team> teams;
	List<Student> students = new ArrayList<Student>();
	List<String> studentsName = new ArrayList<String>();
	Boolean hof;
	
	public DetailedProjectDAO detailedProject = new DetailedProjectDAO();
	
	@ManagedProperty(value = "#{searchProjectToDetailedProject}")
	private SearchProjectToDetailedProjectBean searchProjectToDetailedProject;

	public DetailedProjectBean() {
		
	}
	
	@PostConstruct
	public void init() {
		selectedProject = searchProjectToDetailedProject.getProject();
		subject = selectedProject.getSubject();
		projectInfo = detailedProject.readProjectInfo(selectedProject);
		description = projectInfo.getDetailedDescription();
		supervisorName = projectInfo.getSupervisorName();
		domains = projectInfo.getDomaines();
		for (Domain domain : domains) {
			domainsName.add(domain.getName());
		}
		toolContents = projectInfo.getToolContents();
		for (ToolContent toolcontent : toolContents) {
			toolContentsName.add(toolcontent.getName());
		}
		languages = projectInfo.getLanguages();
		for (Language language : languages) {
			languagesName.add(language.getName());
		}
		teams = detailedProject.readTeam(selectedProject);
		for (Team team : teams) {
			List<Student> stud = new ArrayList<Student>();
			
			stud = team.getStudents();
			for (Student student : stud) {
				students.add(student);
			}
		}
		for (Student student : students) {
			studentsName.add(student.getFirstname()+" "+student.getLastname());
		}
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

	public List<String> getDomainsName() {
		return domainsName;
	}

	public void setDomainsName(List<String> domainsName) {
		this.domainsName = domainsName;
	}

	public List<String> getToolContentsName() {
		return toolContentsName;
	}

	public void setToolContentsName(List<String> toolContentsName) {
		this.toolContentsName = toolContentsName;
	}

	public List<String> getLanguagesName() {
		return languagesName;
	}

	public void setLanguagesName(List<String> languagesName) {
		this.languagesName = languagesName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<String> getStudentsName() {
		return studentsName;
	}

	public void setStudentsName(List<String> studentsName) {
		this.studentsName = studentsName;
	}
	
}
