package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}
