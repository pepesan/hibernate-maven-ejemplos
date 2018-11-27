package com.cursosdedesarrollo.hibernate.ejemplo07manytomanyuni;

import javax.persistence.*;

@Entity(name = "Person7")
public
class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //Getters and setters are omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}