package com.tasmiait.restApi.Services;

import com.tasmiait.restApi.models.Student;
import com.tasmiait.restApi.reposatories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImplementation {
    @Autowired
    private StudentRepository studentRepository;
    @Transactional
    public Student addStudent(Student student)
    {
        try {

            return studentRepository.save(student);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student findByRollno(int rollno) {
        Optional<Student> optional = studentRepository.findByRollno(rollno);
        //System.out.println(optional);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Student> allStudent()
    {
         return  studentRepository.findAll();
    }
    public List<Student> findByDepartment(String  department)
    {
        return studentRepository.findByDepartment(department);

    }
    public Student updataStudent(Student student,long id)
    {
        Optional<Student> optional = studentRepository.findById(id);
        //System.out.println(optional);
        if (optional.isPresent()) {
            Student existingStudent = optional.get();

            // Update fields explicitly
            existingStudent.setName((student.getName()));
            existingStudent.setDepartment(student.getDepartment());
            existingStudent.setMarks(student.getMarks());
            // Add any other fields you want to update

            Student savedStudent = studentRepository.save(existingStudent);
            return savedStudent;
        }
        return null;
    }
    public List<Student> findByMarksBetween(float low, float high)
    {
        return studentRepository.findByMarksBetween(low,high);
    }
    public List<Student> searchByNameAndDepartment(String name, String department)
    {
        return studentRepository.searchByNameAndDepartment(name,department);
    }
    public boolean DeleteByID(long id)
    {
        Optional<Student> optional = studentRepository.findById(id);
        //System.out.println(optional);
        if (optional.isPresent()) {
            Student existingStudent = optional.get();
            studentRepository.delete(existingStudent);
            return true;
        }
        return false;
    }
    public List<Student> findByNameAndDepartment(String name, String department)
    {
        return studentRepository.findByNameAndDepartment(name, department);
    }
}
