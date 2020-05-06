package com.demo.service;

import com.demo.pojo.Resume;

import java.util.List;

public interface ResumeService {

    Resume save(Resume resume);

    Resume update(Resume resume);

    Integer delete(Long id);

    List<Resume> findAll();

    Resume findById(Long id);
}
