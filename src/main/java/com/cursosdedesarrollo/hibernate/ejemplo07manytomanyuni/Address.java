package com.cursosdedesarrollo.hibernate.ejemplo07manytomanyuni;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Address7")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String street;

    @Column(name = "`number`")
    private String number;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Person> personas = new ArrayList<Person>();

    public Address(String s, String s1) {
        this.street=s;
        this.number=s1;
    }

    //Getters and setters are omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Person> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Person> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", personas=" + personas +
                '}';
    }
}