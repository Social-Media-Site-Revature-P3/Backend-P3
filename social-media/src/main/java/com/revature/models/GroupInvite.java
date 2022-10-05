package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group_invites")
public class GroupInvite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_invite_id")
    private int groupInviteId;
    private boolean accepted;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "inviter_id")
    private User groupInviter;
    @ManyToOne
    @JoinColumn(name = "new_member")
    private User newGroupMember;

    public GroupInvite() {
    }

    public GroupInvite(int groupInviteId, boolean accepted, Group group, User groupInviter, User newGroupMember) {
        this.groupInviteId = groupInviteId;
        this.accepted = accepted;
        this.group = group;
        this.groupInviter = groupInviter;
        this.newGroupMember = newGroupMember;
    }

    public GroupInvite(boolean accepted, Group group, User groupInviter, User newGroupMember) {
        this.accepted = accepted;
        this.group = group;
        this.groupInviter = groupInviter;
        this.newGroupMember = newGroupMember;
    }

    public int getGroupInviteId() {
        return groupInviteId;
    }

    public void setGroupInviteId(int groupInviteId) {
        this.groupInviteId = groupInviteId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getGroupInviter() {
        return groupInviter;
    }

    public void setGroupInviter(User groupInviter) {
        this.groupInviter = groupInviter;
    }

    public User getNewGroupMember() {
        return newGroupMember;
    }

    public void setNewGroupMember(User newGroupMember) {
        this.newGroupMember = newGroupMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupInvite that = (GroupInvite) o;
        return groupInviteId == that.groupInviteId && accepted == that.accepted && Objects.equals(group, that.group) && Objects.equals(groupInviter, that.groupInviter) && Objects.equals(newGroupMember, that.newGroupMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupInviteId, accepted, group, groupInviter, newGroupMember);
    }

    @Override
    public String toString() {
        return "GroupInvite{" +
                "groupInviteId=" + groupInviteId +
                ", accepted=" + accepted +
                ", group=" + group +
                ", groupInviter=" + groupInviter +
                ", newGroupMember=" + newGroupMember +
                '}';
    }
}
