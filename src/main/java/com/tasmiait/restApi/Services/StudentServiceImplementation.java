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
    private StudentRepository studnetRepository;
    @Transactional
    public Student addStudent(Student student)
    {
        try {

            return studnetRepository.save(student);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student findByRollno(int rollno) {
        Optional<Student> optional = studnetRepository.findByRollno(rollno);
        //System.out.println(optional);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Student> allStudent()
    {
         return  studnetRepository.findAll();
    }
    public List<Student> findByDepartment(String  department)
    {
        return studnetRepository.findByDepartment(department);

    }

}
