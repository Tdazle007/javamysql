package com.javamysql.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public String getStudents(Model model){
    model.addAttribute("students", studentService.getStudents());
    return "index";
    }

    @GetMapping(path = "add")
    public String getStudent(Model model){
        Student addstudent = new Student();
        model.addAttribute("addstudent", addstudent);
        return "savestudent";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping(path = "{studentId}")
    public String showUpdateStudent(@PathVariable("studentId") Long studentId, Model model){
        Optional<Student> getstudent =  studentService.getStudent(studentId);
        model.addAttribute("addstudent", getstudent);
        return "savestudent";
    }

    @PostMapping(path = "{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String emailAddress, @RequestParam(required = false) String guardianMobile){
        studentService.updateStudent(studentId, firstName, lastName, emailAddress, guardianMobile);
        return "redirect:/students";
    }

    @GetMapping(path = "delete/{deleteStudentId}")
    public String deleteStudent(@PathVariable("deleteStudentId") Long deleteStudentId){
        studentService.deleteStudent(deleteStudentId);
        return "redirect:/students";
    }


}
