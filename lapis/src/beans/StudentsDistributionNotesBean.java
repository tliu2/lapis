package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import business.CourseDAO;
import business.PromotionDAO;
import business.UniversityYearDAO;
import persistence.Promotion;

@ManagedBean
public class StudentsDistributionNotesBean {
	
	private PieChartModel pieModel;


	public StudentsDistributionNotesBean() {
	}


	@PostConstruct
	public void init() {
		createPieModel();
	}

    private void createPieModel() {
        pieModel = new PieChartModel();
 
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
 
        pieModel.setTitle("Simple Pie");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }
	
    public PieChartModel getPieModel() {
        return pieModel;
    }

}
