package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event_requests")
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_request_id")
    private int eventRequestId;
    private String text;
    private boolean accepted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public EventRequest() {
    }

    public EventRequest(int eventRequestId, String text, boolean accepted, User user, Event event) {
        this.eventRequestId = eventRequestId;
        this.text = text;
        this.accepted = accepted;
        this.user = user;
        this.event = event;
    }

    public EventRequest(String text, boolean accepted, User user, Event event) {
        this.text = text;
        this.accepted = accepted;
        this.user = user;
        this.event = event;
    }

    public int getEventRequestId() {
        return eventRequestId;
    }

    public void setEventRequestId(int eventRequestId) {
        this.eventRequestId = eventRequestId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRequest that = (EventRequest) o;
        return eventRequestId == that.eventRequestId && accepted == that.accepted && Objects.equals(text, that.text) && Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventRequestId, text, accepted, user, event);
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "eventRequestId=" + eventRequestId +
                ", text='" + text + '\'' +
                ", accepted=" + accepted +
                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
