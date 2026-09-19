package com.sena.model;

public class Routine {

    private Long idRoutine;
    private String name;
    private String objective;
    private Long idUser;

    public Routine() {
    }

    public Routine(Long idRoutine, String name, String objective, Long idUser) {
        this.idRoutine = idRoutine;
        this.name = name;
        this.objective = objective;
        this.idUser = idUser;
    }

    public Long getIdRoutine() {
        return idRoutine;
    }

    public void setIdRoutine(Long idRoutine) {
        this.idRoutine = idRoutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
