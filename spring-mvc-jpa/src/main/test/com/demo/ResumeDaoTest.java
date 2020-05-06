package com.demo;

import com.demo.dao.ResumeDao;
import com.demo.pojo.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ResumeDaoTest {

    @Autowired
    private ResumeDao resumeDao;

    @Test
    public void testFindById() {
        Optional<Resume> byId = resumeDao.findById(1l);
        if (byId.isPresent()) {
            System.out.println(byId.get());
        }
    }

    @Test
    public void testFindOne() {
        Resume resume = new Resume();
        resume.setId(2l);
        Example<Resume> example = Example.of(resume);
        Optional<Resume> one = resumeDao.findOne(example);
        if (one.isPresent()) {
            System.out.println(one.get());
        }
    }

    @Test
    public void testSave() {
        Resume resume = new Resume();
        resume.setId(5l);
        resume.setName("赵六");
        resume.setAddress("上海");
        resume.setPhone("13434343333");
        Resume save = resumeDao.save(resume);
        System.out.println(save);
    }

    @Test
    public void testDelete() {
        resumeDao.deleteById(4l);
    }

    @Test
    public void testFindAll() {
        List<Resume> all = resumeDao.findAll();
        all.stream().forEach(System.out::println);
    }

    @Test
    public void testSort() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        resumeDao.findAll(sort).stream().forEach(System.out::println);
    }


    @Test
    public void testPage() {
        PageRequest of = PageRequest.of(0, 2);
        Page<Resume> all = resumeDao.findAll(of);
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        all.getContent().stream().forEach(System.out::println);
    }


}
