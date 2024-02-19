package org.zeyad.sms.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> fidByEmail(String email);
    List<Student> findByNameContainingAndEmailContaining(String name, String email, Pageable pageable);
}