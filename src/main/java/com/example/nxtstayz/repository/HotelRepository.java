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

public interface HotelRepository {
    ArrayList<Hotel> getAllHotels();

    Hotel getHotelById(int hotelId);

    List<Room> getRooms(int hotelId);

    Hotel addHotel(Hotel hotel);

    Hotel updateHotel(int hotelId, Hotel hotel);

    void deleteHotel(int hotelId);
}