package com.cursosdedesarrollo.hibernate.ejemplo06;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Date;
import java.util.List;

public class AppMain06 {

    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate06.cfg.xml");

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
                userObj.setUserid(i);
                userObj.setUsername("Editor " + i);
                userObj.setCreatedBy("Administrator");
                userObj.setCreatedDate(new Date());

                sessionObj.save(userObj);
                //sessionObj.persist(userObj);

            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // consulta básica
            String hql = "FROM User";
            Query query = sessionObj.createQuery(hql);
            List results = query.list();
            System.out.println("\n"+results+"\n");
            // as
            hql = "FROM User AS u";
            query = sessionObj.createQuery(hql);
            results = query.list();
            System.out.println("\n"+results+"\n");

            // select
            hql = "SELECT u.username FROM User u";
            query = sessionObj.createQuery(hql);
            results = query.list();
            System.out.println("\n"+results+"\n");

            //where
            hql = "FROM User u WHERE u.userid=101";
            query = sessionObj.createQuery(hql);
            results = query.list();
            System.out.println("\n"+results+"\n");

            //order
            hql = "FROM User u ORDER BY u.userid DESC";
            query = sessionObj.createQuery(hql);
            results = query.list();
            System.out.println("\n"+results+"\n");


            //group by
            hql = "SELECT SUM(u.userid),u.createdDate FROM User u GROUP BY u.createdDate";
            query = sessionObj.createQuery(hql);
            results = query.list();
            System.out.println("\n"+results+"\n");

            //parámetros nombrados
            hql = "FROM User u WHERE u.userid = :user_id";
            query = sessionObj.createQuery(hql);
            query.setParameter("user_id",101);
            results = query.list();
            System.out.println("\n"+results+"\n");

            //update
            hql = "UPDATE User set createdDate = :cdate "  +
                    "WHERE id = :user_id";
            query = sessionObj.createQuery(hql);
            query.setParameter("cdate", new Date());
            query.setParameter("user_id", 101);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            //delete
            hql = "DELETE FROM User "  +
                    "WHERE userid = :user_id";
            query = sessionObj.createQuery(hql);
            query.setParameter("user_id", 105);
            int resultado = query.executeUpdate();
            System.out.println("Rows affected: " + resultado);



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
