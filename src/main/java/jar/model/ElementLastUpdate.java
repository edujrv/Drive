package jar.model;

import java.util.Date;

public class ElementLastUpdate {

    private Date updateDate;
    private User user;

    /*
     * Construct.
     */

    public ElementLastUpdate() {
    }

    /*
     * Getters.
     */

    public Date getUpdateDate() {
        return updateDate;
    }

    public User getUser() {
        return user;
    }

    /*
     * Setters.
     */

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
