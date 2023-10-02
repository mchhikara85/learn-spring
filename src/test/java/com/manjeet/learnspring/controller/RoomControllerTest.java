package com.manjeet.learnspring.controller;

import com.manjeet.learnspring.data.entity.Room;
import com.manjeet.learnspring.data.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        room1.setRoomid(1);
        room1.setName("Best Room 1");
        room1.setNumber("Best Number 1");
        room1.setBedInfo("Best Bed 1");
        roomlist.add(room1);
        Room room2 = new Room();
        room2.setRoomid(2);
        room2.setName("Best Room 2");
        room2.setNumber("Best Number 2");
        room2.setBedInfo("Best Bed 2");
        roomlist.add(room2);

        doReturn(roomlist).when(roomRepository).findAll();

        String response = mockmvc.perform(get("/rooms"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Best Room")))
                .andReturn().getResponse().getContentAsString();
    }
}
