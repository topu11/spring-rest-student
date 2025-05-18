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
    public Optional<Student> updataStudent(Student studnet,int rollno);
    public List<Student> findByMarksBetween(float low, float high);
    public List<Student> searchByNameAndDepartment(String name, String department);
    public boolean DeleteByID(long id);
    public List<Student> findByNameAndDepartment(String name, String department);
}
