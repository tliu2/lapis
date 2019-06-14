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

import business.CriterionDAO;
import business.DBConnection;
import business.EvaluationDAO;
import business.ProjectDAO;
import persistence.Criterion;
import persistence.Evaluation;
import persistence.Project;

@ManagedBean(name = "evalTab")
@ViewScoped
public class EvaluationTabBean implements Serializable {

	private List<Criterion> criteriaList;
	private List<String> criteriaName;
	private List<Integer> percentages = new ArrayList<Integer>();
	private Criterion visibleText;
	private int visiblePercentage;
	private List<Evaluation> evaList;
	private List<Evaluation> stateSaveEvaList;
	private String projectName;

	private EvaluationDAO evaluationDAO = new EvaluationDAO();
	private CriterionDAO criterionDAO = new CriterionDAO();
	private ProjectDAO projectDAO = new ProjectDAO();

	private Session session = DBConnection.getSession();

	@ManagedProperty("#{projectToCriteria}")
	private ProjectToCriteriaBean projectToCriteria;

	public EvaluationTabBean() {
 
	}

	@PostConstruct
	public void init() {
		projectName = projectToCriteria.getProject();
		Project selectedProject = projectDAO.getProjectFromProjectString(projectName, session);
		evaList = initEvaluationList(selectedProject);
		criteriaList = criterionDAO.readAllCriterion(session);
		criteriaName = readAllCriterionName(criteriaList);
		for (int i = 1; i < 100; i++) {
			percentages.add(i);
		}
		stateSaveEvaList = new ArrayList<Evaluation>();
	}

	public List<String> readAllCriterionName(List<Criterion> criteriaList) {
		List<String> criteriaNameList = new ArrayList<String>();
		for (Criterion criterion : criteriaList) {
			criteriaNameList.add(criterion.getName());
		}
		return criteriaNameList;
	}

	public List<Evaluation> initEvaluationList(Project selectedProject) { // Dans le bean
		List<Evaluation> evaList = new ArrayList<Evaluation>();
		for (int i = 0; i < 1; i++) {
			Criterion crit = new Criterion("Select Criterion", "");
			Evaluation eval = new Evaluation(crit, 0);
			evaList.add(eval);
		}
		if (selectedProject.getEvaluation().isEmpty()) {
			return evaList;
		} else {
			return selectedProject.getEvaluation();
		}
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

	public void setcriteriaList(List<Criterion> criteriaList) {
		this.criteriaList = criteriaList;
	}


	public Criterion getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(Criterion visibleText) {
		this.visibleText = visibleText;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
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
		Criterion criterion = criterionDAO.getOneCriterionByName(criterionName);
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

		// evaluationDAO.persistEvaluation(evaList);
		List<Integer> idList = getEvaluationIDsFromEvaluationList(evaList);

		for (Evaluation eval : evaList) {
			System.out.println(eval.getId());
			if (idList.contains(eval.getId()) && eval.getId() != 0) {
				evaluationDAO.updateEval(eval, session);
			} else {
				evaluationDAO.persistOneEvaluation(eval, session);
			}
		}

		projectName = projectToCriteria.getProject();
		Project selectedProject = projectDAO.getProjectFromProjectString(projectName, session);
		selectedProject.setEvaluation(evaList);

		projectDAO.updateInfo(selectedProject, session);

		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluation Created !", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Integer> getEvaluationIDsFromEvaluationList(List<Evaluation> evaList) { // Dans le Bean
		List<Integer> iDList = new ArrayList<Integer>();
		for (Evaluation eval : evaList) {
			iDList.add(eval.getId());
		}
		return iDList;
	}
}