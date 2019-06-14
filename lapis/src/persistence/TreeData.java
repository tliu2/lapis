package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TreeData implements Serializable, Comparable<TreeData> {

	private String name;

	private String score;

	private String description;

	private int id;

	private List<Integer> scoreList = new ArrayList<Integer>(21);

	public TreeData(String name, String score, String description, int id) {
		this.name = name;
		this.score = score;
		this.description = description;
		this.id = id;
		initScoreList();

	}

	private void initScoreList() {
		for (int i = 0; i <= 20; i++)
			scoreList.add(i);
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Integer> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Integer> scoreList) {
		this.scoreList = scoreList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getscore() {
		return score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setscore(String score) {
		this.score = score;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	// Eclipse Generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeData other = (TreeData) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public int compareTo(TreeData document) {
		return this.getName().compareTo(document.getName());
	}
}