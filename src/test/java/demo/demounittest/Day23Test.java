package demo.demounittest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasSize;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import demo.demounittest.Issue.ActiveState;


/**
 * Spring MVC Test Framework
 * 
 * Test Rest API and Handle Exception
 * 
 * @author johns
 *
 */

@RunWith(HierarchicalContextRunner.class)
//@AutoConfigureMockMvc
public class Day23Test {

    private IssueService issueService;
    
    //@Autowired
    private MockMvc mockMvc;
    
    // - layer one - setup
    
    @Before
    public void setup() {
        issueService = mock(IssueService.class);
        IssueController issueController = new IssueController();
        ReflectionTestUtils.setField(issueController, "issueService", issueService);

        mockMvc = MockMvcBuilders.standaloneSetup(issueController)
                .setControllerAdvice(new ErrorHandleAdvice()) // add error handling advice
                .setMessageConverters(TestContextConfig.objectMapperHttpMessageConverter())
                .build();
    }
    
    // - layer two - to test create issue
    
    public class Create {
        Issue input;

        public class WhenFieldsAreEmpty {

            @Before
            public void createEmptyInput() {
                input = new Issue();
                input.setTitle("");
            }

            // test HTTP status code = 400 if input object is not valid object
            @Test
            public void thenReturnHttpStatusCodeBadRequest() throws Exception {
            	
            	
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input))
                ).andExpect(status().isBadRequest());
            }

            // return error contain fieldErrors
            @Test
            public void thenReturnOneValidationError() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input))
                )
                        .andExpect(jsonPath("$.fieldErrors", hasSize(1)));
            }

            // verify the error is as expected
            @Test
            public void thenReturnValidationErrorAboutEmptyTitle() throws Exception {
                ResultActions perform = mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input))
                );
                perform
                        .andExpect(
                                jsonPath("$.fieldErrors[0].field", is("title"))
                        )
                        .andExpect(
                                jsonPath("$.fieldErrors[0].errorCode", is("NotBlank"))
                        );
            }


            @Test
            public void thenNotCreateIssue() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input))
                );

                verify(issueService, never()).create(input);
                //.create(isA(Issue.class));
            }
        }
        
        public class WhenAllFieldsAreValid {
            @Before
            public void setup() {
                createValidInput();
                returnCreated();
            }

            private void createValidInput() {
                input = new Issue();
                input.setTitle("inputTitle");
            }

            private void returnCreated() {
                Issue created = new Issue();
                created.setId(1L);
                created.setActiveState(ActiveState.TODO);
                created.setCreator("100L");
                created.setTitle("inputTitle");
                BDDMockito.given(issueService.create(input)).willReturn(created);
            }

            @Test
            public void thenReturnHttpStatusCodeCreated() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input)))
                        .andExpect(status().isCreated());
            }

            @Test
            public void thenReturnCorrectInfo() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input)))
                        .andExpect(jsonPath("$.creator", is("100L")))
                        .andExpect(jsonPath("$.id", is(1)))
                        .andExpect(jsonPath("$.title", is("inputTitle")))
                        .andExpect(jsonPath("$.activeState", is(ActiveState.TODO.toString())));
            }

            /**
            * 3. 驗證它建立一個空id的issue。
            **/
            @Test
            public void thenCreateIssueWithoutId() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input)));

                verify(issueService, times(1))
                        .create(
                                assertArg(issue -> assertThat(issue.getId()).isNull())
                        );
            }

            // verify it is correct
            @Test
            public void thenCreateIssueWithCorrectTitle() throws Exception {
                mockMvc.perform(post("/api/issue")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(input)));

                verify(issueService, times(1))
                        .create(
                                assertArg(issue -> assertThat(issue.getTitle()).isEqualTo("inputTitle"))
                        );
            }

       
        }
    }
    
    
    
	
}
