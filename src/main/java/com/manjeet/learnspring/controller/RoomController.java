package com.manjeet.learnspring.controller;

import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import com.manjeet.learnspring.model.GetRoomsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<GetRoomsResponse> getRoomsByPage(Pageable pageable) {
        GetRoomsResponse getRoomsResponse = new GetRoomsResponse();
        Page<Room> page = roomRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.DESC, "roomId"))));
        getRoomsResponse.setRoomList(page.getContent());
        return ResponseEntity.ok(getRoomsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable("id") long roomId) {
        return ResponseEntity.ok(this.roomRepository.findById(roomId).get());
    }

    @PostMapping
    public ResponseEntity<Void> createRoom(@RequestBody Room room, UriComponentsBuilder ucb) {
        Room savedRoom = roomRepository.save(room);
        URI uri = ucb
                .path("/rooms/{id}")
                .buildAndExpand(savedRoom.getRoomId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") long roomId, @RequestBody Room room) {
        if (roomRepository.findById(roomId).isPresent()) {
            room.setRoomId(roomId);
            return ResponseEntity.ok(roomRepository.save(room));
        }
        return ResponseEntity.notFound().build();
    }
}
