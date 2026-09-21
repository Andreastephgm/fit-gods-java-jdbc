package com.sena.model;

public class Excercise {
    private Long idExercise;
    private String name;
    private  String description;
    private String muscularGroup;

    public Excercise() {
    }

    public Excercise(Long idExercise, String name, String description, String muscularGroup) {
        this.idExercise = idExercise;
        this.name = name;
        this.description = description;
        this.muscularGroup = muscularGroup;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
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

    public String getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(String muscularGroup) {
        this.muscularGroup = muscularGroup;
    }
}
