package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static EntityManagerFactory globalEmf = null;

    private EntityManagerFactorySingleton() {
    }

    public static EntityManagerFactory getInstance() {
        if (globalEmf == null) {

            globalEmf = Persistence.createEntityManagerFactory("PUGreenPepper");

        }
        return globalEmf;
    }
}
