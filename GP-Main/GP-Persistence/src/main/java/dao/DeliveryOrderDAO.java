/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.DeliveryOrder;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author waw
 */
public class DeliveryOrderDAO implements IDeliveryDAO, Serializable {

    public DeliveryOrderDAO() {

    }

    public EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(deliveryOrder);
            em.getTransaction().commit();
            return deliveryOrder;

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public DeliveryOrder edit(DeliveryOrder deliveryOrder) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            deliveryOrder = em.merge(deliveryOrder);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = deliveryOrder.getOrderNumber();
                if (findDeliveryOrder(id) == null) {
                    throw new NonexistentEntityException("The deliveryOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return deliveryOrder;
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DeliveryOrder deliveryOrder;
            try {
                deliveryOrder = em.getReference(DeliveryOrder.class, id);
                deliveryOrder.getOrderNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deliveryOrder with id " + id + " no longer exists.", enfe);
            }
            em.remove(deliveryOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DeliveryOrder> findDeliveryOrderEntities() {
        return findDeliveryOrderEntities(true, -1, -1);
    }

    public List<DeliveryOrder> findDeliveryOrderEntities(int maxResults, int firstResult) {
        return findDeliveryOrderEntities(false, maxResults, firstResult);
    }

    private List<DeliveryOrder> findDeliveryOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DeliveryOrder.class));
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

    public DeliveryOrder findDeliveryOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DeliveryOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeliveryOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DeliveryOrder> rt = cq.from(DeliveryOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
