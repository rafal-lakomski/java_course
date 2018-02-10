package rl.java.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import rl.java.course.addressbook.model.Contacts;
import rl.java.course.addressbook.model.GroupData;
import rl.java.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionWithGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("TestGroup1");
        app.group().create(group);
        assertThat(app.db().groups().size(), equalTo(before.size() + 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream()
                .mapToInt(g -> g.getId()).max().getAsInt()))));
      }
      app.goTo().contactPage();
      Contacts before = app.db().contacts();
      ContactData contact = new ContactData()
              .withName("Kamil").withLastName("Stoch").withAddress("Test st")
              .withHomePhone("212555589321").withEmail("test1@test.com");
      app.contact().createContact(contact);
      app.goTo().contactPage();
      assertThat(app.db().contacts().size(), equalTo(before.size() + 1));
      Contacts after = app.db().contacts();
      assertThat(after,
              equalTo(before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testContactWithGroupDeletion() {
    GroupData group = app.db().groups().iterator().next();
    boolean isContactWithGroup = true;
    for (GroupData groupDate : app.db().groups()) {
      if (groupDate.getContacts().size() > 0) {
        group = groupDate;
        isContactWithGroup = false;
        break;
      }
    }
    if (isContactWithGroup) {
      ContactData contact = app.db().contacts().iterator().next();
      app.goTo().contactPage();
      app.contact().addContactToGroup(contact, group);
      app.goTo().contactPage();
      assertThat(app.db().groups(group.getId()), equalTo(group.getContacts().withAdded(contact)));
    }
    Contacts before = app.db().groups(group.getId()).getContacts();
    ContactData assosiateContact = app.db().groups(group.getId()).getContacts().iterator().next();
    app.goTo().contactPage();
    app.contact().deleteContactFromGroup(group, assosiateContact);
    app.goTo().contactPage();
    Contacts after = app.db().groups(group.getId()).getContacts();
    assertThat(after, equalTo(before.without(assosiateContact)));


  }
}