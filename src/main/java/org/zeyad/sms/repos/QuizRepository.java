package org.zeyad.sms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}