package training.employee.controllers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import training.employee.models.Users;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;



    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure().addAnnotatedClass(Users.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }

}
