package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import business.AddStudent;
import business.PromotionCreation;
import persistence.Promotion;
import persistence.UniversityYear;

@ManagedBean
@ViewScoped
public class AddStudentBean implements Serializable {
     
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String univYear;  
    private String promo;
    private List<String> universityYears;
    private Map<String,String> promotions;
    private AddStudent addStudent = new AddStudent();
    private PromotionCreation promoCreate = new PromotionCreation();
    
    
    public AddStudentBean() {
    	
    }
    
    @PostConstruct
    public void init() {
    	
    	List<UniversityYear> years = promoCreate.readUniversityYears();
    	universityYears  = new ArrayList<String>();
    	for (UniversityYear universityYear : years) {
    		universityYears.add(universityYear.toString());
    	}
    	
    	List<Promotion> promotions = new ArrayList<Promotion>();
    	Map<String,String> map = new HashMap<String, String>();
    	
    	/*for(UniversityYear universityYears: years) {
    		promotions = addStudent.readPromotion(universityYears);
    		System.out.println(promotions.get(0).toString());
    		for(Promotion promos: promotions) {
    			map = new HashMap<String, String>();
                map.put(promos.toString(), promos.toString());
    		}
            data.put(universityYears.toString(), map);
    	}*/
    	map = new HashMap<String, String>();
        map.put("L1-2001-2002", "L1-2001-2002");
        map.put("L2-2001-2002", "L2-2001-2002");
        data.put(universityYears.get(0), map);
    }

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public String getUnivYear() {
		return univYear;
	}

	public void setUnivYear(String univYear) {
		this.univYear = univYear;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public List<String> getUniversityYears() {
		return universityYears;
	}

	public void setUniversityYears(List<String> universityYears) {
		this.universityYears = universityYears;
	}

	public Map<String, String> getPromotions() {
		return promotions;
	}

	public void setPromotions(Map<String, String> promotions) {
		this.promotions = promotions;
	}

	public AddStudent getAddStudent() {
		return addStudent;
	}

	public void setAddStudent(AddStudent addStudent) {
		this.addStudent = addStudent;
	}

	public PromotionCreation getPromoCreate() {
		return promoCreate;
	}

	public void setPromoCreate(PromotionCreation promoCreate) {
		this.promoCreate = promoCreate;
	}

	public void onChange() {
        if(univYear !=null && !univYear.equals(""))
            promotions = data.get(univYear);
        else
            promotions = new HashMap<String, String>();
    }
     
    public void displayLocation() {
        FacesMessage msg;
        if(promo != null && univYear != null)
            msg = new FacesMessage("Selected", promo + " of " + univYear);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "promo is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
}
