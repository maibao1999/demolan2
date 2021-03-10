package com.iuh.contactapp.User.entity;

import com.iuh.contactapp.Contact.entity.Contact;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String name;
    private String numberphone;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Contact> contactList = new ArrayList<>();

    public User() {
    }

    public User(long id, String name, String numberphone, String password, List<Contact> contactList) {
        this.id = id;
        this.name = name;
        this.numberphone = numberphone;
        this.password = password;
        this.contactList = contactList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberphone='" + numberphone + '\'' +
                ", password='" + password + '\'' +
                ", contactList=" + contactList +
                '}';
    }
}
