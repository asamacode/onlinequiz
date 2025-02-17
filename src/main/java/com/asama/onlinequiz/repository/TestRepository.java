package com.asama.onlinequiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Long>{

    @Query("FROM Test t join t.subject s join s.appClasses c WHERE c.id  = :classId AND t.id NOT IN"
            + " (SELECT rs.test.id FROM ResultTest rs WHERE rs.student.id = :studentId)")
    List<Test> findListTestStudentId(@Param("classId") Long cid, @Param("studentId") String sid);
    
    @Query("FROM Test t join t.subject s join s.appClasses c WHERE c.id  = :classId AND t.id IN"
            + " (SELECT rs.test.id FROM ResultTest rs WHERE rs.student.id = :studentId)")
    List<Test> findListTestedStudentId(@Param("classId") Long cid, @Param("studentId") String sid);

}
