package com.joreijarr.studycontrol.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long tour_id;
    public String name, description, price, country, hotel, transport, date_start;

    public Tours() {
    }

    public Tours(String name, String description, String price, String country, String hotel, String transport, String date_start) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.country = country;
        this.hotel = hotel;
        this.transport = transport;
        this.date_start = date_start;
    }

    public Long getTour_id() {
        return tour_id;
    }

    public void setTour_id(Long tour_id) {
        this.tour_id = tour_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }
}
