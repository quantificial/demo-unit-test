package demo.demounittest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class IssueService {
	
    public Issue queryById(Long id) {
        throw new EntityNotFoundException(id);
    }
    
    public List<Issue> queryAll() {
    	return null;
    }

	public Issue create(@Valid Issue issue) {
		return issue;
	}
	

}
