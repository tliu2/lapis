package beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import business.LanguageDAO;
import persistence.Language;

@ManagedBean
public class AddLanguageBean {

	private String name;
	private LanguageDAO languageDAO = new LanguageDAO();

	private java.util.List<Language> languages = new ArrayList<Language>();
	private ArrayList<String> items = new ArrayList<String>();

	public AddLanguageBean() {
	}

	@PostConstruct
	public void init() {
		System.out.println("ok");
		languages = languageDAO.readAllLanguages();
		items = new ArrayList<String>();

		for (Language language : languages)
			items.add(language.getName());

	}

	public void createLanguage() {
		FacesMessage msg;
		if (name != null && !name.equals("")) {
			languageDAO.createLanguage(name);
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

	public LanguageDAO getLanguageDAO() {
		return languageDAO;
	}

	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public java.util.List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(java.util.List<Language> languages) {
		this.languages = languages;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

}
