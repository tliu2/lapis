package business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.DBConnection;
import persistence.DataInit;
import persistence.UniversityYear;

public class Initiation {
	
	public static final int START_YEAR = 2000;
	public static final int END_YEAR = 2100;
	
	private static List<UniversityYear> years = new ArrayList<UniversityYear>();
	
	public static void initUniversityList() {
		
		for(int i = START_YEAR; i < END_YEAR; i++) {
			UniversityYear newYear = new UniversityYear(i,i+1);
			years.add(newYear);
		}
	}
	
	public static void main(String[] args) {
		initUniversityList();
		System.out.println();
		
		DataInit.createTables();

		Session session = DBConnection.getSession();
		Transaction persistTransaction1 = session.beginTransaction();
		
		for(UniversityYear universityYear : years ) {
			session.persist(universityYear);
		}
		
		
		persistTransaction1.commit();

		session.close();
	}
}
