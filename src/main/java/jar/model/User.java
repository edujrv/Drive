package jar.model;

//import com.sun.prism.Texture;

public class User implements Comparable<User> {

    private int id;
    private String userName;
    private String name;
    private String lastName;
    private String mail;

    /*
     * Construct.
     */

    public User() {
    }

    public User(int id, String userName, String name, String lastName, String mail) {
        setId(id);
        setUserName(userName);
        setName(name);
        setLastName(lastName);
        setMail(mail);
    }

    /*
     * Getters.
     */

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    /*
     * Setters.
     */

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * A user is equals to other user if their IDs match
     */
    @Override
    public boolean equals(Object obj) {
        return getId() == ((User) obj).getId();
    }

    /**
     * A user is bigger or lesser than other user if their "userName" are
     * respectively so
     */
    @Override
    public int compareTo(User arg0) {
        return this.userName.compareTo(arg0.getUserName());
    }

}
