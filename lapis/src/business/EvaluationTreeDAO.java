package business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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

	public TreeNode createTeamTreeData(int id) {
		TeamDAO teamDAO = new TeamDAO();
		List<Team> teams = teamDAO.readTeamById(id);
		Team team = teams.get(0);

		TreeNode root = new DefaultTreeNode(new TreeData("", "", "", -1), null);

		List<StudentScore> studentScoresList = team.getStudentScores();
		List<EvaluationScore> evaluationScores = studentScoresList.get(0).getScores();
		
		for(int i=0; i<evaluationScores.size(); i++) {
			TreeNode evaluationRoot = new DefaultTreeNode(
					new TreeData(evaluationScores.get(i).getEvaluation().getCriterion().getName(),"","",evaluationScores.get(i).getId()), root);
	
			for (StudentScore studentScore : studentScoresList) {
				TreeNode studentLeaf = new DefaultTreeNode( new TreeData(studentScore.getStudent().getFirstname()+" "+studentScore.getStudent().getLastname(), Double.toString(studentScore.getScores().get(0).getScore()),"",studentScore.getStudent().getId()), evaluationRoot);

			}
		}

//    	TreeNode root = new DefaultTreeNode("Root", null);
//    	
//        List <Evaluation> evaluationList = team.getProject().getEvaluation();
//        for(Evaluation evaluation: evaluationList) {
//        	TreeNode evaluationRoot = new DefaultTreeNode(evaluation.getCriterion().getName(), root);
//        	for(Student student : team.getStudents()) {
//        		TreeNode studentLeaf = new DefaultTreeNode(student.getFirstname()+" "+student.getLastname(), evaluationRoot);
//        	}
//        }
//         
		return root;
	}

}