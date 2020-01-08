package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
 
public class SelectMoves {
 
    public static void main(String[] args) {
 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
         
        List<Move> moves = session.createQuery("from Move").list();
         
        session.close();
         
        for (Move mv : moves) {
            System.out.println(
            		mv.getGame().getId() + " " + 
            		mv.getMove() + " " + 
            		mv.getPlayer() + " " + 
            		mv.getPosition());
        }
    }
}