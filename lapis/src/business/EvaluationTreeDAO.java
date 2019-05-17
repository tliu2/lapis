package business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import persistence.EvaluationScore;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;
import persistence.TreeData;

@ManagedBean(name = "evaluationTreeDAO")
@ApplicationScoped
public class EvaluationTreeDAO {

	public TreeNode createTeamTreeData(int id, Session session) {
		TeamDAO teamDAO = new TeamDAO();
		List<Team> teams = teamDAO.readTeamById(id, session);
		Team team = teams.get(0);

		TreeNode root = new DefaultTreeNode(new TreeData("", "", "", -1), null);

		List<StudentScore> studentScoresList = team.getStudentScores();
		List<EvaluationScore> evaluationScores = studentScoresList.get(0).getScores();

		for (int i = 0; i < evaluationScores.size(); i++) {
			TreeNode evaluationRoot = new DefaultTreeNode(
					new TreeData(evaluationScores.get(i).getEvaluation().getCriterion().getName(), "", "",
							evaluationScores.get(i).getId()),
					root);

			for (StudentScore studentScore : studentScoresList) {
				TreeNode studentLeaf = new DefaultTreeNode(new TreeData(
						studentScore.getStudent().getFirstname() + " " + studentScore.getStudent().getLastname(),
						Double.toString(studentScore.getScores().get(i).getScore()), "",
						studentScore.getStudent().getId()), evaluationRoot);

			}
		}

		return root;
	}

}