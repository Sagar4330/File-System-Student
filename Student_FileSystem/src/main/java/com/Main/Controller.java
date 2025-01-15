package com.Main;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class Controller {
	
	
    @Autowired
    private ServiceFile fileStorageService;

    @GetMapping
    public List<StudentFile> getAllStudents() throws IOException {
        return fileStorageService.getAllStudents();
    }

    @PostMapping
    public StudentFile createStudent(@RequestBody StudentFile student) throws IOException {
        List<StudentFile> students = fileStorageService.getAllStudents();
        student.setId(UUID.randomUUID().toString());
        students.add(student);
        fileStorageService.saveAllStudents(students);
        return student;
    }

    @PutMapping("/{id}")
    public StudentFile updateStudent(@PathVariable String id, @RequestBody StudentFile updatedStudent) throws Exception {
        List<StudentFile> students = fileStorageService.getAllStudents();
        for (StudentFile student : students) {
            if (student.getId().equals(id)) {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                fileStorageService.saveAllStudents(students);
                return student;
            }
        }
        throw new Exception("Student not found");
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) throws IOException {
        List<StudentFile> students = fileStorageService.getAllStudents();
        students.removeIf(student -> student.getId().equals(id));
        fileStorageService.saveAllStudents(students);
        return "Student deleted successfully";
    }


}
