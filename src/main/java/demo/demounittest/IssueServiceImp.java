package demo.demounittest;

public class IssueServiceImp {
	
    private IssueRepository repository;

    public Issue find(Issue issue) {
        return repository.find(issue);
    }

    public void setRepository(IssueRepository repository) {
        this.repository = repository;
    }
}
