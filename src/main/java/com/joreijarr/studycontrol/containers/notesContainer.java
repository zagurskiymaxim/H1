package com.joreijarr.studycontrol.containers;

public class notesContainer {
    public String client, tour, note_date;


    public notesContainer() {
    }

    public notesContainer(String client, String tour, String note_date) {
        this.client = client;
        this.tour = tour;
        this.note_date = note_date;
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
