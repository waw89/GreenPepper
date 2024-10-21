/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author waw
 */
public class OrderDAO implements Serializable {

      public OrderDAO() {
        
    }

    public EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public Order create(Order order) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return order;
    }

    public Order edit(Order order) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            order = em.merge(order);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = order.getOrderNumber();
                if (findOrder(id) == null) {
                    throw new NonexistentEntityException("The order with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return order;
    }



    public List<Order> findOrderEntities() {
        return findOrderEntities(true, -1, -1);
    }

    public List<Order> findOrderEntities(int maxResults, int firstResult) {
        return findOrderEntities(false, maxResults, firstResult);
    }

    private List<Order> findOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order.class));
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

    public Order findOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order> rt = cq.from(Order.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Order>findActiveOrders(){
      EntityManager em = getEntityManager();
      String jpql = "Select o FROM Order o WHERE o.ORDER_STATE = :ORDER_STATE ORDER BY o.creationDate DESC";
      TypedQuery<Order> query = em.createQuery(jpql, Order.class);
      query.setParameter("ORDER_STATE", ORDER_STATE.ACTIVE);
      return query.getResultList();
    }
    
     public List<Order>findCanceledPaidOrders(){
      EntityManager em = getEntityManager();
      String jpql = "Select o FROM Order o WHERE o.ORDER_STATE IN :ORDER_STATE ORDER BY o.creationDate DESC";
      TypedQuery<Order> query = em.createQuery(jpql, Order.class);
      query.setParameter("ORDER_STATE", Arrays.asList(ORDER_STATE.PAID, ORDER_STATE.CANCELLED));
      return query.getResultList();
    }
     

}
