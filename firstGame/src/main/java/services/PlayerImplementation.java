package services;

import entitites.Player;
import lombok.Data;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Ingres9Dialect;

import java.lang.reflect.Array;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;


public class PlayerImplementation {
    static Session session;
    static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {


        // Turn Hibernate logging off
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
        return sessionFactory;
    }


    public static void findThePlayer(){
        Scanner scanner=new Scanner(System.in);
        Player player=null;

        try{
            session=buildSessionFactory().openSession();
            session.beginTransaction();
            List randomPlayer= session.createQuery("SELECT pname FROM player ORDER BY RAND()").list();
            String randomFinal= (String) randomPlayer.get(0);
            int j=0;

            while(true) {
                System.out.println("\n Give a player Name");
                String name = scanner.nextLine();
                if (name.equalsIgnoreCase(randomFinal)) {
                    System.out.println("Congrats, you found it");
                    System.exit(0);
                    break;

                }
                else
                {
                    j = j + 1;

                    switch (j) {
                        case 1:{
                            System.out.println("You havent found it.Here is HELP 1");
                            Query query=session.createQuery("Select isactive from player where pname=?");
                            query.setString(0, randomFinal);
                            boolean isActive=(boolean) query.list().get(0);
                            if(isActive){
                                System.out.println("Player is active");
                            }
                            else {
                                System.out.println("Player is not active");
                            }
                            break;

                        }
                        case 2:{
                            System.out.println("You havent found it.Here is HELP 2");
                            Query query = session.createQuery("SELECT nationality FROM player where pname=?");
                            query.setString(0, randomFinal);
                            String nationality = (String) query.list().get(0);
                            System.out.println("Nationality: "+nationality);
                            break;
                        }
                        case 3:{
                            System.out.println("You havent found it.Here is HELP 3");
                            Query query=session.createQuery("Select age from player where pname=?");
                            query.setString(0, randomFinal);
                            int age = (int) query.list().get(0);
                            System.out.println("Age "+age);
                            break;
                        }
                        case 4:{
                            System.out.println("You havent found it.Here is HELP 4");
                            Query query=session.createQuery("Select position from player where pname=?");
                            query.setString(0, randomFinal);
                            String position = (String) query.list().get(0);
                            System.out.println("Position "+position);
                            break;
                        }
                        case 5:{
                            System.out.println("You havent found it.Here is HELP 5");
                            Query query=session.createQuery("Select league from player where pname=?");
                            query.setString(0, randomFinal);
                            String league = (String) query.list().get(0);
                            System.out.println("League: "+league);
                            break;

                        }
                        case 6:{
                            System.out.println("You havent found it.Here is LAST HELP");
                            Query query=session.createQuery("Select team from player where pname=?");
                            query.setString(0, randomFinal);
                            String team = (String) query.list().get(0);
                            System.out.println("Team: "+team);
                            break;
                        }
                        case 7:{
                            System.out.println("Sorry. You havent found it. The player was "+randomFinal);
                            System.exit(0);
                            break;
                        }
                    }

                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
