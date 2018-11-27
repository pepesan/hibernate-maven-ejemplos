package com.cursosdedesarrollo.hibernate.ejemplo07manytomanyuni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class AppMain07 {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate07.cfg.xml");

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


            Person person1 = new Person();
            Person person2 = new Person();

            Address address1 = new Address( "12th Avenue", "12A" );
            Address address2 = new Address( "18th Avenue", "18B" );
            sessionObj.save( address1);
            sessionObj.save( address2);


            sessionObj.save( person1 );
            sessionObj.save( person2 );

            address1.getPersonas().add(person1);
            sessionObj.save(address1);
            address1.getPersonas().add(person2);
            sessionObj.flush();


            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            sessionObj.beginTransaction();
            List<Person> listado=sessionObj.createQuery("from Person7").list();
            sessionObj.getTransaction().commit();
            System.out.println("\n"+listado +"\n");
            sessionObj.beginTransaction();
            List<Person> listado_dir=sessionObj.createQuery("from Address7").list();
            sessionObj.getTransaction().commit();
            System.out.println("\n"+listado_dir +"\n");
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
