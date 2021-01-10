package jar.model;

import java.time.LocalDateTime;

public class ElementDataCreate {

    private LocalDateTime startDate;
    private User creatorUser;

    /*
     * Construct.
     */

    public ElementDataCreate() {
    }

    public ElementDataCreate(LocalDateTime startDate, User creatorUser) {
        setStartDate(startDate);
        setCreatorUser(creatorUser);
    }

    /*
     * Getters.
     */

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    /*
     * Setters.
     */

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }
}
