package com.sena.repository;

import com.sena.model.RoutineExcercise;

import java.util.List;

public interface RoutineExerciseRepository {

    RoutineExcercise save(RoutineExcercise routineExcercise);
    List<RoutineExcercise> findAll();
    RoutineExcercise findById(Long id);
    RoutineExcercise update(RoutineExcercise routineExcercise);
    void deleteById(Long id);
}
