package entities;


public class BaseEntity {
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
