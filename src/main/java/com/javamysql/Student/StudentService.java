package com.javamysql.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService{

    private final StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long studentId){
       return studentRepository.findById(studentId);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long deleteStudentId) {
        boolean exists = studentRepository.existsById(deleteStudentId);
        if(!exists){
            throw new RuntimeException("student with id " + deleteStudentId +" does not exist");
        }
        studentRepository.deleteById(deleteStudentId);
    }

    @Transactional
    public Student updateStudent(Long studentId, String firstName, String lastName, String emailAddress, String guardianMobile) {
        Optional <Student> optional = studentRepository.findById(studentId);
        Student student = null;
        if(optional.isPresent()){
            student = optional.get();
        }else{
            throw new RuntimeException("Student with "+studentId+" does not exist");
        }
        return student;
    }

}
