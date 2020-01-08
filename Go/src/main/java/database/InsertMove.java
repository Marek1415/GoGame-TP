package database;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsertMove {

	public static void main(String[] args) {
		 
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Move move = new Move();
        List<Game> games = session.createQuery("from Game").list();
        
        move.setGame(games.get(0));
        move.setMove(4);
        move.setPlayer('B');
        move.setPosition(20);
         
        session.save(move);
        session.getTransaction().commit();
         
        session.close();
        System.out.println("everything was OK!");
    }
	
}
