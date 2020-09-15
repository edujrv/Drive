package jar.model;

import java.time.LocalDateTime;

public class Event<E> {
    public static enum TypeEvent {
        ADD, REMOVE, UPDATE
    }

    private TypeEvent type;
    private User user;
    private LocalDateTime eventTime;
    private E objEventOn;

    public Event(TypeEvent type, User user, LocalDateTime eventTime, E objEventOn) {
        setType(type);
        setUser(user);
        setEventTime(eventTime);
        setObjEventOn(objEventOn);
    }

    /**
     * @return TypeEvent return the type
     */
    public TypeEvent getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeEvent type) {
        this.type = type;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return LocalDateTime return the eventTime
     */
    public LocalDateTime getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * @return E return the objEventOn
     */
    public E getObjEventOn() {
        return objEventOn;
    }

    /**
     * @param objEventOn the objEventOn to set
     */
    public void setObjEventOn(E objEventOn) {
        this.objEventOn = objEventOn;
    }

}