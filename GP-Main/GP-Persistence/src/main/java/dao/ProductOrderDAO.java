/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.ORDER_STATE;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC
 */
public class ProductOrderDAO implements Serializable {

    public ProductOrderDAO() {
    }

    public EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }
        
    public void create(ProductOrder productOrder) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order order = productOrder.getOrder();
            if (order != null) {
                order = em.getReference(order.getClass(), order.getOrderNumber());
                productOrder.setOrder(order);
            }
            em.persist(productOrder);
            if (order != null) {
                order.getProducts().add(productOrder);
                order = em.merge(order);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductOrder productOrder) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductOrder persistentProductOrder = em.find(ProductOrder.class, productOrder.getId());
            Order orderOld = persistentProductOrder.getOrder();
            Order orderNew = productOrder.getOrder();
            if (orderNew != null) {
                orderNew = em.getReference(orderNew.getClass(), orderNew.getOrderNumber());
                productOrder.setOrder(orderNew);
            }
            productOrder = em.merge(productOrder);
            if (orderOld != null && !orderOld.equals(orderNew)) {
                orderOld.getProducts().remove(productOrder);
                orderOld = em.merge(orderOld);
            }
            if (orderNew != null && !orderNew.equals(orderOld)) {
                orderNew.getProducts().add(productOrder);
                orderNew = em.merge(orderNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = productOrder.getId();
                if (findProductOrder(id) == null) {
                    throw new NonexistentEntityException("The productOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductOrder productOrder;
            try {
                productOrder = em.getReference(ProductOrder.class, id);
                productOrder.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productOrder with id " + id + " no longer exists.", enfe);
            }
            Order order = productOrder.getOrder();
            if (order != null) {
                order.getProducts().remove(productOrder);
                order = em.merge(order);
            }
            em.remove(productOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductOrder> findProductOrderEntities() {
        return findProductOrderEntities(true, -1, -1);
    }

    public List<ProductOrder> findProductOrderEntities(int maxResults, int firstResult) {
        return findProductOrderEntities(false, maxResults, firstResult);
    }

    private List<ProductOrder> findProductOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductOrder.class));
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

    public ProductOrder findProductOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductOrder> rt = cq.from(ProductOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   public void deleteAllProductsByOrder(Order order) {
    EntityManager em = getEntityManager();
    em.getTransaction().begin();
    String jpql = "DELETE FROM ProductOrder o WHERE o.order = :order";
    Query query = em.createQuery(jpql);
    query.setParameter("order", order);
    query.executeUpdate();
    em.getTransaction().commit();
    em.close();
}

    
}
