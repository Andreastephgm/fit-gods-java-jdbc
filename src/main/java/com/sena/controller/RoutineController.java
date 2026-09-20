package com.sena.controller;

import com.sena.model.Routine;
import com.sena.service.RoutineService;

import java.util.List;

public class RoutineController {

    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    public Routine saveRoutine(Routine routine){
        return  routineService.save(routine);
    }

    public List<Routine> FindAllRoutines(){
        return  routineService.findAll();
    }

    public Routine FinfByIdRoutine(Long id){
        return  routineService.findById(id);
    }

    public Routine UpdateRoutine(Routine routine){
        return  routineService.update(routine);
    }

    public void deleteRoutineById(Long id){
         routineService.deleteById(id);
    }
}
