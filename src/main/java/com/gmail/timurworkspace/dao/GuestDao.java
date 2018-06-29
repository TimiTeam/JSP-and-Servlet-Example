package com.gmail.timurworkspace.dao;


import com.gmail.timurworkspace.components.Guest;

import java.util.List;

public interface GuestDao {

    List<Guest> findByAll();

    Guest findById(int id);

    void saveGuest(Guest guest);

    void deleteGuest(int id);

}
