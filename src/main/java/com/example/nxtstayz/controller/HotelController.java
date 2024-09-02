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
import com.example.nxtstayz.service.HotelJpaService;

@RestController
public class HotelController {
    @Autowired
    public HotelJpaService hotelService;

    @GetMapping("/hotels")
    public ArrayList<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") int hotelId) {
        return hotelService.getHotelById(hotelId);
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    public List<Room> getRooms(@PathVariable("hotelId") int hotelId) {
        return hotelService.getRooms(hotelId);
    }

    @PostMapping("/hotels")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @PutMapping("/hotels/{hotelId}")
    public Hotel updateHotel(@PathVariable("hotelId") int hotelId, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(hotelId, hotel);
    }

    @DeleteMapping("/hotels/{hotelId}")
    public void deleteHotel(@PathVariable("hotelId") int hotelId) {
        hotelService.deleteHotel(hotelId);
    }

}