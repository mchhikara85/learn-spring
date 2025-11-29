package com.manjeet.learnspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    void testGetRooms() throws Exception {
        List<Room> roomlist = new ArrayList<>();
        Room room1 = new Room();
        room1.setRoomId(1);
        room1.setName("Best Room 1");
        room1.setNumber("Best Number 1");
        room1.setBedInfo("Best Bed 1");
        roomlist.add(room1);
        Room room2 = new Room();
        room2.setRoomId(2);
        room2.setName("Best Room 2");
        room2.setNumber("Best Number 2");
        room2.setBedInfo("Best Bed 2");
        roomlist.add(room2);
        Page<Room> page = new PageImpl<>(roomlist);
        doReturn(page).when(roomRepository).findAll(any(Pageable.class));

        String response = mockmvc.perform(get("/rooms"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Best Room")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void testCreateRoom() throws Exception {
        Room room = new Room();
        room.setName("Best Room 1");
        room.setNumber("Best Number 1");
        room.setBedInfo("Best Bed 1");

        Room savedRoom = new Room();
        savedRoom.setRoomId(501);
        savedRoom.setName("Best Room 2");
        savedRoom.setNumber("Best Number 2");
        savedRoom.setBedInfo("Best Bed 2");

        ObjectMapper objectMapper = new ObjectMapper();

        doReturn(savedRoom).when(roomRepository).save(any(Room.class));

        String response = mockmvc.perform(post("/rooms")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/rooms/501")))
                .andReturn().getResponse().getHeader("Location");
    }

    @Test
    void testUpdateRoom() throws Exception {
        Room room = new Room();
        room.setRoomId(1);
        room.setName("Updated Room");
        room.setNumber("Updated Number");
        room.setBedInfo("Updated Bed");

        doReturn(true).when(roomRepository).existsById(1L);
        doReturn(room).when(roomRepository).save(any(Room.class));

        ObjectMapper objectMapper = new ObjectMapper();

        mockmvc.perform(put("/rooms/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateRoomNotFound() throws Exception {
        Room room = new Room();
        room.setRoomId(1);
        room.setName("Updated Room");
        room.setNumber("Updated Number");
        room.setBedInfo("Updated Bed");

        doReturn(false).when(roomRepository).existsById(1L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockmvc.perform(put("/rooms/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isNotFound());
    }
}
