package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "index")
public class IndexBean {
  
    private String orientation = "horizontal";
 
    public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}