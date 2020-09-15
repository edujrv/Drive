package jar.model;

import java.util.Date;

public class ElementDataCreate {

    private Date startDate;
    private User creatorUser;

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
