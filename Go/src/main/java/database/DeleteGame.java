package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 

public class DeleteGame {
 
    public static void main(String[] args) {
 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Game game = (Game) session.get(Game.class, 1);
         
        session.delete(game);
        session.getTransaction().commit();
         
        session.close();
        System.out.println("everything was OK!");
    }
}
