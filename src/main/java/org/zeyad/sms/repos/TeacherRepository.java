package org.zeyad.sms.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByNameContainingAndEmailContaining(String name, String email, Pageable pageable);
    Optional<Teacher> findByEmail(String email);

}