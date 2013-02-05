package entities;

public abstract class BaseEntity {
    
    public abstract String serialize();
    public abstract BaseEntity deserialize(String data);

}
