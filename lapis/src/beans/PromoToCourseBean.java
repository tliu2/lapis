package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navig", eager = true)
@ApplicationScoped
public class PromoToCourseBean {

	private String year;

	public PromoToCourseBean() {
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
