package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event_invites")
public class EventInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_invite_id")
    private int eventInviteId;
    private boolean accepted;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "inviter_id")
    private User eventInviter;
    @ManyToOne
    @JoinColumn(name = "new_member")
    private User newEventMember;

    public EventInvite() {
    }

    public EventInvite(int eventInviteId, boolean accepted, Event event, User eventInviter, User newEventMember) {
        this.eventInviteId = eventInviteId;
        this.accepted = accepted;
        this.event = event;
        this.eventInviter = eventInviter;
        this.newEventMember = newEventMember;
    }

    public EventInvite(boolean accepted, Event event, User eventInviter, User newEventMember) {
        this.accepted = accepted;
        this.event = event;
        this.eventInviter = eventInviter;
        this.newEventMember = newEventMember;
    }

    public int getEventInviteId() {
        return eventInviteId;
    }

    public void setEventInviteId(int eventInviteId) {
        this.eventInviteId = eventInviteId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getEventInviter() {
        return eventInviter;
    }

    public void setEventInviter(User eventInviter) {
        this.eventInviter = eventInviter;
    }

    public User getNewEventMember() {
        return newEventMember;
    }

    public void setNewEventMember(User newEventMember) {
        this.newEventMember = newEventMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInvite that = (EventInvite) o;
        return eventInviteId == that.eventInviteId && accepted == that.accepted && Objects.equals(event, that.event) && Objects.equals(eventInviter, that.eventInviter) && Objects.equals(newEventMember, that.newEventMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventInviteId, accepted, event, eventInviter, newEventMember);
    }

    @Override
    public String toString() {
        return "EventInvite{" +
                "eventInviteId=" + eventInviteId +
                ", accepted=" + accepted +
                ", event=" + event +
                ", eventInviter=" + eventInviter +
                ", newEventMember=" + newEventMember +
                '}';
    }
}
