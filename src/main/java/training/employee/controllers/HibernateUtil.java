package training.employee.controllers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import training.employee.models.Users;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError("could not configure hibernate " + e);
        }
    }
    public static Session getSession () throws HibernateException {
        return sessionFactory.openSession();
    }

    }
