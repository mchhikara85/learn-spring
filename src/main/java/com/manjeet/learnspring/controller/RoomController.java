package com.manjeet.learnspring.controller;

import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    @ResponseBody
    public Iterable<Room> getRooms() {
        return this.roomRepository.findAll();
    }
}
