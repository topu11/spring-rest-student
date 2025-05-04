package com.tasmiait.restApi.reposatories;

import com.tasmiait.restApi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollno(int rollno);
    List<Student> findByDepartment(String  department);


}
