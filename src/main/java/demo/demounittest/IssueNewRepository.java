package demo.demounittest;

public interface IssueNewRepository {

    long count();

    void delete(Issue issue);

    Issue findById(Long id);

    Issue save(Issue issue);
    
    Issue getBigIssue();
}
