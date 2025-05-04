package com.tasmiait.restApi.Services;

import com.tasmiait.restApi.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public Student addStudent(Student studnet);
    public Optional<Student> findByRollno(int rollno);
    public List<Student> allStudent();
    public List<Student> findByDepartment(String  department);
}
