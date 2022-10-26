package com.project.project.pwa.Repositories;

import com.project.project.pwa.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Query("select u from Student u where u.id = ?1")
    public Student findStudentById(Long id);
}
