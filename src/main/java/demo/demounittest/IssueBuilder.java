package demo.demounittest;

import lombok.Data;

/**
 * demo of using builder
 */
@Data
public class IssueBuilder {
	
	private Long id;
	private String creator;
	
	public IssueBuilder() {
		
	}
	
	
	public IssueBuilder creator(String s) {
		this.creator = s;
		
		return this;
	}
	
	public IssueBuilder withOnlyCreator(String s) {
		this.creator = s;
		return this;
	}
	
	
	public Issue build() {
		
		Issue issue = new Issue();
		issue.setCreator(creator);
		
		return issue;
	}
	
	
}
