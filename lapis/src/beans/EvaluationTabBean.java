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

@ManagedBean(name = "evalTab")
@ViewScoped
public class EvaluationTabBean implements Serializable {

	private List<Criterion> criteriaList;
	private List<String> criteriaName;
	private List<Integer> percentages;
	private String visibleText;
	private int visiblePercentage;
	private List<Evaluation> evaList;
	
	private EvaluationDAO evaluationDAO = new EvaluationDAO();

	private LinkEvaluationToProjectDAO service = new LinkEvaluationToProjectDAO();
	
	public EvaluationTabBean(){
		
	}

	@PostConstruct
	public void init() {
		evaList = service.initEvaluationList();
		criteriaList = service.readAllCriterion();
		criteriaName = service.readAllCriterionName(criteriaList);
		percentages = service.makePercentageList();
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
	
	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

	public int getVisiblePercentage() {
		return visiblePercentage;
	}

	public void setVisiblePercentage(int visiblePercentage) {
		this.visiblePercentage = visiblePercentage;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		/*
		 * Evaluation eval = ((Evaluation)event.getObject()); Session session =
		 * DBConnection.getSession(); Transaction readTransaction =
		 * session.beginTransaction(); session.persist(eval); readTransaction.commit();
		 */
		
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
		Criterion car2Add = service.readAllCriterion().get(0);
		criteriaList.add(car2Add);
		FacesMessage msg = new FacesMessage("New Evaluation added");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void persistEvaluation() {
		FacesMessage msg;
		evaluationDAO.persistEvaluation(evaList);
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluation Created !", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}