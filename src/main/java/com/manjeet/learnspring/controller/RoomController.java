package com.manjeet.learnspring.controller;

import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import com.manjeet.learnspring.model.GetRoomsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<GetRoomsResponse> getRooms() {
        Iterable<Room> iterable = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        iterable.forEach(roomList::add);
        GetRoomsResponse getRoomsResponse = new GetRoomsResponse();
        getRoomsResponse.setRoomList(roomList);
        return ResponseEntity.ok(getRoomsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable("id") long roomId) {
        return ResponseEntity.ok(this.roomRepository.findById(roomId).get());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        roomRepository.save(room);
        return null;
    }
}
