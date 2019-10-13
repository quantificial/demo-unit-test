package demo.demounittest;

import java.util.List;

public class IssueService {
	
    public Issue queryById(Long id) {
        throw new EntityNotFoundException(id);
    }
    
    public List<Issue> queryAll() {
    	return null;
    }
}
