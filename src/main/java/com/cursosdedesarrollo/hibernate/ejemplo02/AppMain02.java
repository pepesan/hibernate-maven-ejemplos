package com.cursosdedesarrollo.hibernate.ejemplo02;

import com.cursosdedesarrollo.hibernate.ejemplo01.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.List;

public class AppMain02 {

    static Phone phone;
    static PhoneDetails phoneDetails;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate02.cfg.xml");

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


            phone = new Phone();
            phone.setNumber("123345");
            phoneDetails = new PhoneDetails();
            phoneDetails.setProvider("O2");
            phoneDetails.setTechnology("5G");
            phone.setDetails(phoneDetails);
            sessionObj.save(phoneDetails);
            sessionObj.save(phone);
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            sessionObj.beginTransaction();
            List<Phone> listado =sessionObj.createQuery("from phones").list();
            sessionObj.getTransaction().commit();
            System.out.println("\n"+listado+"\n");
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
