package business;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.Course;
import persistence.ProjectInfo;
import persistence.Promotion;
import persistence.Student;
import persistence.StudentScore;
import persistence.Team;

public class StudentScoreDAO {
	
	public List<StudentScore> getStudentScoreListOfStudent(Student student){
		Session session = DBConnection.getSession();
		Transaction readTransaction = session.beginTransaction();

		Query readQuery = session.createQuery("from StudentScore s where s.student.id = :id");
		readQuery.setInteger("id", student.getId());
		List result = readQuery.list();
		readTransaction.commit();

		return result;
	}

}
