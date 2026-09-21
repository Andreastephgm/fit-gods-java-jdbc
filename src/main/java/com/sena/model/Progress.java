package com.sena.model;

import java.time.LocalDate;

public class Progress {

    private Long idProgress;
    private LocalDate date;
    private Double weight;
    private String notes;
    private Long idUser;

    public Progress() {
    }

    public Progress(Long idProgress, LocalDate date, Double weight, String notes, Long idUser) {
        this.idProgress = idProgress;
        this.date = date;
        this.weight = weight;
        this.notes = notes;
        this.idUser = idUser;
    }

    public Long getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(Long idProgress) {
        this.idProgress = idProgress;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
