package vn.iostar.dao.impl;

import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.IUserDao;
import vn.iostar.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    @Override
	public List<User> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
	public User findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(User.class, id);
    }

    @Override
	public void insert(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
	public void update(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
	public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User user = em.find(User.class, id);
            if (user != null) em.remove(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<User> findByUsername(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.username LIKE :kw";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("kw", "%" + keyword + "%");
        return query.getResultList();
    }
    
    @Override
    public List<User> findAll(int page, int size) {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User u";
        return ((Long) em.createQuery(jpql).getSingleResult()).intValue();
    }

    
}
