package demo.demounittest;

public class IssueRepositoryStub implements IssueRepository {

	private final Issue issue;
	
    IssueRepositoryStub(Issue issue) {
        this.issue = issue;
    }
	
	@Override
	public Issue find(Issue issue) {
		
		issue.setCreator("JF");
		
		return issue;
	}

}
