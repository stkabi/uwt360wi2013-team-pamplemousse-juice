package entities;

import java.security.MessageDigest;
/**
 * 
 * @author Justin Prentice
 *
 */
public class User extends BaseEntity {
    private static final long serialVersionUID = -7917347868104375158L;

    public static enum Role {
        CONTESTANT, JUDGE, ORGANIZER
    }

    private Role role = Role.CONTESTANT;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String passwordHash;

    /**
     * 
     * @param id
     *            The user ID.
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
     * @param passwordHash
     *            User's passwordHash
     */
    public User(final Role role, final String name, final String address, final String phoneNumber, final String email, final String passwordHash) {
        this.role = role;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public User() {
        super();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
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
        return passwordHash;
    }

    public void setPassword(final String passwordPlainText) {
        this.passwordHash = User.hashPassword(passwordPlainText);
    }

    public boolean authenticate(final String passwordPlainText) {
        // convert string to md5, validate against stored md5.
        return User.hashPassword(passwordPlainText).compareTo(this.passwordHash) == 0;
    }

    /**
     * Get the hash for a plaintext password
     * 
     * @param passwordPlainText
     *            Password in plain text
     * @return hashed password
     */
    public static String hashPassword(String passwordPlainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordPlainText.getBytes());
            byte[] digest = md.digest();
            String pwHash = "";
            for (byte b : digest) {
                pwHash += (Integer.toHexString((int) b & 0xff));
            }
            return pwHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
