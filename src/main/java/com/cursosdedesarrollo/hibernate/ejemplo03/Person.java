package com.cursosdedesarrollo.hibernate.ejemplo03;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Person3")
public  class Person {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<Phone>();

    //Getters and setters are omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}