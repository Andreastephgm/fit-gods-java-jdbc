package com.sena.model;

public class RoutineExcercise {

    private Long idRoutine;
    private Long idExercise;
    private Integer series;
    private Integer repetitions;
    private Integer rest;

    public RoutineExcercise() {
    }

    public RoutineExcercise(Long idRoutine, Long idExercise, Integer series, Integer repetitions, Integer rest) {
        this.idRoutine = idRoutine;
        this.idExercise = idExercise;
        this.series = series;
        this.repetitions = repetitions;
        this.rest = rest;
    }

    public Long getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(Long idRoutine) {
        this.idRoutine = idRoutine;
    }

    public Long getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Long idExercise) {
        this.idExercise = idExercise;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }
}
