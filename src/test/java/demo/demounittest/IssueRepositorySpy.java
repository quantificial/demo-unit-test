package demo.demounittest;

import java.util.ArrayList;
import java.util.List;

/**
 * spy object is to record the statistics of the instance call...
 * 
 * @author Johnson Fu
 *
 */
public class IssueRepositorySpy implements IssueNewRepository {
	
    private int saveExecuteCount;

    public int getSaveExecuteCount() {
        return saveExecuteCount;
    }

    private List<Issue> issueList;

    public List<Issue> getSavedIssueList() {
        return issueList;
    }

    public IssueRepositorySpy() {
        saveExecuteCount = 0;
        issueList = new ArrayList<>();
    }

    @Override
    public Issue save(Issue issue) {
        saveExecuteCount++;
        issueList.add(issue);
        return issue;
    }

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Issue issue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Issue findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
