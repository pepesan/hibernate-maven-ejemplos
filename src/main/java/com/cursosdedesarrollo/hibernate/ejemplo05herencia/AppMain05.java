package com.cursosdedesarrollo.hibernate.ejemplo05herencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.math.BigDecimal;

public class AppMain05 {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate05.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            DebitAccount debitAccount = new DebitAccount();
            debitAccount.setId( 1L );
            debitAccount.setOwner( "John Doe" );
            debitAccount.setBalance( BigDecimal.valueOf( 100 ) );
            debitAccount.setInterestRate( BigDecimal.valueOf( 1.5d ) );
            debitAccount.setOverdraftFee( BigDecimal.valueOf( 25 ) );

            CreditAccount creditAccount = new CreditAccount();
            creditAccount.setId( 2L );
            creditAccount.setOwner( "John Doe" );
            creditAccount.setBalance( BigDecimal.valueOf( 1000 ) );
            creditAccount.setInterestRate( BigDecimal.valueOf( 1.9d ) );
            creditAccount.setCreditLimit( BigDecimal.valueOf( 5000 ) );

            sessionObj.persist( debitAccount );
            sessionObj.persist( creditAccount );

            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
            if(!sessionFactoryObj.isClosed()){
                sessionFactoryObj.close();
            }
        }
    }
}
