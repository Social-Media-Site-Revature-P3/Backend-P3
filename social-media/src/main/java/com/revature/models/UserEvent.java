package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_events")
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_event_id")
    private int userEventId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private boolean creator;

    private boolean admin;

    public UserEvent() {
    }

    public UserEvent(int userEventId, User user, Event event, boolean creator, boolean admin) {
        this.userEventId = userEventId;
        this.user = user;
        this.event = event;
        this.creator = creator;
        this.admin = admin;
    }

    public UserEvent(User user, Event event, boolean creator, boolean admin) {
        this.user = user;
        this.event = event;
        this.creator = creator;
        this.admin = admin;
    }

    public int getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(int userEventId) {
        this.userEventId = userEventId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isCreator() {
        return creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEvent userEvent = (UserEvent) o;
        return userEventId == userEvent.userEventId && creator == userEvent.creator && admin == userEvent.admin && Objects.equals(user, userEvent.user) && Objects.equals(event, userEvent.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userEventId, user, event, creator, admin);
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "userEventId=" + userEventId +
                ", user=" + user +
                ", event=" + event +
                ", creator=" + creator +
                ", admin=" + admin +
                '}';
    }
}
