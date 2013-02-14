package entities;

import java.io.Serializable;


public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -721319874728872980L;
    public String id;
    
    public BaseEntity() {
        this.id = java.util.UUID.randomUUID().toString();
    }
    
    public String getID() {
        return id;
    }
    
    /**
     * This should never be used. Only for testing purposes.
     * @param id
     */
    public void setID(String id) {
        this.id = id;
    }
}
