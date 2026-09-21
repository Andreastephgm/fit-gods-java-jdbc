package com.sena.service;

import com.sena.model.Progress;
import com.sena.repository.ProgressRepository;

import java.util.List;

public class ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public Progress save(Progress progress){
        return progressRepository.save(progress);
    }

    public List<Progress> findAll(){
        return progressRepository.findAll();
    }

    public Progress findById(Long id){
        return progressRepository.findById(id);
    }

    public Progress update(Progress progress){
        return progressRepository.update(progress);
    }

    public void deleteById(Long id){
        progressRepository.deleteById(id);
    }
}
