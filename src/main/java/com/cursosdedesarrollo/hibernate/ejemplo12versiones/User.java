package com.cursosdedesarrollo.hibernate.ejemplo12versiones;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user_table12")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private int userid;

    @Column(name = "user_name")
    private String username;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Version
    private Integer version;

    public User() {
        this.userid = 0;
        this.username = "";
        this.createdBy = "";
        this.createdDate = new Date();
    }

    public User(int userid, String username, String createdBy, Date createdDate) {
        this.userid = userid;
        this.username = username;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

}
