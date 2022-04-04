package repository;

//import model.User;

import model.Destination;
import model.Packagee;
import model.User;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public static void data_insert(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> take_from_users(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT * FROM users");
        query.setResultTransformer(Transformers.aliasToBean(User.class));
        List<User> destinationEntities = (List<User>) query.getResultList();

        return destinationEntities;
    }

    public Boolean have_user(String username, String password){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT username, password FROM travelling_agency.users where username = '"+username+"' and password='"+password+"';");
        query.setResultTransformer(Transformers.aliasToBean(User.class));
        List<Optional> destinationEntities = (List<Optional>) query.getResultList();
        if(destinationEntities.isEmpty())
            return false;
        else
            return true;
    }

    public Boolean already_exist(String username){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = (Query) em.createNativeQuery("SELECT username FROM travelling_agency.users where username = '"+username+"';");
        query.setResultTransformer(Transformers.aliasToBean(User.class));
        List<Optional> destinationEntities = (List<Optional>) query.getResultList();

        if(destinationEntities.isEmpty())
            return false;
        else
            return true;
    }

}
