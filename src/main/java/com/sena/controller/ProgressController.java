package com.sena.controller;

import com.sena.model.Progress;
import com.sena.service.ProgressService;

import java.util.List;

public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    public Progress saveProgress(Progress progress){
        return progressService.save(progress);
    }

    public List<Progress> findAllPrgress(){
        return progressService.findAll();
    }

    public Progress findByIdProgress(Long id){
        return progressService.findById(id);
    }

    public Progress updateProgress(Progress progress){
        return progressService.update(progress);
    }

    public void DeleteProgress(Long id){
        progressService.deleteById(id);
    }
}
