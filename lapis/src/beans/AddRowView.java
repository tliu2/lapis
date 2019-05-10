package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import business.LinkCriteriaToProjectDAO;
import persistence.Criterion;

@ManagedBean(name = "dtAddRowView")
@ViewScoped
public class AddRowView implements Serializable {

	private List<Criterion> cars1;
	private List<String> carsName;
	private List<Integer> percentages;
	private int firstPercentage;

	@ManagedProperty("#{criteriaService}")
	private LinkCriteriaToProjectDAO service;

	@PostConstruct
	public void init() {
		cars1 = service.readAllCriterion();
		carsName = service.readAllCriterionName(cars1);
		percentages = service.makePercentageList();
		firstPercentage = percentages.get(0);
	}

	public List<Criterion> getCars1() {
		return cars1;
	}

	public List<Integer> getPercentages() {
		return percentages;
	}

	public void setPercentages(List<Integer> percentages) {
		this.percentages = percentages;
	}

	public LinkCriteriaToProjectDAO getService() {
		return service;
	}

	public void setCars1(List<Criterion> cars1) {
		this.cars1 = cars1;
	}

	public void setService(LinkCriteriaToProjectDAO service) {
		this.service = service;
	}
	
	public int getFirstPercentage() {
		return firstPercentage;
	}

	public void setFirstPercentage(int firstPercentage) {
		this.firstPercentage = firstPercentage;
	}

	public List<String> getCarsName() {
		return carsName;
	}

	public void setCarsName(List<String> carsName) {
		this.carsName = carsName;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onAddNew() {
		// Add one new car to the table
		Criterion car2Add = service.readAllCriterion().get(0);
		cars1.add(car2Add);
		FacesMessage msg = new FacesMessage("New Criterion added");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRemoveNew() {
		// Remove one new car to the table
		int last = cars1.size();
		cars1.remove(last-1);
		FacesMessage msg = new FacesMessage("Last Criterion removed");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}