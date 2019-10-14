package demo.demounittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImp {
	
    private IssueRepository repository;
    
    @Autowired
    private IssueNewRepository repo;

    public Issue find(Issue issue) {
        return repository.find(issue);
    }

    public void setRepository(IssueRepository repository) {
        this.repository = repository;
    }
    
    public Issue getBigIssue() {
    	return repo.getBigIssue();
    }
    
    
}
