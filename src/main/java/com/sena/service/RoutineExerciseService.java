package com.sena.service;

import com.sena.model.RoutineExcercise;
import com.sena.repository.RoutineExerciseRepository;

import java.util.List;

public class RoutineExerciseService {

    private final RoutineExerciseRepository routineExerciseRepository;

    public RoutineExerciseService(RoutineExerciseRepository routineExerciseRepository) {
        this.routineExerciseRepository = routineExerciseRepository;
    }

    public RoutineExcercise save(RoutineExcercise routineExcercise){
        return routineExerciseRepository.save(routineExcercise);
    }

    public List<RoutineExcercise> findAll(){
        return routineExerciseRepository.findAll();
    }

    public RoutineExcercise findById(Long id){
        return routineExerciseRepository.findById(id);
    }

    public RoutineExcercise update(RoutineExcercise routineExcercise){
        return routineExerciseRepository.update(routineExcercise);
    }

    public void deleteById(Long id){
        routineExerciseRepository.deleteById(id);
    }
}
