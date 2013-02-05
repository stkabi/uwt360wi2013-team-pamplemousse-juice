package entities;

public class User extends BaseEntity {
    private int userID;
    private String role; // possibly use an enum instead?
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password; // strictly speaking, we shouldn't do this

    /**
     * Constructs a user with default values (should be changed soon after
     * construction).
     */
    public User() {
        this(0, "", "", "", "", "", "");
    }

    /**
     * 
     * @param userId
     *            The user ID. (Where should we designate IDs? In some db
     *            interaction class?)
     * @param role
     *            User's role in the contest (organizer, judge, participant)
     * @param name
     *            The User's name
     * @param address
     *            User-provided address
     * @param phoneNumber
     *            User's phone number
     * @param email
     *            User's email
     * @param password
     *            User's password
     */
    public User(final int userId, final String role, final String name, final String address, final String phoneNumber, final String email, final String password) {
        super();
        this.userID = userId;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(final int userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    
    public User deserialize(String data) {
        return new User();
    }

    public String serialize() {
        return null;
    }
}
