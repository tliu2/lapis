package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import business.CriterionDAO;

@ManagedBean
public class AddCriterionBean {
	private String name;
	private String description;
	
	private CriterionDAO critDAO = new CriterionDAO();
	
	public AddCriterionBean() {
	}
	
	public void createCriterion() {
		FacesMessage msg;
		if((name != null && !name.equals(""))&&(description != null && !description.equals(""))) {
			critDAO.createCriterion(name, description);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Criterion created !", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid : missing information !", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public CriterionDAO getCritDAO() {
		return critDAO;
	}

	public void setCritDAO(CriterionDAO critDAO) {
		this.critDAO = critDAO;
	}
	
	
}
