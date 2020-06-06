package com.asama.onlinequiz.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    List<Question> findAllByLecturerId(String id);
    
    @Query("FROM Question q WHERE q.subject.id = :subid AND q.level = :lv")
    List<Question> findAllBySubjectId(@Param("subid") Long subId,@Param("lv") Integer lv, Pageable pageable);
    
    List<Question> findTop10ByContentLikeAndSubjectId(String key, Long subId);
}
