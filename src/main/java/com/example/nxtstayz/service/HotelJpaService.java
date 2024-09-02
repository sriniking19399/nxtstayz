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
public class HotelJpaService implements HotelRepository {
    @Autowired
    private HotelJpaRepository hotelJpaRepository;
    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Override
    public ArrayList<Hotel> getAllHotels() {
        List<Hotel> hotelList = hotelJpaRepository.findAll();
        ArrayList<Hotel> hotels = new ArrayList<>(hotelList);
        return hotels;
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return hotel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Room> getRooms(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> roomList = roomJpaRepository.findAll();
            List<Room> result = new ArrayList<>();
            for (Room room : roomList) {
                if (room.getHotel() == hotel) {
                    result.add(room);
                }
            }
            return roomList;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        Hotel saveHotel = hotelJpaRepository.save(hotel);
        return saveHotel;
    }

    @Override
    public Hotel updateHotel(int hotelId, Hotel hotel) {
        try {
            Hotel newHotel = hotelJpaRepository.findById(hotelId).get();
            if (hotel.getHotelName() != null) {
                newHotel.setHotelName(hotel.getHotelName());
            }
            if (hotel.getLocation() != null) {
                newHotel.setLocation(hotel.getLocation());
            }
            if (hotel.getRating() != 0) {
                newHotel.setRating(hotel.getRating());
            }
            hotelJpaRepository.save(newHotel);
            return newHotel;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteHotel(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> roomList = roomJpaRepository.findAll();
            for (Room room : roomList) {
                if (room.getHotel() == hotel) {
                    room.setHotel(null);
                }
            }
            roomJpaRepository.saveAll(roomList);
            hotelJpaRepository.deleteById(hotelId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}