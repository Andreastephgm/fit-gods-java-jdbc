package com.sena.repository;

import com.sena.model.Excercise;

import java.util.List;

public interface ExcerciseRepository {

    Excercise save(Excercise excercise);
    List<Excercise> findAll();
    Excercise findById(Long id);
    Excercise update(Excercise excercise);
    void deleteById(Long id);
}
