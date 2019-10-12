package demo.demounittest;

import lombok.Data;

@Data
public class Issue {
    private Long id;
    private ActiveState activeState;
    private ResultState resultState;
    private String creator;
    private String solver;
    private String log;
    
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
    	UNSOLVED
    }
}
