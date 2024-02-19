package org.zeyad.sms.repos;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zeyad.sms.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCodeContainingAndTitleContaining(String code, String title, Pageable pageable);
    @Modifying
    @Transactional
    @Query("DELETE FROM Course c JOIN c.students s WHERE c.id = :courseId AND s.id = :studentId")
    void deleteStudentFromCourse(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}