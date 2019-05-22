package com.geelaro.jpa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IpAddress {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int times;

    @Column(nullable = false)
    private String ip;

    public IpAddress() {
    }

    public IpAddress(int times, String ip) {
        this.times = times;
        this.ip = ip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public int getTimes() {
        return times;
    }

    public String getIp() {
        return ip;
    }
}
