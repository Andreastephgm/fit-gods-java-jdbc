package com.sena.controller;

import com.sena.model.RoutineExcercise;
import com.sena.service.RoutineExerciseService;

import java.util.List;

public class RoutineExerciseController {

    private final RoutineExerciseService routineExerciseService;

    public RoutineExerciseController(RoutineExerciseService routineExerciseService) {
        this.routineExerciseService = routineExerciseService;
    }

    public RoutineExcercise saveRoutineExercise(RoutineExcercise routineExcercise){
        return routineExerciseService.save(routineExcercise);
    }

    public List<RoutineExcercise> findAllRoutineExercise(){
        return routineExerciseService.findAll();
    }

    public RoutineExcercise findByRoutineExercise(Long id){
        return routineExerciseService.findById(id);
    }

    public RoutineExcercise updateRoutineExercise(RoutineExcercise routineExcercise){
        return routineExerciseService.update(routineExcercise);
    }

    public void saveRoutineExercise(Long id){
        routineExerciseService.deleteById(id);
    }
}
