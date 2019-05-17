package beans;

import javax.faces.bean.ViewScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigTeamtoEvaluation", eager = true)
@ApplicationScoped
public class TeamToEvaluationBean {

	private String teamID;

	public TeamToEvaluationBean() {
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

}
