/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxtstayz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.nxtstayz.model.*;
import com.example.nxtstayz.repository.*;

@Service
public class RoomJpaService implements RoomRepository {
    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Override
    public ArrayList<Room> getAllRooms() {
        List<Room> roomList = roomJpaRepository.findAll();
        ArrayList<Room> rooms = new ArrayList<>(roomList);
        return rooms;
    }

    @Override
    public Room getRoomById(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            return room;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel getHotel(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            return room.getHotel();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room addRoom(Room room) {
        Hotel hotel = room.getHotel();
        int hotelId = hotel.getHotelId();
        try {
            Hotel compHotel = hotelJpaRepository.findById(hotelId).get();
            room.setHotel(compHotel);
            Room saveRoom = roomJpaRepository.save(room);
            return saveRoom;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room updateRoom(int roomId, Room room) {
        try {
            Room newRoom = roomJpaRepository.findById(roomId).get();
            if (room.getRoomNumber() != null) {
                newRoom.setRoomNumber(room.getRoomNumber());
            }
            if (room.getRoomType() != null) {
                newRoom.setRoomType(room.getRoomType());
            }
            if (room.getPrice() != 0) {
                newRoom.setPrice(room.getPrice());
            }
            if (room.getHotel() != null) {
                Hotel hotel = room.getHotel();
                int hotelId = hotel.getHotelId();
                Hotel compHotel = hotelJpaRepository.findById(hotelId).get();
                newRoom.setHotel(compHotel);
            }
            roomJpaRepository.save(newRoom);
            return newRoom;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteRoom(int roomId) {
        try {
            roomJpaRepository.deleteById(roomId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
