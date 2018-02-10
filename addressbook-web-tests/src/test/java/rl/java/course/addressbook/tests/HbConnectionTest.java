package rl.java.course.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
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
      return;
    }
  }

  @Test
  public void testHbConnectionGroups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupDate").list();
    for (GroupData groups : result) {
      System.out.println(groups);
      System.out.println(groups.getContacts());
    }
    session.getTransaction().commit();
    session.close();
  }

  @Test
  public void testHbConnectionContacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactDate where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    for (ContactData contact : result) {
      System.out.println(contact);
      System.out.println(contact.getGroups());
    }
  }

}
