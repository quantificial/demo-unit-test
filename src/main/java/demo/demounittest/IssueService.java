package demo.demounittest;

public class IssueService {
	
    public Issue queryById(Long id) {
        throw new EntityNotFoundException(id);
    }
}
