package persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ProjectInfo {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Project.class)
	private Project project;
	private String supervisorName;
	private boolean isHof;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Domain.class)
	private List<Domain> domaines = new ArrayList<Domain>();
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Language.class)
	private List<Language> languages = new ArrayList<Language>();
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = ToolContent.class)
	private List<ToolContent> toolContents = new ArrayList<ToolContent>();
	private String detailedDescription;
	
	public ProjectInfo(Project project, String supervisorName, boolean isHof, List<Domain> domaines,
			List<Language> languages, List<ToolContent> toolContents, String detailedDescription) {
		this.project = project;
		this.supervisorName = supervisorName;
		this.isHof = isHof;
		this.domaines = domaines;
		this.languages = languages;
		this.toolContents = toolContents;
		this.detailedDescription = detailedDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public boolean isHof() {
		return isHof;
	}

	public void setHof(boolean isHof) {
		this.isHof = isHof;
	}

	public List<Domain> getDomaines() {
		return domaines;
	}

	public void setDomaines(List<Domain> domaines) {
		this.domaines = domaines;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public List<ToolContent> getToolContents() {
		return toolContents;
	}

	public void setToolContents(List<ToolContent> toolContents) {
		this.toolContents = toolContents;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public ProjectInfo() {
	}
	
	
}
