package business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


import persistence.Evaluation;
import persistence.EvaluationScore;
import persistence.Student;
import persistence.Team;
import persistence.TreeData;

@ManagedBean(name = "EvaluationTreeDAO")
@ApplicationScoped
public class EvaluationTreeDAO {
     
    public TreeNode createTeamTreeData(int id) {
    	TeamDAO teamDAO = new TeamDAO();
    	List<Team> teams = teamDAO.readTeamById(id);
    	Team team = teams.get(0);
    	
    	TreeNode root = new DefaultTreeNode(new TreeData("","",""), null);
    	 
    	
    	List <EvaluationScore> evaluationScoreList = team.getScores();
      for(EvaluationScore evaluationScore: evaluationScoreList) {
      	TreeNode evaluationRoot = new DefaultTreeNode(new TreeData(evaluationScore.getEvaluation().getCriterion().getName(),Integer.toString(evaluationScore.getScore()),evaluationScore.getDescription()), root);
//      	for(Student student : team.getStudents()) {
//      		TreeNode studentLeaf = new DefaultTreeNode(student.getFirstname()+" "+student.getLastname(), evaluationRoot);
//      	}
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