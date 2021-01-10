package jar.model;

import java.time.LocalDateTime;

public class ElementLastOpened {

    private LocalDateTime openDate;
    private boolean isMe;

    /*
     * Construct.
     */

    public ElementLastOpened() {
    }

    public ElementLastOpened(LocalDateTime openDate, boolean isMe) {
        setOpenDate(openDate);
        setIsMe(isMe);
    }

    /*
     * Getters.
     */

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public boolean getIsMe() {
        return isMe;
    }

    /*
     * Setters.
     */

    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }

    public void setIsMe(boolean isMe) {
        this.isMe = isMe;
    }
}
