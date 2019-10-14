package demo.demounittest;

import java.util.HashMap;
import java.util.Map;

final public class IssueRepositoryFake implements IssueNewRepository {

    private long nextId;

    private Map<Long, Issue> issueMap;

    public IssueRepositoryFake() {
        this.nextId = 1L;
        this.issueMap = new HashMap<>();
    }

    @Override
    public Issue findById(Long id) {
        return issueMap.get(id);
    }

    @Override
    public Issue save(Issue issue) {
        long id = nextId;
        issue.setId(id);

        issueMap.put(id, issue);
        nextId++;

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
	public Issue getBigIssue() {
		// TODO Auto-generated method stub
		return null;
	}
}