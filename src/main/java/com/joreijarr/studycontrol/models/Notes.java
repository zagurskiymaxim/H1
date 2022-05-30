package com.joreijarr.studycontrol.models;


import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long note_id;

    public String client, tour, note_date;


    public Notes() {
    }

    public Notes(String client, String tour, String note_date) {
        this.client = client;
        this.tour = tour;
        this.note_date = note_date;
    }

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

}
