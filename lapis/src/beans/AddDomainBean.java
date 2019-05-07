package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import business.DomainDAO;

@ManagedBean
public class AddDomainBean {

	private String name;
	private DomainDAO domainDAO = new DomainDAO();

	public AddDomainBean() {
	}

	public void createDomain() {
		FacesMessage msg;
		if (name != null && !name.equals("")) {
			domainDAO.createDomain(name);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Domain created !", null);
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

	public DomainDAO getDomainDAO() {
		return domainDAO;
	}

	public void setDomainDAO(DomainDAO domainDAO) {
		this.domainDAO = domainDAO;
	}

}
