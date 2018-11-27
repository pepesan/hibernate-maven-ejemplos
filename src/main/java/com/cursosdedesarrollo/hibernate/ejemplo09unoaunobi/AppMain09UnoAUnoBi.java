package com.cursosdedesarrollo.hibernate.ejemplo09unoaunobi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class AppMain09UnoAUnoBi {

    static Student student;
    static Address address;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate09.cfg.xml");

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


            Student student= new Student();
            student.setFirstName("Juan");
            sessionObj.persist( student);
            Address address = new Address();
            address.setStreet("Gran Vía");
            sessionObj.persist( address);
            student.setAddress(address);
            sessionObj.persist( student);
            address.setStudent(student);
            sessionObj.persist( address);
            sessionObj.flush();
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");
            System.out.println("\nEstudiante:"+student+"\n");
            System.out.println("\nDirección:"+address+"\n");
            System.out.println("\nDirección:"+address.getStudent()+"\n");
            /*
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            sessionObj.beginTransaction();
            List<Person> listado =sessionObj.createQuery("from Person24").list();
            sessionObj.getTransaction().commit();
            System.out.println("\n"+listado+"\n");
            //Obtener un valor de la BBDD
            sessionObj.beginTransaction();
            Person p =(Person)sessionObj.get(Person.class,new Long(1));
            sessionObj.getTransaction().commit();
            System.out.println("\n"+p+"\n");
            //Cambiar un valor de la BBDD
            sessionObj.beginTransaction();
            p.setName("Pepe");
            sessionObj.save(p);
            sessionObj.getTransaction().commit();
            //Coger un valor de la BBDD
            sessionObj.beginTransaction();
            p =(Person)sessionObj.get(Person.class,new Long(1));
            sessionObj.getTransaction().commit();
            System.out.println("\n"+p+"\n");
            //Borrar un objeto de la BBDD
            sessionObj.beginTransaction();
            sessionObj.delete(phone2);
            sessionObj.delete(phone1);
            sessionObj.delete(person);
            sessionObj.getTransaction().commit();
            System.out.println("\n"+p+"\n");
            //Comprobar que no hay objeto
            sessionObj.beginTransaction();
            p =(Person)sessionObj.get(Person.class,new Long(1));
            sessionObj.getTransaction().commit();
            System.out.println("\n"+p+"\n");
            */
        } catch(Exception sqlException) {
            if( sessionObj!=null && null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj!=null && sessionObj != null) {
                sessionObj.close();
            }
            if(sessionFactoryObj!=null && !sessionFactoryObj.isClosed()){
                sessionFactoryObj.close();
            }
        }
    }
}
