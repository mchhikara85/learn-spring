package com.manjeet.learnspring.controller;

import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<List<Room>> getRooms() {
        Iterable<Room> iterable = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        iterable.forEach(roomList::add);
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable("id") long roomId) {
        return ResponseEntity.ok(this.roomRepository.findById(roomId).get());
    }
}
