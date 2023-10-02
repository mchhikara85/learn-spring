package com.manjeet.learnspring.model;

import com.manjeet.learnspring.data.entity.Room;
import lombok.Data;

import java.util.List;

@Data
public class GetRoomsResponse {

    private List<Room> roomList;
}
