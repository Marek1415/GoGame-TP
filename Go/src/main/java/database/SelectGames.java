package database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
 
public class SelectGames {
 
    public static void main(String[] args) {
 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
         
        List<Game> games = session.createQuery("from Game").list();
         
        session.close();
         
        for (Game gm : games) {
            System.out.println(gm.getId()+". "+gm.getSize()+" "+gm.getDate());
        }
    }
}