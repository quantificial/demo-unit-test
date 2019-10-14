package demo.demounittest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Issue> queryAll() {
        return issueService.queryAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Issue queryById(@PathVariable Long id) {
        return issueService.queryById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Issue create(@Valid @RequestBody Issue issue) {
        return issueService.create(issue);
    }
}