package com.demo.dao;

import com.demo.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeDao extends JpaRepository<Resume, Long>, JpaSpecificationExecutor<Resume> {

    @Query("from Resume  where id=?1")
    List<Resume> findByJpql(Long id);

    @Query(value = "select * from  tb_resume where  name like ?1 and  address like ?2", nativeQuery = true)
    List<Resume> findBySql(String name, String address);

    List<Resume> findByNameLikeAndAddress(String name, String address);

}
