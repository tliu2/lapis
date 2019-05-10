package persistence;

import java.io.Serializable;

public class TreeData implements Serializable, Comparable<TreeData> {
	 
    private String name;
     
    private String score;
     
    private String description;
     
    public TreeData(String name, String score, String description) {
        this.name = name;
        this.score = score;
        this.description = description;
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
 
    public void setscore(String score) {
        this.score = score;
    }
 
    public String getdescription() {
        return description;
    }
 
    public void setdescription(String description) {
        this.description = description;
    }
 
    //Eclipse Generated hashCode and equals
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