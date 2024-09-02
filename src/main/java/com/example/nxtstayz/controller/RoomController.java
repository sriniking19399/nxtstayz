/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxtstayz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.nxtstayz.model.*;
import com.example.nxtstayz.service.RoomJpaService;

@RestController
public class RoomController {
    @Autowired
    public RoomJpaService roomService;

    @GetMapping("/hotels/rooms")
    public ArrayList<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/hotels/rooms/{roomId}")
    public Room getRoomById(@PathVariable("roomId") int roomId) {
        return roomService.getRoomById(roomId);
    }

    @GetMapping("/rooms/{roomId}/hotel")
    public Hotel getHotel(@PathVariable("roomId") int roomId) {
        return roomService.getHotel(roomId);
    }

    @PostMapping("/hotels/rooms")
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping("/hotels/rooms/{roomId}")
    public Room updateRoom(@PathVariable("roomId") int roomId, @RequestBody Room room) {
        return roomService.updateRoom(roomId, room);
    }

    @DeleteMapping("/hotels/rooms/{roomId}")
    public void deleteRoom(@PathVariable("roomId") int roomId) {
        roomService.deleteRoom(roomId);
    }

}