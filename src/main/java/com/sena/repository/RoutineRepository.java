package com.sena.repository;

import com.sena.model.Routine;

import java.util.List;

public interface RoutineRepository {

    Routine save(Routine routine);
    List<Routine> findAll();
    Routine findById(Long id);
    Routine update(Routine routine);
    void deleteById(Long id);
}
