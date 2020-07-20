package jar.model;

public class Account {
    private int id;
    private String username;
    private String firstName;
    private String lastName;

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    // Setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Constructores
    public Account(int id, String username, String firstName, String lastName) {
        this.id = id;
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
    }

}