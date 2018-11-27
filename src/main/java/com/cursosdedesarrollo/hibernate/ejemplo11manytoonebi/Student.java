package com.cursosdedesarrollo.hibernate.ejemplo11manytoonebi;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SECTION")
    private String section;

    @ManyToOne(optional = false)
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;

    public Student() {
    }

    public Student(String firstName, String lastName, String section) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}
