package com.sena.model;

public class Excercise {
    private Long idExcercise;
    private String name;
    private  String description;
    private String muscular_group;

    public Excercise() {
    }

    public Excercise(Long idExcercise, String name, String description, String muscular_group) {
        this.idExcercise = idExcercise;
        this.name = name;
        this.description = description;
        this.muscular_group = muscular_group;
    }

    public Long getId_excercise() {
        return idExcercise;
    }

    public void setId_excercise(Long idExcercise) {
        this.idExcercise = idExcercise;
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

    public String getMuscular_group() {
        return muscular_group;
    }

    public void setMuscular_group(String muscular_group) {
        this.muscular_group = muscular_group;
    }
}
