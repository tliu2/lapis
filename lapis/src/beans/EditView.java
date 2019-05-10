package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import business.LinkCriteriaToProjectDAO;
import persistence.Criterion;

@ManagedBean(name = "dtEditView")
@ViewScoped
public class EditView implements Serializable {

	private List<Criterion> cars1;
	private List<Criterion> cars2;

	@ManagedProperty("#{criteriaService}")
	private LinkCriteriaToProjectDAO service;

	@PostConstruct
	public void init() {
		cars1 = service.readAllCriterion();
		cars2 = service.readAllCriterion();
	}

	public List<Criterion> getCars1() {
		return cars1;
	}

	public List<Criterion> getCars2() {
		return cars2;
	}

	public void setService(LinkCriteriaToProjectDAO service) {
		this.service = service;
	}

	public LinkCriteriaToProjectDAO getService() {
		return service;
	}

	public void setCars1(List<Criterion> cars1) {
		this.cars1 = cars1;
	}

	public void setCars2(List<Criterion> cars2) {
		this.cars2 = cars2;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Criterion Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
}