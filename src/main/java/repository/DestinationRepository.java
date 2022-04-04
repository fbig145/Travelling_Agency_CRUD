package repository;

import model.Destination;
import model.Packagee;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DestinationRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public static void destination_insert(Destination destination){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(destination);
        em.getTransaction().commit();
        em.close();
    }

    public List<Destination> take_from_destination(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT * FROM destination");
        query.setResultTransformer(Transformers.aliasToBean(Destination.class));
        List<Destination> destinationEntities = (List<Destination>) query.getResultList();

        return destinationEntities;
    }

    public static int take_dest_id(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT id_destination, name FROM travelling_agency.destination where name = '"+name+"';");
        query.setResultTransformer(Transformers.aliasToBean(Destination.class));
        List<Destination> destination =  (List<Destination>) query.getResultList();
        int destination_id = destination.get(0).getId_destination();
        return destination_id;
    }

    public static void delete_destination(String name){
        int id = take_dest_id(name);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Destination destinationToDelete = em.find(Destination.class, id);
        em.remove(destinationToDelete);

        em.getTransaction().commit();
        em.close();
    }


}
