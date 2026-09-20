package com.sena.controller;

import com.sena.model.Excercise;
import com.sena.service.ExcerciseService;

import java.util.List;

public class ExcerciseController {

    private final ExcerciseService excerciseService;

    public ExcerciseController(ExcerciseService excerciseService) {
        this.excerciseService = excerciseService;
    }

    public Excercise saveExcercise(Excercise excercise){
        return excerciseService.save(excercise);
    }

    public List<Excercise> findAllExcercises(){
        return excerciseService.findAll();
    }

    public Excercise findByIdExcercise(Long id ){
        return excerciseService.findById(id);
    }

    public Excercise updateExcercise(Excercise excercise){
        return excerciseService.update(excercise);
    }

    public void deleteByIdExcercise(Long id){
        excerciseService.deleteById(id);
    }


}
