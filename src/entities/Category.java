package entities;

public class Category
{
  private int categoryID;
  private String name;
  
  public Category() {
    this(0,"");
  }

  public Category(final int categoryID, final String name)
  {
    super();
    this.categoryID = categoryID;
    this.name = name;
  }
  
  public int getCategoryID()
  {
    return categoryID;
  }

  public void setCategoryID(final int categoryID)
  {
    this.categoryID = categoryID;
  }

  public String getName()
  {
    return name;
  }

  public void setName(final String name)
  {
    this.name = name;
  }
}