package demo.demounittest;

public class IssueNewRepositoryImpl implements IssueNewRepository {

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
		return new Issue();
	}

	@Override
	public Issue save(Issue issue) {
		return issue;
	}
	

}
