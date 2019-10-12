package demo.demounittest;

public class EntityNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
    
    EntityNotFoundException (Long id) {
        super(String.format("cannot find the instance: %d", id));
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
}
