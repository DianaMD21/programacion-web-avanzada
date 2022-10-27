package com.project.project.pwa.Controllers;

import com.project.project.Exception.ResourceNotFoundException;
import com.project.project.pwa.Entities.Student;
import com.project.project.pwa.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home(Model model) {
        return "Test/index";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model, HttpSession session) {
        session.setAttribute("allStudents", studentService.getAllStudents());
        model.addAttribute("allStudents", session.getAttribute("allStudents"));
        return "Test/showStudents";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        //model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("create",true);
        return "Test/showStudent";
    }
    @GetMapping("/update-student/{id}")
    public String modifyStudent(Model model, @PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentService.findStudentById( studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        //model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("create",false);
        model.addAttribute("studentUpdate",student);
        return "Test/showStudent";
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/create")
    public String createStudent(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastname", required = false) String lastName,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        Student student=new Student();
        student.setName(name);
        student.setLastName(lastName);
        student.setPhone(phone);
        studentService.createStudent(student);
        return "redirect:students";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "lastname", required = false) String lastName,
                                                 @RequestParam(value = "phone", required = false) String phone
    ) throws ResourceNotFoundException {
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        student.setName(name);
        student.setLastName(lastName);
        student.setPhone(phone);
        final Student updatedStudent = studentService.updateStudent(student);
        return "redirect:/api/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentService.findStudentById( studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentService.deleteStudentById(student);
        System.out.println("OYE ENTRO "+studentId.getClass()+" valor "+studentId+ "name "+student.getName());
        System.out.println("Cant students now: "+studentService.getAllStudents().size());
        return "redirect:/api/students";
    }
}
