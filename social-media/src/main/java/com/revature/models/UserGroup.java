package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private int userGroupId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private boolean creator;

    private boolean admin;

    public UserGroup() {
    }

    public UserGroup(int userGroupId, User user, Group group, boolean creator, boolean admin) {
        this.userGroupId = userGroupId;
        this.user = user;
        this.group = group;
        this.creator = creator;
        this.admin = admin;
    }

    public UserGroup(User user, Group group, boolean creator, boolean admin) {
        this.user = user;
        this.group = group;
        this.creator = creator;
        this.admin = admin;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        UserGroup userGroup = (UserGroup) o;
        return userGroupId == userGroup.userGroupId && creator == userGroup.creator && admin == userGroup.admin && Objects.equals(user, userGroup.user) && Objects.equals(group, userGroup.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, user, group, creator, admin);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "userGroupId=" + userGroupId +
                ", user=" + user +
                ", group=" + group +
                ", creator=" + creator +
                ", admin=" + admin +
                '}';
    }
}
