package rl.java.course.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import rl.java.course.addressbook.model.ContactData;
import rl.java.course.addressbook.model.Contacts;
import rl.java.course.addressbook.model.GroupData;
import rl.java.course.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupDate").list();
    for (GroupData groups : result) {
      System.out.println(groups);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);

  }

  public GroupData groups(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from GroupDate where group_id = " + id;
    System.out.println(request);
    List<GroupData> resultGroup = session.createQuery(request).list();
    GroupData result = resultGroup.iterator().next();
    session.getTransaction().commit();
    session.close();
    return result;

  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactDate where deprecated = '0000-00-00'").list();
    for (ContactData contacts : result) {
      System.out.println(contacts);
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);

  }

  public ContactData contacts(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String request = "from ContactDate where id = " + id;
    List<ContactData> resultContact = session.createQuery(request).list();
    ContactData result = resultContact.iterator().next();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}
