package com.project.project.pwa.Services;

import com.project.project.pwa.Entities.Student;
import com.project.project.pwa.Repositories.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Transactional()
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudents(){
        List<Student> students=studentRepository.findAll();
        return students;
    }

    public Optional<Student> findStudentById(Long id){
        Optional<Student> student =studentRepository.findById(id);
        return student;
    }

    public void deleteStudentById(Student student){
        studentRepository.delete(student);
    }
    public Student updateStudent(Student student){
        studentRepository.save(student);
        return student;
    }
}
