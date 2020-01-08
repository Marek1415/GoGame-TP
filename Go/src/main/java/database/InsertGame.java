package database;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsertGame {

	public static void main(String[] args) {
		 
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
        Game game = new Game();
        game.setSize(14);
        game.setDate(new Date(19991231));
         
        session.save(game);
        session.getTransaction().commit();
         
        session.close();
        System.out.println("everything was OK!");
 
    }
	
}
