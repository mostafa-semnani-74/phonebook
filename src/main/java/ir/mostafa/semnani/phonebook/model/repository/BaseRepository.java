package ir.mostafa.semnani.phonebook.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BaseRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<T> findAll(String query,Class<T> t) {
        TypedQuery<T> findAllQuery = entityManager.createQuery(query, t);
        return findAllQuery.getResultList();
    }

    public T findById(Class<T> t,Long id) {
        return entityManager.find(t, id);
    }

    public void save(T t) {
        entityManager.persist(t);
    }

    public void delete(T t) {
        entityManager.remove(t);
    }
}
