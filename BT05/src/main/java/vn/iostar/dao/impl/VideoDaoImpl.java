package vn.iostar.dao.impl;

import vn.iostar.configs.JPAConfig;
import vn.iostar.dao.IVideoDao;
import vn.iostar.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDaoImpl implements IVideoDao {
    @Override
	public List<Video> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
	public Video findById(String id) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Video.class, id);
    }

    @Override
	public void insert(Video video) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(video);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
	public void update(Video video) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(video);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    @Override
	public void delete(String id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.remove(video);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Video> findByTitle(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :kw";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("kw", "%" + keyword + "%");
        return query.getResultList();
    }
}
