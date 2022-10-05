package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;
    private String date;
    private String time;
    private String name;
    private String info;
    @Column(name = "invite_only")
    private boolean inviteOnly;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEvent> events;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EventRequest> eventRequests;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Event() {
    }

    public Event(int eventId, String date, String time, String name, String info, boolean inviteOnly, List<UserEvent> events, List<Post> posts, List<EventRequest> eventRequests, Group group) {
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.name = name;
        this.info = info;
        this.inviteOnly = inviteOnly;
        this.events = events;
        this.posts = posts;
        this.eventRequests = eventRequests;
        this.group = group;
    }

    public Event(String date, String time, String name, String info, boolean inviteOnly, List<UserEvent> events, List<Post> posts, List<EventRequest> eventRequests, Group group) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.info = info;
        this.inviteOnly = inviteOnly;
        this.events = events;
        this.posts = posts;
        this.eventRequests = eventRequests;
        this.group = group;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
    }

    public List<UserEvent> getEvents() {
        return events;
    }

    public void setEvents(List<UserEvent> events) {
        this.events = events;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<EventRequest> getEventRequests() {
        return eventRequests;
    }

    public void setEventRequests(List<EventRequest> eventRequests) {
        this.eventRequests = eventRequests;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && inviteOnly == event.inviteOnly && Objects.equals(date, event.date) && Objects.equals(time, event.time) && Objects.equals(name, event.name) && Objects.equals(info, event.info) && Objects.equals(events, event.events) && Objects.equals(posts, event.posts) && Objects.equals(eventRequests, event.eventRequests) && Objects.equals(group, event.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, date, time, name, info, inviteOnly, events, posts, eventRequests, group);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", inviteOnly=" + inviteOnly +
                ", events=" + events +
                ", posts=" + posts +
                ", eventRequests=" + eventRequests +
                ", group=" + group +
                '}';
    }
}
