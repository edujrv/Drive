package jar.model;

//import com.sun.prism.Texture;

public class User implements Comparable<User> {

    private int id;
    private String userName;
    private String name;
    private String lastName;
    private String mail;
    private String password;

    /*
     * Construct.
     */

    public User() {
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO: Implementar estos metodos

    @Override
    public boolean equals(Object obj) {
        return getId() == ((User) obj).getId();
    }

    // ? Bajo que condicion un "User" es menor o mayor a otro??
    @Override
    public int compareTo(User arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
