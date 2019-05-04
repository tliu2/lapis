package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import business.UniversityYearDAO;
import persistence.UniversityYear;

@ManagedBean
public class SelectPromoBean {
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private List<UniversityYear> universityYears = new ArrayList<UniversityYear>();

	private String year;
	
	@ManagedProperty(value = "#{navig}")
	private NavigationBean navig;

	private UniversityYearDAO yearDAO = new UniversityYearDAO();

	public SelectPromoBean() {
		System.out.println("ok");
		universityYears = yearDAO.readAllUniversityYears();

		items = new ArrayList<SelectItem>();
		for (UniversityYear universityYear : universityYears) {
			SelectItem menuChoice = new SelectItem(universityYear.getFirst() + "-" + universityYear.getLast());
			items.add(menuChoice);
		}
	}

	public String toAddCourse() {
		navig.setYear(year);
		System.out.println(navig.getYear());
		return "addCourse";
	}
	
	public List<SelectItem> getItems() {
		return items;
	}

	public void setItems(List<SelectItem> items) {
		this.items = items;
	}

	public List<UniversityYear> getUniversityYears() {
		return universityYears;
	}

	public void setUniversityYears(List<UniversityYear> universityYears) {
		this.universityYears = universityYears;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public UniversityYearDAO getYearDAO() {
		return yearDAO;
	}

	public void setYearDAO(UniversityYearDAO yearDAO) {
		this.yearDAO = yearDAO;
	}

	public NavigationBean getNavig() {
		return navig;
	}

	public void setNavig(NavigationBean navig) {
		this.navig = navig;
	}
	
	
}
