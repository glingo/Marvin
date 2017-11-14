package com.marvin.bundle.security.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    
    private String username;
    private String password;
    private boolean active;
    
    private List<Role> roles;
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public static UserBuilder builder() {
        return new UserBuilder();
    }
    
    public static class UserBuilder {
        private String username;
        private String password;
        private boolean active;

        private List<Role> roles;
        private List<Group> groups;
        
        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }
        
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public UserBuilder active(boolean active) {
            this.active = active;
            return this;
        } 
        
        public UserBuilder roles(String... roles) {
            this.roles = Arrays.stream(roles)
                    .map(Role::new)
                    .collect(Collectors.toList());
            return this;
        }
        
        public UserBuilder groups(String... roles) {
            this.groups = Arrays.stream(roles)
                    .map(Group::new)
                    .collect(Collectors.toList());
            return this;
        }
        
        public User build() {
            User user = new User();
            
            user.setActive(active);
            user.setGroups(groups);
            user.setRoles(roles);
            user.setUsername(username);
            user.setPassword(password);
            
            return user;
        }
    }
}
