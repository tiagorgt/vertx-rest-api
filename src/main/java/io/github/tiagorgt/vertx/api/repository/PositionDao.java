package io.github.tiagorgt.vertx.api.repository;

import io.github.tiagorgt.vertx.api.entity.Position;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by tiago on 07/10/2017.
 */
public class PositionDao {
    private static PositionDao instance;
    protected EntityManager entityManager;

    public static PositionDao getInstance(){
        if (instance == null){
            instance = new PositionDao();
        }

        return instance;
    }

    private PositionDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public Position getById(final int id) {
        return entityManager.find(Position.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Position> findAll() {
        return entityManager.createQuery("FROM " + Position.class.getName()).getResultList();
    }
}
