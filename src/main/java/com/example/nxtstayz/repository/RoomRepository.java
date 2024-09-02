/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxtstayz.repository;

import com.example.nxtstayz.model.*;
import java.util.*;

public interface RoomRepository {
    ArrayList<Room> getAllRooms();

    Room getRoomById(int roomId);

    Hotel getHotel(int roomId);

    Room addRoom(Room room);

    Room updateRoom(int roomId, Room room);

    void deleteRoom(int roomId);
}
