package com.demo.service.impl;

import com.demo.dao.ResumeDao;
import com.demo.pojo.Resume;
import com.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Override
    public Resume save(Resume resume) {
        Resume save = resumeDao.save(resume);
        return save;
    }

    @Override
    public Resume update(Resume resume) {
        if (resume.getId() < 1) {
            return null;
        }
        return resumeDao.save(resume);
    }

    @Override
    public Integer delete(Long id) {
        resumeDao.deleteById(id);
        return 1;
    }

    @Override
    public List<Resume> findAll() {
        return resumeDao.findAll();
    }

    @Override
    public Resume findById(Long id) {
        Optional<Resume> optional = resumeDao.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
