package com.manjeet.learnspring.data.repository;

import com.manjeet.learnspring.data.entity.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void findAll_whenExist_thenReturnList() {
        List<Room> list = new ArrayList<>();
        Iterable<Room> iterable = roomRepository.findAll();
        iterable.forEach(list::add);

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(6));
        assertThat(list.get(0).getName(), equalTo("Piccadilly"));
    }

    @Test
    void save_whenExist_thenReturnList() {
        Room room = new Room();
        room.setName("Test Room");
        room.setNumber("Test Number");
        room.setBedInfo("Test Bed");
        roomRepository.save(room);

        List<Room> list = new ArrayList<>();
        Iterable<Room> iterable = roomRepository.findAll();
        iterable.forEach(list::add);

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(7));
        assertThat(list.get(6).getName(), equalTo("Test Room"));
    }
}
