package com.cursosdedesarrollo.hibernate.ejemplo05herencia;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "DebitAccount")
public class DebitAccount extends Account {

    private BigDecimal overdraftFee;


    public BigDecimal getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(BigDecimal overdraftFee) {
        this.overdraftFee = overdraftFee;
    }
}
