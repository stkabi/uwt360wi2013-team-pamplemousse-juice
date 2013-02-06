package entities;

public class Category extends BaseEntity {
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
    
    public Category deserialize(String data) {
        return null;
    }

    public String serialize() {
        return null;
    }
}