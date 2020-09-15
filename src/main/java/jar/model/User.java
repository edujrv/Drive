package jar.model;

//import com.sun.prism.Texture;

public class User {

    public String userName = "";
    public String name = "";
    public String lastName = "";
    public String mail = "";
    // public Texture profile;

    /*
     * Construct.
     */

    public User() {
    }

    /*
     * Getters.
     */

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
     * public Texture getProfile() { return profile; }
     */

    /*
     * Setters.
     */

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

    /*
     * public void setProfile(Texture profile) { this.profile = profile; }
     */

}
