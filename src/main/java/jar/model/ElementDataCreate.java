package jar.model;

import java.util.Date;

public class ElementDataCreate extends Element {

    public Date startDate;
    public User creatorUser;

    /*
     * Construct.
     */

    public ElementDataCreate() {
    }

    /*
     * Getters.
     */

    public Date getStartDate() {
        return startDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    /*
     * Setters.
     */

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }
}
