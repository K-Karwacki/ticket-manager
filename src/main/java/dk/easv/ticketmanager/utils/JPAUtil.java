package dk.easv.ticketmanager.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil
{
  private static final EntityManagerFactory entityManagerFactory =
      Persistence.createEntityManagerFactory("MSSQL_PU");

  private JPAUtil(){}

  public static EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

}
