package com.cursosdedesarrollo.hibernate.ejemplo05;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "CreditAccount")
public class CreditAccount extends Account {

    private BigDecimal creditLimit;

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
