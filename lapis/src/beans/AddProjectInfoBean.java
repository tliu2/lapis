package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.hibernate.Session;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import business.CourseDAO;
import business.DBConnection;
import business.DomainDAO;
import business.LanguageDAO;
import business.ProjectDAO;
import business.ProjectInfoDAO;
import business.PromotionDAO;
import business.StudentDAO;
import business.ToolDAO;
import business.UniversityYearDAO;
import persistence.Course;
import persistence.Domain;
import persistence.Evaluation;
import persistence.Language;
import persistence.Project;
import persistence.ProjectInfo;
import persistence.Promotion;
import persistence.Tool;
import persistence.ToolContent;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class AddProjectInfoBean {

	private Map<String, List<String>> data = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataPromo = new HashMap<String, List<String>>();
	private Map<String, List<String>> dataProject = new HashMap<String, List<String>>();

	private String year;
	private String promo;
	private String course;
	private String project;

	private String ide;
	private String vms;
	private String wps;
	private String com;
	private String pms;

	private List<String> years;
	private List<String> promotions;
	private List<String> courses;
	private List<String> projects;

	private List<String> languages = new ArrayList<String>();
	private List<String> selectedLanguages = new ArrayList<String>();
	private DualListModel<String> languagesDual;

	private List<String> domains = new ArrayList<String>();
	private List<String> selectedDomains = new ArrayList<String>();
	private DualListModel<String> domainsDual;

	private List<String> tools = new ArrayList<String>();

	private String supervisorName;
	private String description;
	private String newDomain;

	private boolean hof = false;

	private List<ToolContent> toolContents = new ArrayList<ToolContent>();
	private List<Language> selectLanguages = new ArrayList<Language>();
	private List<Domain> selectDomains = new ArrayList<Domain>();

	private Project selectProject;

	private ProjectInfoDAO projectInfoDAO = new ProjectInfoDAO();
	private ToolDAO toolDAO = new ToolDAO();
	private DomainDAO domainDAO = new DomainDAO();
	private LanguageDAO languageDAO = new LanguageDAO();
	private ProjectDAO projectDAO = new ProjectDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private PromotionDAO promoDAO = new PromotionDAO();
	private UniversityYearDAO yearDAO = new UniversityYearDAO();

	private Session session = DBConnection.getSession();

	public AddProjectInfoBean() {

	}

	@PostConstruct
	public void init() {
		List<UniversityYear> allYears = yearDAO.readAllUniversityYears();
		years = new ArrayList<String>();
		for (UniversityYear universityYear : allYears) {
			years.add(universityYear.toString());
		}

		for (UniversityYear universityYear : allYears) {
			int id = universityYear.getId();
			List<Promotion> promos = promoDAO.readPromoByYearId(id);
			List<String> promoList = new ArrayList<String>();
			for (Promotion promo : promos) {
				promoList.add(promo.toString());
			}
			data.put(universityYear.toString(), promoList);
		}

		List<Promotion> allPromos = promoDAO.readAllPromo();
		for (Promotion promotion : allPromos) {
			int id = promotion.getId();
			List<Course> courses = courseDAO.readCourseByPromoId(id);
			List<String> courseList = new ArrayList<String>();
			for (Course course : courses) {
				courseList.add(course.getName());
			}
			dataPromo.put(promotion.toString(), courseList);
		}

		List<Course> allCourses = courseDAO.readAllCourse();
		for (Course course : allCourses) {
			int id = course.getId();
			List<Project> projects = projectDAO.readProjectByCourseId(id);
			List<String> projectList = new ArrayList<String>();
			for (Project project : projects) {
				projectList.add(project.getSubject());
			}
			dataProject.put(course.getName(), projectList);
		}

		List<Language> allLanguages = languageDAO.readAllLanguages();
		languages = new ArrayList<String>();
		for (Language language : allLanguages) {
			languages.add(language.getName());
		}
		languagesDual = new DualListModel<String>(languages, selectedLanguages);

		List<Domain> allDomains = domainDAO.readAllDomains();
		domains = new ArrayList<String>();
		for (Domain domain : allDomains) {
			domains.add(domain.getName());
		}
		domainsDual = new DualListModel<String>(domains, selectedDomains);

		List<Tool> allTools = toolDAO.readAllTools();
		for (Tool tool : allTools) {
			ToolContent toolC = new ToolContent(tool, "");
			toolContents.add(toolC);
		}

	}

	public void addProjectInfo() {
		FacesMessage msg;
		if (promo != null && year != null && course != null && project != null && supervisorName != null
				&& !supervisorName.equals("") && description != null && !description.equals("")) {
			
			for (String language : languagesDual.getTarget()) {
				Language lang = languageDAO.readLanguageByName(language).get(0);
				selectLanguages.add(lang);
			}

			for (String domain : domainsDual.getTarget()) {
				Domain dom = domainDAO.readDomainByName(domain).get(0);
				selectDomains.add(dom);
			}

			if (!projectInfoExist()) {
				projectInfoDAO.createProjectInfo(selectProject, supervisorName, hof, selectDomains, selectLanguages,
						toolContents, description, session);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informations added !", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				int courseID = courseDAO.getIdFromCourseString(course);
				List<Project> projects = projectDAO.readProjectByCourseId(courseID);
				for (Project p : projects) {
					if (p.getSubject().equals(project)) {
						selectProject = p;
					}
				}
				int projectID = projectDAO.getIdFromProjectString(selectProject.toString());
				ProjectInfo projectInfo = projectInfoDAO.getProjectInfoByProjectID(projectID, session).get(0);
				projectInfo.setDomaines(selectDomains);
				projectInfo.setHof(hof);
				projectInfo.setLanguages(selectLanguages);
				projectInfo.setSupervisorName(supervisorName);
				projectInfo.setDetailedDescription(description);
				projectInfo.setToolContents(toolContents);
				projectInfoDAO.updateInfo(projectInfo, session);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Project update !", null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}

		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid : missing information !", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}
	}

	public void updateFields() {
		if (projectInfoExist()) {
			if (projects != null && !projects.equals("")) {

				int courseID = courseDAO.getIdFromCourseString(course);
				List<Project> projects = projectDAO.readProjectByCourseId(courseID);
				for (Project p : projects) {
					if (p.getSubject().equals(project)) {
						selectProject = p;
					}
				}
				int projectID = projectDAO.getIdFromProjectString(selectProject.toString());
				List<ProjectInfo> projectInfos = projectInfoDAO.getProjectInfoByProjectID(projectID, session);
				ProjectInfo projectInfo = projectInfos.get(0);
				ide = projectInfo.getToolContents().get(0).getName();
				vms = projectInfo.getToolContents().get(1).getName();
				wps = projectInfo.getToolContents().get(2).getName();
				// com = projectInfo.getToolContents().get(3).getName();
				// pms = projectInfo.getToolContents().get(4).getName();
				hof = projectInfo.isHof();
				description = projectInfo.getDetailedDescription();
				supervisorName = projectInfo.getSupervisorName();
				
				selectedLanguages.clear();
				for (int index = 0; index < projectInfo.getLanguages().size(); index++) {
					selectedLanguages.add(projectInfo.getLanguages().get(index).getName());
					if (languages.contains(selectedLanguages.get(index))) {
						languages.remove(selectedLanguages.get(index));
					}
				}
				languagesDual = new DualListModel<String>(languages, selectedLanguages);
				
				selectedDomains.clear();
				for (int index = 0; index < projectInfo.getDomaines().size(); index++) {
					selectedDomains.add(projectInfo.getDomaines().get(index).getName());
					if (domains.contains(selectedDomains.get(index))) {
						domains.remove(selectedDomains.get(index));
					}
				}
				domainsDual = new DualListModel<String>(domains, selectedDomains);
				
				toolContents = projectInfo.getToolContents();
			}
		}
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Tool Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onChange() {
		if (year != null && !year.equals("")) {
			promotions = data.get(year);
		} else {
			promotions = new ArrayList<String>();
		}
	}

	public void onChangeCourse() {
		if (promo != null && !promo.equals("")) {
			courses = dataPromo.get(promo);
		} else {
			courses = new ArrayList<String>();
		}
	}

	public void onChangeProject() {
		if (course != null && !course.equals("")) {
			projects = dataProject.get(course);
		} else {
			projects = new ArrayList<String>();
		}
	}

	public boolean projectInfoExist() {

		int courseID = courseDAO.getIdFromCourseString(course);
		List<Project> projects = projectDAO.readProjectByCourseId(courseID);
		for (Project p : projects) {
			if (p.getSubject().equals(project)) {
				selectProject = p;
			}
		}

		int projectID = projectDAO.getIdFromProjectString(selectProject.toString());
		List<ProjectInfo> projectInfos = projectInfoDAO.getProjectInfoByProjectID(projectID, session);
		if (projectInfos.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void addDomain() {
		Domain domain = new Domain(newDomain);
		domainDAO.createDomain(domain);
		domains.add(domain.getName());
		domainsDual = new DualListModel<String>(domains, selectedDomains);
	}
	
	public void refreshDomainList() {
		domainsDual = new DualListModel<String>(domains, selectedDomains);
	}

	
	public DualListModel<String> getDomainsDual() {
		return domainsDual;
	}

	public void setDomainsDual(DualListModel<String> domainsDual) {
		this.domainsDual = domainsDual;
	}
	
	public List<String> getSelectedDomains() {
		return selectedDomains;
	}

	public void setSelectedDomains(List<String> selectedDomains) {
		this.selectedDomains = selectedDomains;
	}

	public List<Domain> getSelectDomains() {
		return selectDomains;
	}

	public void setSelectDomains(List<Domain> selectDomains) {
		this.selectDomains = selectDomains;
	}

	public List<String> getSelectedLanguages() {
		return selectedLanguages;
	}

	public void setSelectedLanguages(List<String> selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

	public DualListModel<String> getLanguagesDual() {
		return languagesDual;
	}

	public void setLanguagesDual(DualListModel<String> languagesDual) {
		this.languagesDual = languagesDual;
	}

	public List<Language> getSelectLanguages() {
		return selectLanguages;
	}

	public void setSelectLanguages(List<Language> selectLanguages) {
		this.selectLanguages = selectLanguages;
	}

	public List<Domain> getSelectDomain() {
		return selectDomains;
	}

	public void setSelectDomain(List<Domain> selectDomain) {
		this.selectDomains = selectDomain;
	}

	public Project getSelectProject() {
		return selectProject;
	}

	public void setSelectProject(Project selectProject) {
		this.selectProject = selectProject;
	}

	public ProjectInfoDAO getProjectInfoDAO() {
		return projectInfoDAO;
	}

	public void setProjectInfoDAO(ProjectInfoDAO projectInfoDAO) {
		this.projectInfoDAO = projectInfoDAO;
	}

	public List<ToolContent> getToolContents() {
		return toolContents;
	}

	public void setToolContents(List<ToolContent> toolContents) {
		this.toolContents = toolContents;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public List<String> getTools() {
		return tools;
	}

	public void setTools(List<String> tools) {
		this.tools = tools;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHof() {
		return hof;
	}

	public void setHof(boolean hof) {
		this.hof = hof;
	}

	public Map<String, List<String>> getDataPromo() {
		return dataPromo;
	}

	public void setDataPromo(Map<String, List<String>> dataPromo) {
		this.dataPromo = dataPromo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<String> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<String> promotions) {
		this.promotions = promotions;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public PromotionDAO getPromoDAO() {
		return promoDAO;
	}

	public void setPromoDAO(PromotionDAO promoDAO) {
		this.promoDAO = promoDAO;
	}

	public UniversityYearDAO getYearDAO() {
		return yearDAO;
	}

	public void setYearDAO(UniversityYearDAO yearDAO) {
		this.yearDAO = yearDAO;
	}

	public Map<String, List<String>> getDataProject() {
		return dataProject;
	}

	public void setDataProject(Map<String, List<String>> dataProject) {
		this.dataProject = dataProject;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public String getIde() {
		return ide;
	}

	public void setIde(String ide) {
		this.ide = ide;
	}

	public String getVms() {
		return vms;
	}

	public void setVms(String vms) {
		this.vms = vms;
	}

	public String getWps() {
		return wps;
	}

	public void setWps(String wps) {
		this.wps = wps;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getPms() {
		return pms;
	}

	public void setPms(String pms) {
		this.pms = pms;
	}

	public ToolDAO getToolDAO() {
		return toolDAO;
	}

	public void setToolDAO(ToolDAO toolDAO) {
		this.toolDAO = toolDAO;
	}

	public DomainDAO getDomainDAO() {
		return domainDAO;
	}

	public void setDomainDAO(DomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

	public LanguageDAO getLanguageDAO() {
		return languageDAO;
	}

	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}
	
	public String getNewDomain() {
		return newDomain;
	}

	public void setNewDomain(String newDomain) {
		this.newDomain = newDomain;
	}
}
