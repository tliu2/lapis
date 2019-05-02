package persistence;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class ToolContent {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER, targetEntity = Tool.class)
	private Tool tool;
	private String name;
	
	public ToolContent(Tool tool, String name) {
		this.tool = tool;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ToolContent() {
	}
	
}
