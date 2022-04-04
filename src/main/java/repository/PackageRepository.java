package repository;

import model.Packagee;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class PackageRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public static void package_insert(Packagee packagee){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(packagee);
        em.getTransaction().commit();
        em.close();
    }

    public List<Packagee> load_package(int id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT * FROM package WHERE destination_id = '"+id+"';");
        query.setResultTransformer(Transformers.aliasToBean(Packagee.class));
        List<Packagee> packageEntities = (List<Packagee>) query.getResultList();

        return packageEntities;
    }

    public void delete_package(int search_id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT id_package FROM package WHERE destination_id = '"+search_id+"';");
        query.setResultTransformer(Transformers.aliasToBean(Packagee.class));
        List<Packagee> packageEntities = (List<Packagee>) query.getResultList();
        int id_package = packageEntities.get(0).getId_package();

        Packagee packageToDelete = em.find(Packagee.class, id_package);
        em.remove(packageToDelete);

        em.getTransaction().commit();
        em.close();

    }

    public void update_package(int search_id, String name, int price, Date start, Date end, String details, int spots, String status){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT id_package FROM package WHERE destination_id = '"+search_id+"';");
        query.setResultTransformer(Transformers.aliasToBean(Packagee.class));
        List<Packagee> packageEntities = (List<Packagee>) query.getResultList();
        int id_package = packageEntities.get(0).getId_package();

        Packagee packageToDelete = em.find(Packagee.class, id_package);
        packageToDelete.setName(name);
        packageToDelete.setPrice(price);
        packageToDelete.setStart(start);
        packageToDelete.setEnd(end);
        packageToDelete.setDetails(details);
        packageToDelete.setSpots(spots);
        packageToDelete.setStatus(status);
        em.merge(packageToDelete);

        em.getTransaction().commit();
        em.close();
    }

    public static List<Packagee> showPackages(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT * FROM travelling_agency.package;");
        query.setResultTransformer(Transformers.aliasToBean(Packagee.class));
        List<Packagee> packageEntities = (List<Packagee>) query.getResultList();

        return packageEntities;

    }



}
