package jar.model;

import java.util.Date;

public class ElementLastOpened {

    private Date openDate;
    private User user;

    /*
     * Construct.
     */

    public ElementLastOpened() {
    }

    /*
     * Getters.
     */

    public Date getOpenDate() {
        return openDate;
    }

    public User getUser() {
        return user;
    }

    /*
     * Setters.
     */

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
