package demo.demounittest;

public class FactoryIssue {

	public static Issue createIssueWithCreatorId(String id) {
		Issue issue = new Issue();
		issue.setCreator(id);
		
		return issue;
	}
}
