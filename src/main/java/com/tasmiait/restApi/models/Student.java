package com.tasmiait.restApi.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table
@JsonPropertyOrder({"id","name", "rollno", "department", "marks", "createdAt", "updatedAt", "empty"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // ← use lowercase 'id' for consistency

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private int rollno;

    @Column
    private String department;

    @Column
    private float marks;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) &&
                this.rollno == 0 &&
                this.marks == 0.0f;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", rollno=" + rollno + ", department=" + department +
                ", marks=" + marks + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

    // Constructors

    public Student(String name, int rollno, String department, float marks) {
        this.name = name;
        this.rollno = rollno;
        this.department = department;
        this.marks = marks;
    }

    public Student() {
        // Default constructor
    }
}
