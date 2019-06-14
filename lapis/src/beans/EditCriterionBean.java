package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import business.*;
import persistence.*;


@ManagedBean
@ViewScoped
public class EditCriterionBean {

	private List<String> criterions = new ArrayList<String>();
	private String selectedCriterion;
	private String description;
	
	private Criterion criterion;

	private CriterionDAO criterionDAO = new CriterionDAO();

	
	public EditCriterionBean() {
	}

	@PostConstruct
	public void init() {
		List<Criterion> criterionList = criterionDAO.readAllCriterion();
		for(Criterion criterion: criterionList) {
			criterions.add(criterion.getName());
		}
	}
	
	public void updateDescription() {
		criterion = criterionDAO.getCriterionByName(selectedCriterion).get(0);
		description = criterion.getDescription();
	}

	public void updateCriterion() {
		//criterion.setDescription(description);
		System.out.println(description + selectedCriterion);
		criterionDAO.updateDescription(description, selectedCriterion);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSelectedCriterion() {
		return selectedCriterion;
	}

	public void setSelectedCriterion(String selectedCriterion) {
		this.selectedCriterion = selectedCriterion;
	}

	public Criterion getCriterion() {
		return criterion;
	}

	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	public List<String> getCriterions() {
		return criterions;
	}

	public void setCriterions(List<String> criterions) {
		this.criterions = criterions;
	}

}