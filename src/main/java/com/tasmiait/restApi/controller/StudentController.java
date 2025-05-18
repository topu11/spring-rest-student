package com.tasmiait.restApi.controller;

import com.tasmiait.restApi.Services.StudentServiceImplementation;
import com.tasmiait.restApi.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentServiceImplementation studentServiceImplementation;
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {

        Student savedStudent = studentServiceImplementation.addStudent(student);
        Map<String, Object> studentReturnData = new LinkedHashMap<>();

        if (savedStudent != null) {


            studentReturnData.put("success", true); // Set success flag
            studentReturnData.put("message", "Student Added Successfully");
            studentReturnData.put("data", savedStudent);

            // Return 201 Created if the student was inserted, else 409 Conflict
            return ResponseEntity.status(HttpStatus.CREATED).body(studentReturnData);

       } else {
            studentReturnData.put("success", false);
            studentReturnData.put("message", "Cannot insert");
            studentReturnData.put("data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentReturnData); // 400 Bad Request
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> allStudent()
    {
        List<Student> students=studentServiceImplementation.allStudent();
        Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
        studnetResultMapp.put("success",true);
        studnetResultMapp.put("data",students);
        return ResponseEntity.status(HttpStatus.OK).body(studnetResultMapp);

    }
    @GetMapping("/rollno/{rollno}")
    public ResponseEntity<?> studentByRoll(@PathVariable("rollno") int rollno)
    {
        Student student=studentServiceImplementation.findByRollno(rollno);
        if(student != null)
        {
            Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
            studnetResultMapp.put("success",true);
            studnetResultMapp.put("data",student);
            return ResponseEntity.status(HttpStatus.OK).body(studnetResultMapp);
        }else
        {
            Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
            studnetResultMapp.put("success",false);
            studnetResultMapp.put("data",null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(studnetResultMapp);
        }


    }
    @GetMapping("/department/{department}")
    public ResponseEntity<?> allStudentByDepartment(@PathVariable("department") String department)
    {
        List<Student> students=studentServiceImplementation.findByDepartment(department);
        Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
        studnetResultMapp.put("success",true);
        studnetResultMapp.put("data",students);
        return ResponseEntity.status(HttpStatus.OK).body(studnetResultMapp);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updataStudent(@PathVariable("id") long id,@RequestBody Student student) {

        Student savedStudent = studentServiceImplementation.updataStudent(student,id);
        Map<String, Object> studentReturnData = new LinkedHashMap<>();

        if (savedStudent != null) {


            studentReturnData.put("success", true); // Set success flag
            studentReturnData.put("message", "Student updated Successfully");
            studentReturnData.put("data", savedStudent);

            // Return 201 Created if the student was inserted, else 409 Conflict
            return ResponseEntity.status(HttpStatus.OK).body(studentReturnData);

        } else {
            studentReturnData.put("success", false);
            studentReturnData.put("message", "studen not found");
            studentReturnData.put("data", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentReturnData); // 400 Bad Request
        }
    }

    @GetMapping("/marks-range")
    public ResponseEntity<?> getStudentsByMarksRange(@RequestParam float min, @RequestParam float max) {
        List<Student> students=studentServiceImplementation.findByMarksBetween(min, max);
        Map<String,Object> finalMapp=new LinkedHashMap<>();

        Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
        studnetResultMapp.put("total",students.size());
        studnetResultMapp.put("students",students);
        finalMapp.put("success",true);
        finalMapp.put("data",studnetResultMapp);
        return ResponseEntity.status(HttpStatus.OK).body(finalMapp);
    }

    @GetMapping("/searchby/name/department")
    public ResponseEntity<?> getStudentsSearchByNameDepartment(@RequestParam(required = false)  String name, @RequestParam(required = false)  String department) {
        List<Student> students=studentServiceImplementation.searchByNameAndDepartment(name, department);
        Map<String,Object> finalMapp=new LinkedHashMap<>();

        Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
        studnetResultMapp.put("total",students.size());
        studnetResultMapp.put("students",students);
        finalMapp.put("success",true);
        finalMapp.put("data",studnetResultMapp);
        return ResponseEntity.status(HttpStatus.OK).body(finalMapp);
    }
    @PostMapping("/findby/name/department")
    public ResponseEntity<?> getStudentFindByNameDepartment(@RequestParam String name, @RequestParam String department)
    {
        List<Student> students=studentServiceImplementation.findByNameAndDepartment(name, department);
        Map<String,Object> finalMapp=new LinkedHashMap<>();

        Map<String,Object> studnetResultMapp=new LinkedHashMap<>();
        studnetResultMapp.put("total",students.size());
        studnetResultMapp.put("students",students);
        finalMapp.put("success",true);
        finalMapp.put("data",studnetResultMapp);
        return ResponseEntity.status(HttpStatus.OK).body(finalMapp);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") long id)
    {
       boolean isStduetDelete=studentServiceImplementation.DeleteByID(id);
        Map<String,Object> finalMapp=new LinkedHashMap<>();
       if(isStduetDelete)
       {
           finalMapp.put("success",true);
           finalMapp.put("message","Studnet "+ id + " is deleted successfully");
       }else
       {
           finalMapp.put("success",false);
           finalMapp.put("message","Can not find studnet by "+ id);
       }
        return ResponseEntity.status(HttpStatus.OK).body(finalMapp);
    }

}
