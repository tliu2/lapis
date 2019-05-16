package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import business.DBConnection;
import business.EvaluationDAO;
import business.LinkEvaluationToProjectDAO;
import persistence.Criterion;
import persistence.Evaluation;
import persistence.Project;

@ManagedBean(name = "evalTab")
@ViewScoped
public class EvaluationTabBean implements Serializable {

	private List<Criterion> criteriaList;
	private List<String> criteriaName;
	private List<Integer> percentages;
	private Criterion visibleText;
	private int visiblePercentage;
	private List<Evaluation> evaList;
	private List<Evaluation> stateSaveEvaList;
	private String projectName;

	private EvaluationDAO evaluationDAO = new EvaluationDAO();

	private LinkEvaluationToProjectDAO service = new LinkEvaluationToProjectDAO();
	
	private Session session = DBConnection.getSession();
	
	@ManagedProperty("#{projectToCriteria}")
	private ProjectToCriteriaBean projectToCriteria;

	public EvaluationTabBean() {

	}

	@PostConstruct
	public void init() {
		projectName = projectToCriteria.getProject();
		Project selectedProject = service.getProjectFromProjectString(projectName, session);
		evaList = service.initEvaluationList(selectedProject);
		criteriaList = service.readAllCriterion(session);
		criteriaName = service.readAllCriterionName(criteriaList);
		percentages = service.makePercentageList();
		stateSaveEvaList = new ArrayList<Evaluation>();
	}

	public List<Evaluation> getEvaList() {
		return evaList;
	}

	public void setEvaList(List<Evaluation> evaList) {
		this.evaList = evaList;
	}

	public List<Criterion> getCriteriaList() {
		return criteriaList;
	}

	public void setCriteriaList(List<Criterion> criteriaList) {
		this.criteriaList = criteriaList;
	}

	public List<String> getCriteriaName() {
		return criteriaName;
	}

	public void setCriteriaName(List<String> criteriaName) {
		this.criteriaName = criteriaName;
	}

	public List<Criterion> getcriteriaList() {
		return criteriaList;
	}

	public EvaluationDAO getEvaluationDAO() {
		return evaluationDAO;
	}

	public void setEvaluationDAO(EvaluationDAO evaluationDAO) {
		this.evaluationDAO = evaluationDAO;
	}

	public List<Integer> getPercentages() {
		return percentages;
	}

	public void setPercentages(List<Integer> percentages) {
		this.percentages = percentages;
	}

	public LinkEvaluationToProjectDAO getService() {
		return service;
	}

	public void setcriteriaList(List<Criterion> criteriaList) {
		this.criteriaList = criteriaList;
	}

	public void setService(LinkEvaluationToProjectDAO service) {
		this.service = service;
	}

	public Criterion getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(Criterion visibleText) {
		this.visibleText = visibleText;
	}

	public int getVisiblePercentage() {
		return visiblePercentage;
	}

	public void setVisiblePercentage(int visiblePercentage) {
		this.visiblePercentage = visiblePercentage;
	}

	public List<Evaluation> getStateSaveEvaList() {
		return stateSaveEvaList;
	}

	public void setStateSaveEvaList(List<Evaluation> stateSaveEvaList) {
		this.stateSaveEvaList = stateSaveEvaList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ProjectToCriteriaBean getProjectToCriteria() {
		return projectToCriteria;
	}

	public void setProjectToCriteria(ProjectToCriteriaBean projectToCriteria) {
		this.projectToCriteria = projectToCriteria;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		Evaluation eva = (Evaluation) event.getObject();
		String criterionName = eva.getCriterion().getName();
		Criterion criterion = service.getCriterionByName(criterionName);
		eva.setCriterion(criterion);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void onAddNew() {
		// Add one new car to the table
		Criterion crit = new Criterion("Select Criterion", "");
		Evaluation eval2Add = new Evaluation(crit, 0);
		evaList.add(eval2Add);
		FacesMessage msg = new FacesMessage("New Evaluation added");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void persistEvaluation() {
		FacesMessage msg;
		
		//evaluationDAO.persistEvaluation(evaList);
		List<Integer> idList = service.getEvaluationIDsFromEvaluationList(evaList);
		
		for (Evaluation eval : evaList) {
			System.out.println(eval.getId());
			if (idList.contains(eval.getId())) {	
				service.updateEval(eval, session);
			}else {
				evaluationDAO.persistOneEvaluation(eval, session);
			}
		}
		
		projectName = projectToCriteria.getProject();
		Project selectedProject = service.getProjectFromProjectString(projectName, session);
		selectedProject.setEvaluation(evaList);

		service.updateInfo(selectedProject, session);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluation Created !", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}