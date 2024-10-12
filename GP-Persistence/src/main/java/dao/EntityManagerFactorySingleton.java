package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
