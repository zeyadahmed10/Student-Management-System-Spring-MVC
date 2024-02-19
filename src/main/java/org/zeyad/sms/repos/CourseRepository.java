package org.zeyad.sms.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCodeContainingAndTitleContaining(String code, String title, Pageable pageable);
}