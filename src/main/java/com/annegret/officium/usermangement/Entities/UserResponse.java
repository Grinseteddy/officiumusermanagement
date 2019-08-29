package com.annegret.officium.usermangement.Entities;

import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private UserEntity user;
    private Message message;
    private List<Link> usefulLinks;

    public UserResponse() {
        user=null;
        message=null;
        usefulLinks=null;
    }

    public UserResponse(UserEntity user, Message message, List<Link> usefulLinks) {
        this.user=user;
        this.message=message;
        this.usefulLinks=usefulLinks;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Link> getUsefulLinks() {
        return usefulLinks;
    }

    public void setUsefulLinks(ArrayList<Link> usefulLinks) {
        this.usefulLinks = usefulLinks;
    }
}
