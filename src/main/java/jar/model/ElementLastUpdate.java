package jar.model;

import java.time.LocalDateTime;

public class ElementLastUpdate {

    private LocalDateTime updateDate;
    private User user;

    /*
     * Construct.
     */

    public ElementLastUpdate() {
    }

    public ElementLastUpdate(LocalDateTime updateDate, User user) {
        setUpdateDate(updateDate);
        setUser(user);
    }

    /*
     * Getters.
     */

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public User getUser() {
        return user;
    }

    /*
     * Setters.
     */

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
