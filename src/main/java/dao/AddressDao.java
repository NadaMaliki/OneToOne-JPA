package dao;

import jakarta.persistence.EntityManager;
import models.Address;

import java.util.List;

public class AddressDao {
    public void add_address(Address adr) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(adr);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void removeStudent(Address adr) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            Address managedAddress = em.contains(adr) ? adr : em.merge(adr);
            em.remove(managedAddress);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void updateAddress(Address adr) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(adr);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Address getAddress(int id) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            return em.find(Address.class, id);
        } finally {
            em.close();
        }
    }

    public List<Address> getAllStudents() {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            return em.createQuery("select a from Address a", Address.class).getResultList();
        } finally {
            em.close();
        }
    }

}
