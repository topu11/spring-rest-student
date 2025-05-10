package com.tasmiait.restApi.reposatories;

import com.tasmiait.restApi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRollno(int rollno);
    List<Student> findByDepartment(String  department);
    List<Student> findByMarksBetween(float low, float high);
    @Query("SELECT s FROM Student s WHERE " +
            "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:department IS NULL OR LOWER(s.department) LIKE LOWER(CONCAT('%', :department, '%')))")
    List<Student> searchByNameAndDepartment(@Param("name") String name,
                                            @Param("department") String department);


}
