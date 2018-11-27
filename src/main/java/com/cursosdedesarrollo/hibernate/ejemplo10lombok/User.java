package com.cursosdedesarrollo.hibernate.ejemplo10lombok;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "User25")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userid;

    @Column(name = "user_name")
    private String username;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

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
