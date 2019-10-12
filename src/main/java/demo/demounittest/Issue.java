package demo.demounittest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private Long id;
    private ActiveState activeState;
    private ResultState resultState;
    private String creator;
    private String solver;
    private String log;
    private String title;
    
    public enum ActiveState {
    	TODO("todo"),
    	PROGRESS("progress"),
    	DONE("done");
    	
    	private String value;
    	
    	ActiveState(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    	
    }
    
    public enum ResultState {
    	RESOLVED,
    	UNSOLVED;
    	
    	ResultState() {}
    }
}
