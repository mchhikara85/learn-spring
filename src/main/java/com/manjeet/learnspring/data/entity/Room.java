package com.manjeet.learnspring.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROOM_NUMBER")
    private String number;

    @Column(name = "BED_INFO")
    private String bedInfo;

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }
}
