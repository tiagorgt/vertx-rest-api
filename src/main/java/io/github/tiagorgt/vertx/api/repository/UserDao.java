package io.github.tiagorgt.vertx.api.repository;

import io.github.tiagorgt.vertx.api.entity.User;
import io.netty.util.internal.StringUtil;
import io.vertx.core.json.JsonObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tiago on 07/10/2017.
 */
public class UserDao {
    private static UserDao instance;
    protected EntityManager entityManager;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }

    private UserDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public User getById(String cpf) {
        Object result = entityManager.find(User.class, cpf);
        if (result != null) {
            return (User) result;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager.createQuery("FROM " + User.class.getName()).getResultList();
    }

    public List<User> getByFilter(JsonObject filter) {
        Query query = entityManager.createQuery(sqlFilter(filter));
        parametersFilter(filter, query);
        List<User> result = query.getResultList();

        return result;
    }

    private String sqlFilter(JsonObject filter) {
        String sqlQuery = "SELECT u FROM User u";
        String preParameter = " WHERE";
        String sqlParameter = "";

        if (!StringUtil.isNullOrEmpty(filter.getString("name"))) {
            sqlParameter += preParameter + " upper(u.name) LIKE upper(:name)";
            preParameter = " OR";
        }

        if (!StringUtil.isNullOrEmpty(filter.getString("status")) && !filter.getString("status").equals("AI")) {
            sqlParameter += preParameter + " u.status = :status";
            preParameter = " OR";
        }

        if (!StringUtil.isNullOrEmpty(String.valueOf(filter.getValue("profile"))) && !String.valueOf(filter.getValue("profile")).equals("99")) {
            sqlParameter += preParameter + " u.profile = :profile";
            preParameter = " OR";
        }

        return sqlQuery + sqlParameter;
    }

    private void parametersFilter(JsonObject filter, Query query) {
        if (!StringUtil.isNullOrEmpty(filter.getString("name"))) {
            String likeNameParam = "%" + filter.getString("name") + "%";
            query.setParameter("name", likeNameParam);
        }

        if (!StringUtil.isNullOrEmpty(filter.getString("status")) && !filter.getString("status").equals("AI")) {
            query.setParameter("status", filter.getString("status"));
        }

        if (!StringUtil.isNullOrEmpty(String.valueOf(filter.getValue("profile"))) && !String.valueOf(filter.getValue("profile")).equals("99")) {
            query.setParameter("profile", filter.getInteger("profile"));
        }
    }

    public void persist(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(User user) {
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, user.getCpf());
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(String cpf) {
        try {
            User user = getById(cpf);
            remove(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
