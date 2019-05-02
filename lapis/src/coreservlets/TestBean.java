package coreservlets;

import java.util.Date;
import javax.faces.bean.*;

@ManagedBean
public class TestBean {
  private int number;
  private Date date;
  
  public int getNumber() {
    return(number);
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public Date getDate() {
    return(date);
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String doAction() {
    return("show-test-data");
  }
}
