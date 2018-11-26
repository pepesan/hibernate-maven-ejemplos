package com.cursosdedesarrollo.hibernate.ejemplo01;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;

public class AppMain {

    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate01.cfg.xml");

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

            for(int i = 101; i <= 105; i++) {
                userObj = new User();
                //userObj.setUserid(i);
                userObj.setUsername("Editor " + i);
                userObj.setCreatedBy("Administrator");
                userObj.setCreatedDate(new Date());

                sessionObj.save(userObj);
                //sessionObj.persist(userObj);

            }
            //Capturar un objeto por su id
            User user105=(User) sessionObj.get(User.class,userObj.getUserid());
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");
            //modificación
            user105.setUsername("Editor " + 2000);
            sessionObj.save(user105);
            //JPA merge
            System.out.println("\n.......Record Modified Successfully To The Database.......\n");
            //borrado
            sessionObj.delete(user105);
            System.out.println("\n.......Record Deleted Successfully To The Database.......\n");
            User guardado= (User)sessionObj.get(User.class,101);

            System.out.println("\n Dato Guardado: "+guardado+"\n");

            

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
