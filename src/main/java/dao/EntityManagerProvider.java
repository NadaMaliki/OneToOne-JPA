package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManagerProvider instance;
    private EntityManagerFactory emf;

    private EntityManagerProvider() {
        try {
            emf = Persistence.createEntityManagerFactory("Student");
        } catch (Exception e) {
            System.err.println("Error initializing EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static synchronized EntityManagerProvider getInstance() {
        if (instance == null) {
            instance = new EntityManagerProvider();
        }
        return instance;
    }

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}