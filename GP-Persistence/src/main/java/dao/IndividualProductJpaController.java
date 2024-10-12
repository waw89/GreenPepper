/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.IndividualProduct;
import dao.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author waw
 */
public class IndividualProductJpaController implements Serializable {

    public IndividualProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IndividualProduct individualProduct) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(individualProduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IndividualProduct individualProduct) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            individualProduct = em.merge(individualProduct);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = individualProduct.getId();
                if (findIndividualProduct(id) == null) {
                    throw new NonexistentEntityException("The individualProduct with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IndividualProduct individualProduct;
            try {
                individualProduct = em.getReference(IndividualProduct.class, id);
                individualProduct.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The individualProduct with id " + id + " no longer exists.", enfe);
            }
            em.remove(individualProduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IndividualProduct> findIndividualProductEntities() {
        return findIndividualProductEntities(true, -1, -1);
    }

    public List<IndividualProduct> findIndividualProductEntities(int maxResults, int firstResult) {
        return findIndividualProductEntities(false, maxResults, firstResult);
    }

    private List<IndividualProduct> findIndividualProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IndividualProduct.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public IndividualProduct findIndividualProduct(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IndividualProduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndividualProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IndividualProduct> rt = cq.from(IndividualProduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
