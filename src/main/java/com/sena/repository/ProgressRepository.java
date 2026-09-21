package com.sena.repository;

import com.sena.model.Progress;

import java.util.List;

public interface ProgressRepository {

    Progress save(Progress progress);
    List<Progress> findAll();
    Progress findById(Long id);
    Progress update(Progress progress);
    void deleteById(Long id);
}
