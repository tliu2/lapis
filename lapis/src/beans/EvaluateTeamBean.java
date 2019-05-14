package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;

import business.EvaluationScoreDAO;
import business.EvaluationTreeDAO;
import business.TeamDAO;
import persistence.Team;
import persistence.TreeData;

@ManagedBean(name = "ttEditView")
@ApplicationScoped
public class EvaluateTeamBean implements Serializable {

	private TreeNode root;
	private TreeNode root2;
	private Team team;

	@ManagedProperty("#{evaluationTreeDAO}")
	private EvaluationTreeDAO service;

	@ManagedProperty("#{navigTeamtoEvaluation}")
	private TeamToEvaluationBean navig;

	private TeamDAO teamDAO = new TeamDAO();
	private EvaluationScoreDAO evaluationScoreDAO = new EvaluationScoreDAO();

	@PostConstruct
	public void init() {
		List<Team> teams = teamDAO.readTeamById(Integer.valueOf(navig.getTeamID()));
		team = teams.get(0);
		System.out.println("team id : " + team.getId());
		root = service.createTeamTreeData(team.getId());
		root2 = service.createTeamTreeData(team.getId());
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
		TreeNode node = ((TreeNode) event.getObject());

		if (evaluationScoreDAO.updateEvaluationScoreWithId(((TreeData) node.getData()).getId(),
				Integer.parseInt(((TreeData) node.getData()).getScore()),
				((TreeData) node.getData()).getDescription())) {
			FacesMessage msg1 = new FacesMessage("Update successfull");
			FacesContext.getCurrentInstance().addMessage(null, msg1);
		} else {
			FacesMessage msg2 = new FacesMessage("Update failed");
			FacesContext.getCurrentInstance().addMessage(null, msg2);
		}

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((TreeNode) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
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

	public TreeNode getRoot2() {
		return root2;
	}

	public void setRoot2(TreeNode root2) {
		this.root2 = root2;
	}

	public TeamToEvaluationBean getNavig() {
		return navig;
	}

	public void setNavig(TeamToEvaluationBean navig) {
		this.navig = navig;
	}

}