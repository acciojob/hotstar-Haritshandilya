package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class User {

//    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

//    @Column(name="name")
    private String name;

//    @Column(name="age")
    private int age;

//    @Column(name="mobNo")
    private String mobNo;

//    @Column(name="subscription")
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Subscription subscription;

    public User(int id, String name, int age, String mobNo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobNo = mobNo;
    }

    public User(String name, int age, String mobNo) {
        this.name = name;
        this.age = age;
        this.mobNo = mobNo;
    }

    public User(int id, String name, int age, String mobNo, Subscription subscription) {
        this(id, name,age, mobNo);
        this.subscription = subscription;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
