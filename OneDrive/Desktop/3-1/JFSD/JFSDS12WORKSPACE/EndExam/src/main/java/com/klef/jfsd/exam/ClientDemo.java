package com.klef.jfsd.exam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        insert(sessionFactory);
        viewall(sessionFactory);
        sessionFactory.close();
    }

    private static void insert(SessionFactory sessionFactory) {
        Session s = sessionFactory.openSession();
        Transaction t = s.beginTransaction();
        Client c = new Client();
        c.setName("ABC");
        c.setGender("FeMale");
        c.setAge(30);
        c.setLocation("klu, guntur");
        c.setEmail("abc@gmail.com");
        c.setMobile("1234567890");
        s.save(c);
        t.commit();
        s.close();
        System.out.println("Records inserted successfully.");
    }

    private static void viewall(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        for (Client client : clients) {
        	System.out.println(client.getId());
            System.out.println(client.getName());
            System.out.println(client.getAge());
            System.out.println(client.getEmail());
            System.out.println(client.getLocation());
            System.out.println(client.getMobile());
        }
        session.close();
    }
}
