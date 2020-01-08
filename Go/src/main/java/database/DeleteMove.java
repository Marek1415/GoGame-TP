package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 

public class DeleteMove {
 
    public static void main(String[] args) {
 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        List<Move> moves = session.createQuery("from Move").list();
         
        session.delete(moves.get(1));
        session.getTransaction().commit();
         
        session.close();
        System.out.println("everything was OK!");
    }
}
