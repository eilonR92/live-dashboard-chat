

package com.example.LiveUsersDashboard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity(name = "users")
public class User implements Serializable {

    private final int ONE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String Name;
    private String email;
    private String userName;
    private String password;
    @Column(updatable = false)
    private Instant regTime;
    private Instant lastLogIn;
    private Integer logsCount;
    private Boolean online;
    private String ip;

    public User() {
        this.regTime = Instant.now();
        this.lastLogIn = Instant.now();
        this.logsCount = ONE;
        this.online = true;
        this.ip = getIp();
    }


    public User(Long id, String name, String email,
                String userName, String password,
                Instant regTime, Instant lastLogIn,
                Integer logsCount, boolean online, String ip) {
        this.id = id;
        this.Name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.regTime = regTime;
        this.lastLogIn = lastLogIn;
        this.logsCount = logsCount;
        this.online = online;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getRegTime() {
        return regTime;
    }

    public void setRegTime(Instant regTime) {
        this.regTime = regTime;
    }

    public Instant getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(Instant lastLogIn) {
        this.lastLogIn = lastLogIn;
    }

    public Integer getLogsCount() {
        return logsCount;
    }

    public void setLogsCount(Integer logsCount) {
        this.logsCount = logsCount;
    }

    public boolean isOnline() {
        return online;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", regTime=" + regTime.toString() +
                ", lastLogIn=" + lastLogIn +
                ", logsCount=" + logsCount +
                ", online=" + online +
                ", ip='" + ip + '\'' +
                '}';
    }

}




