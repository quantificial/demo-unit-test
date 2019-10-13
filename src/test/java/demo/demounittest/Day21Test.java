package demo.demounittest;

import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import demo.demounittest.Issue.ActiveState;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;


/**
 * Spring MVC Test framework
 * 
 * test rest api
 * 
 * @author Johnson Fu
 *
 */
@RunWith(HierarchicalContextRunner.class)
public class Day21Test {
	
    private MockMvc mockMvc;
    
    private IssueService issueService;

    // - layer one
    
    @Before
    public void setup() {
        IssueController issueController = new IssueController();
        
        issueService = mock(IssueService.class);
        
        // inject issueService into issueController
        ReflectionTestUtils.setField(issueController, "issueService", issueService);

//        mockMvc = MockMvcBuilders.standaloneSetup(issueController)
//                .build();
        
        mockMvc = MockMvcBuilders.standaloneSetup(issueController)
                .setMessageConverters(TestContextConfig.objectMapperHttpMessageConverter())
                .build();
    }
    
    // - layer two
    
    public class QueryAll {

        public class WhenTwoIssueAreFound {

        	// create two issues object instance 
            @Before
            public void returnTwoIssue() {
                Issue issue1 = new Issue();
                issue1.setId(1L);
                issue1.setTitle("first");
                issue1.setActiveState(ActiveState.TODO);

                Issue issue2 = new Issue();
                issue2.setId(2L);
                issue2.setTitle("second");
                issue2.setActiveState(ActiveState.TODO);

                // mock the return objects
                BDDMockito.given(issueService.queryAll())
                        .willReturn(Arrays.asList(issue1, issue2));
            }

            @Test
            public void thenReturnHttpStatusCodeOk() throws Exception {

                mockMvc.perform(get("/api/issue"))
                        .andExpect(status().isOk());
            }

            @Test
            public void thenReturnTwoIssue() throws Exception {
            	
            	//ResultActions result = mockMvc.perform(get("/api/issue"));
            	
            	//result.andReturn().getResponse()
            	
            	System.out.println("in thenReturnTwoIssue...");

                mockMvc.perform(get("/api/issue"))
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andDo(print());
            }


            @Test
            public void thenReturnCorrectInfo() throws Exception {
                ResultActions result = mockMvc.perform(get("/api/issue"));

                String contentAsString = result.andReturn().getResponse().getContentAsString();
                
                System.out.println(contentAsString);
                
                result.andExpect(jsonPath("$[0].id", is(1)))
                        .andExpect(jsonPath("$[0].title", is("first")))
                        .andExpect(jsonPath("$[0].activeState", is(ActiveState.TODO.toString())))
                        .andExpect(jsonPath("$[1].id", is(2)))
                        .andExpect(jsonPath("$[1].title", is("second")))
                        .andExpect(jsonPath("$[1].activeState", is(ActiveState.TODO.toString())));            
            }
        }
    }
    
    // another test
    
    @Before
    public void setup2() {
        Issue issue = new Issue();
        issue.setId(3L);
        issue.setCreator("100L");
        issue.setTitle("third");
        issue.setActiveState(ActiveState.TODO);

        BDDMockito.given(issueService.queryById(3L)).willReturn(issue);    	
    }
    
    @Test
    public void thenReturnHttpStatusCodeOk() throws Exception {

        mockMvc.perform(get("/api/issue/{id}", 3L))
                .andExpect(status().isOk());
    }

    @Test
    public void thenReturnCorrectInfo() throws Exception {
        mockMvc.perform(get("/api/issue/{id}", 3L))
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("title", is("third")))
                //.andExpect(jsonPath("desc", is("desc")))
                .andExpect(jsonPath("activeState", is(ActiveState.TODO.toString())))
                .andExpect(jsonPath("creator", is("100L")));
    }
	
    

}
