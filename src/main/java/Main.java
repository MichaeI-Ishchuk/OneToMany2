import Test.Adress;
import Test.Client;
import Test.Manager;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nata on 26.01.2017.
 */
public class Main {


    public static void main(String[] args) {


        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.jpa");
        EntityManager em = sessionFactory.createEntityManager();
        Client c1 = new Client();
        c1.setName("Vasy");
        c1.setSurname("ferver");

        Client c2 = new Client();
        c2.setName("Koly");
        c2.setSurname("Hjoe");

        Client c4 = new Client();
        c4.setName("Koly3");
        c4.setSurname("Hjoe2");

        Manager m1 = new Manager();
        m1.setName("Man1");
        Manager m2 = new Manager();
        m2.setName("Man2");


        em.getTransaction().begin();

        Adress ad1 = new Adress();
        ad1.setClient(c1);
        ad1.setClientAdress("Телиги");
        Adress ad2 = new Adress();
        ad2.setClient(c2);
        ad2.setClientAdress("Борщаговка");


        Adress add3 = new Adress();
        add3.setClient(c4);
        add3.setClientAdress("Jdfier");

        em.persist(ad1);
        em.persist(ad2);
        em.persist(add3);
        em.getTransaction().commit();
        em.getTransaction().begin();



        c1.setAdress(ad1);
        c2.setAdress(ad2);
        c4.setAdress(add3);


        em.persist(c1);
        em.persist(c2);
        em.persist(c4);
        em.getTransaction().commit();
///////////////////////////////////////////
         em.getTransaction().begin();

        List<Client> clients1 = new ArrayList<Client>();
        clients1.add(c1);
        clients1.add(c2);

        c1.setManager(m1);
        c2.setManager(m1);

        m1.setClientList(clients1);
        c1.setManager(m1);
        c2.setManager(m1);


        List<Client> clients2 = new ArrayList<Client>();

        clients1.add(c4);
        c4.setManager(m2);

        m2.setClientList(clients2);
        c4.setManager(m2);


        em.persist(m1);
        em.persist(m2);

        em.getTransaction().commit();
        /////////////////////////
        em.getTransaction().begin();

        Client c3 = em.createQuery( "FROM Test.Client cl " +
                "WHERE name=:cl_name", Client.class)
                .setParameter("cl_name", "Vasy")
                .getSingleResult();
        em.getTransaction().commit();
        System.out.println(c3.getName()+" "+c3.getAdress());


//////////////////////////////////////////

        em.getTransaction().begin();

        Adress ad4 = em.createQuery( "FROM Test.Adress ad " +
                "WHERE client_adress=:ad_name", Adress.class)
                .setParameter("ad_name", "Телиги")
                .getSingleResult();
        em.getTransaction().commit();


        System.out.println(ad4.getClientAdress());
        System.out.println(ad4.getClient().getName());
        /////////////////////////////////

        em.getTransaction().begin();

          Manager m3 = em.createQuery("FROM Test.Manager mn WHERE name=:managerName", Manager.class).setParameter("managerName", "Man1").getSingleResult();
          List<Client> managerClients = m3.getClientList();

        em.getTransaction().commit();

        System.out.println(m3.getName());
        for (Client s : managerClients
             ) {
            System.out.println(s.getName());
        }

        em.close();
        sessionFactory.close();


    }
}



