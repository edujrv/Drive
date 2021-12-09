package jar.model;

public class User implements Comparable<User> {

    private String name;
    private String mail;

    /*
     * Construct.
     */

    public User() {
    }

    public User(String name, String mail) {
        setName(name);
        setMail(mail);
    }

    /*
     * Getters.
     */

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    /*
     * Setters.
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * A user is equal to other user if their mails match
     */
    @Override
    public boolean equals(Object obj) {
        return getMail().equals(((User) obj).getMail());
    }

    /**
     * A user is bigger or lesser than other user if their name are respectively so
     */
    @Override
    public int compareTo(User arg0) {
        return getName().compareTo(arg0.getName());
    }
}
