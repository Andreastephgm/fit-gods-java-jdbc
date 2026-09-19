package com.sena.model;

import java.util.Date;

public class Progress {

    private Long id_progress;
    private Date date;
    private Double weight;
    private String notes;
    private User id_user;

    public Progress() {
    }

    public Progress(Long id_progress, Date date, Double weight, String notes, User id_user) {
        this.id_progress = id_progress;
        this.date = date;
        this.weight = weight;
        this.notes = notes;
        this.id_user = id_user;
    }

    public Long getId_progress() {
        return id_progress;
    }

    public void setId_progress(Long id_progress) {
        this.id_progress = id_progress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
}
