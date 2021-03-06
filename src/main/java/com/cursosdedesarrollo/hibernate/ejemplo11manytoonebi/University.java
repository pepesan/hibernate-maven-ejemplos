package com.cursosdedesarrollo.hibernate.ejemplo11manytoonebi;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UNIVERSITY")
public class University {

    @Id
    @GeneratedValue
    @Column(name = "UNIVERSITY_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> students;

    public University() {

    }

    public University(String name, String country) {
        this.name = name;
        this.country = country;
    }

}
