package com.annegret.officium.usermangement.Entities;

import javassist.bytecode.ByteArray;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name ="surname")
    private String surname;

    @Column(name ="username")
    private String username;

    @Column(name = "emailaddress")
    private String emailAddress;

    @Column(name ="password")
    private String password;

    @Column(name = "defaultpicture")
    private String defaultPicture;

    public UserEntity() {
        id = UUID.randomUUID().toString();
        name=id;
        username=id;
        emailAddress="notknown@void.com";
    }

    public UserEntity(String name, String surname, String username, String emailAddress, String password,
               String defaultPicture) {
        id = UUID.randomUUID().toString();
        this.name=name;
        this.surname=surname;
        this.username=username;
        this.emailAddress=emailAddress;
        this.password=password;
        this.defaultPicture=defaultPicture;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(String defaultPicture) {
        this.defaultPicture = defaultPicture;
    }
}
