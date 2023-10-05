package com.manjeet.learnspring;

import com.manjeet.learnspring.data.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeatureTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGetRoom() {
        ResponseEntity<Room> responseEntity = testRestTemplate.getForEntity("/rooms/1", Room.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getRoomId());
    }

    @Test
    void testCreateRoom() {
        Room room = new Room();
        room.setNumber("401");
        room.setName("New Room");
        room.setBedInfo("New Bed");
        ResponseEntity<Void> savedRoomResponse = testRestTemplate.postForEntity("/rooms", room, Void.class);
        assertEquals(HttpStatus.CREATED, savedRoomResponse.getStatusCode());

        ResponseEntity<Room> responseEntity = testRestTemplate.getForEntity(savedRoomResponse.getHeaders().getLocation(), Room.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotEquals(0, responseEntity.getBody().getRoomId());
    }
}
