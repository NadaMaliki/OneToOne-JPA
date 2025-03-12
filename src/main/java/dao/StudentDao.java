package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import models.Address;
import models.Student;

import java.util.List;

public class StudentDao {

    public void add_Student(Student std) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(std);
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

    public void removeStudent(Student std) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            Student managedStudent = em.contains(std) ? std : em.merge(std);
            em.remove(managedStudent);
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

    public void updateStudent(Student std) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(std);
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

    public Student getStudent(int id) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public List<Student> getAllStudents() {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try {
            return em.createQuery("select u from Student u", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getStudentsByName (String name) {
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        Query q;
        try {
            q = em.createQuery("select u from Student u where u.name = :name", Student.class);
            q.setParameter("name", name);
            return q.getResultList();
        }finally {
            em.close();
        }
    }

    public List<Student> getStudentByNote (Double note) {
        Query q;
        EntityManager em = EntityManagerProvider.getInstance().createEntityManager();
        try{
            q = em.createQuery("select u from Student u where u.note > :note", Student.class);
            q.setParameter("note", note);
            return q.getResultList();
        }finally {
            em.close();
        }

    }
}