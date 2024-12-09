/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.DinerOrder;
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
public class DinerOrderDAO implements IDinerOrderDAO {

    public DinerOrderDAO() {
    }

    public EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public DinerOrder create(DinerOrder dinerOrder) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dinerOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return dinerOrder;
    }
    
    public DinerOrder edit(DinerOrder dinerOrder) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dinerOrder = em.merge(dinerOrder);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dinerOrder.getOrderNumber();
                if (findDinerOrder(id) == null) {
                    throw new NonexistentEntityException("The dinerOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return dinerOrder;
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DinerOrder dinerOrder;
            try {
                dinerOrder = em.getReference(DinerOrder.class, id);
                dinerOrder.getOrderNumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dinerOrder with id " + id + " no longer exists.", enfe);
            }
            em.remove(dinerOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<DinerOrder> findDinerOrderEntities() {
        return findDinerOrderEntities(true, -1, -1);
    }

    public List<DinerOrder> findDinerOrderEntities(int maxResults, int firstResult) {
        return findDinerOrderEntities(false, maxResults, firstResult);
    }

    private List<DinerOrder> findDinerOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DinerOrder.class));
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

    public DinerOrder findDinerOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DinerOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getDinerOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DinerOrder> rt = cq.from(DinerOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
  
}
