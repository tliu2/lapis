package beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import business.DomainDAO;
import persistence.Domain;

@ManagedBean
public class AddDomainBean {

	private String name;
	private DomainDAO domainDAO = new DomainDAO();

	private java.util.List<Domain> domains = new ArrayList<Domain>();
	private ArrayList<String> items = new ArrayList<String>();

	public AddDomainBean() {
	}

	@PostConstruct
	public void init() {
		System.out.println("ok");
		domains = domainDAO.readAllDomains();
		items = new ArrayList<String>();

		for (Domain domain : domains)
			items.add(domain.getName());

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

	public java.util.List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(java.util.List<Domain> domains) {
		this.domains = domains;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
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


