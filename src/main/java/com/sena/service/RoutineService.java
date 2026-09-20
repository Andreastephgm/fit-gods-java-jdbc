package com.sena.service;

import com.sena.model.Routine;
import com.sena.repository.RoutineRepository;

import java.util.List;

public class RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineService(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    public Routine save(Routine routine){
        return routineRepository.save(routine);
    }

    public List<Routine> findAll(){
        return routineRepository.findAll();
    }

    public Routine  findById(Long id){
        return routineRepository.findById(id);
    }

    public Routine update(Routine routine){
        return routineRepository.update(routine);
    }

    public void deleteById(Long id){
        routineRepository.deleteById(id);
    }
 }
