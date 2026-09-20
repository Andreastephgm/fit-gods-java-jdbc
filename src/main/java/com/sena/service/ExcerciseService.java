package com.sena.service;

import com.sena.model.Excercise;
import com.sena.repository.ExcerciseRepository;

import java.lang.reflect.Executable;
import java.security.spec.ECField;
import java.util.List;

public class ExcerciseService {

    private final ExcerciseRepository excerciseRepository;

    public ExcerciseService(ExcerciseRepository excerciseRepository) {
        this.excerciseRepository = excerciseRepository;
    }

    public Excercise save(Excercise excercise){
        return excerciseRepository.save(excercise);
    }

    public List<Excercise> findAll(){
        return excerciseRepository.findAll();
    }

    public Excercise findById(Long id){
        return excerciseRepository.findById(id);
    }

    public Excercise update(Excercise excercise){
        return excerciseRepository.update(excercise);
    }

    public void deleteById(Long id){
        excerciseRepository.deleteById(id);
    }
}
