package com.cursosdedesarrollo.hibernate.ejemplo11manytoonebi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

public class AppMain11 {

    static University userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate11.cfg.xml");

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
            Student student1 = new Student("Sam", "Disilva", "Maths");
            Student student2 = new Student("Joshua", "Brill", "Science");
            Student student3 = new Student("Peter", "Pan", "Physics");

            University university = new University("CAMBRIDGE", "ENGLAND");
            List<Student> allStudents = new ArrayList<Student>();

            student1.setUniversity(university);
            student2.setUniversity(university);
            student3.setUniversity(university);

            allStudents.add(student1);
            allStudents.add(student2);
            allStudents.add(student3);

            university.setStudents(allStudents);


            sessionObj.persist(university);// Students will be presisted automatically, thanks to CASCADE.ALL defined on students
            // property of University class.

            List<Student> students = (List<Student>) sessionObj.createQuery(
                    "from Student ").list();
            for (Student s : students) {
                System.out.println("Student Details : " + s);
                System.out.println("Student University Details: "
                        + s.getUniversity());
            }

            // Note that now you can also access the relationship from University to Student

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
