package services;

import entitites.Player;
import lombok.Data;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            System.out.println(randomFinal);
            int j=0;

            while(true){
                System.out.println("Give a player Name");
                String name =scanner.nextLine();
                if(name.equals(randomFinal)){
                    System.out.println("Congrats, you found it");
                    Query finalPlayer=session.createQuery("Select pname,team,league,position from player where pname=?");
                    finalPlayer.setString(0,randomFinal);

                }
                else{
                    j=j+1;
                    if(j==1){
                        System.out.println("You havent found it. 4 Help Left");
                        System.out.println("Here is a help. \n Team:");
                            Query ql=session.createQuery("SELECT team FROM player where pname=?");
                            ql.setString(0,randomFinal);
                            List lista=ql.list();
                            String team= (String) lista.get(0);
                        System.out.println(team);
                    }
                    else if(j==2){
                        System.out.println("no its not");
                    }
                    else if(j==3){
                        System.out.println("Two left");
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
