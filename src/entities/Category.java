package entities;

public class Category extends BaseEntity {
    private static final long serialVersionUID = -5468875646426363744L;
    private String name;

    public Category(final String id, final String name) {
        this.id = id;
        this.name = name;
    }
    
    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}