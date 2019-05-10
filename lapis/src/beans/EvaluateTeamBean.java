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
import org.primefaces.model.TreeNode;

import business.EvaluationTreeDAO;
import business.TeamDAO;
import persistence.Team;

@ManagedBean(name="ttBasicView")
public class EvaluateTeamBean implements Serializable{
     
    private TreeNode root;
     
    @ManagedProperty("#{documentService}")
    private EvaluationTreeDAO service;
    
    @ManagedProperty("#{navigTeamtoEvaluation}")
	private TeamToEvaluationBean navig;
    
    private TeamDAO teamDAO = new TeamDAO();
     
    @PostConstruct
    public void init() {
    	List<Team> teams = teamDAO.readTeamById(Integer.valueOf(navig.getTeamID()));
    	Team team = teams.get(0);
    	System.out.println("team id : "+team.getId());
        root = service.createTeamTreeData(team.getId()); 
    }
 
    public TreeNode getRoot() {
        return root;
    }
     
    public void setService(EvaluationTreeDAO service) {
        this.service = service;
    }
     
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Document Edited", ((TreeNode) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((TreeNode) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public EvaluateTeamBean() {
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

	public EvaluationTreeDAO getService() {
		return service;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	} 
    
    
}