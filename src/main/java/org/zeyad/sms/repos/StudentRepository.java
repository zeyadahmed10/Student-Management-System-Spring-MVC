package org.zeyad.sms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zeyad.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}