package rl.java.course.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.GroupData;

import java.util.List;

public class HbConnectionTest {


  private SessionFactory sessionFactory;

  @BeforeMethod
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void connectionHbTest() {


    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupDate").list();
    for (GroupData group : result) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();


  }
}
