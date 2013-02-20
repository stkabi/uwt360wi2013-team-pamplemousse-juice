package entities;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -721319874728872980L;
    protected String id;

    public BaseEntity() {
    }

    public String getID() {
        return id;
    }

    public void generateID() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    public void setID(String id) {
        this.id = id;
    }
}
