package rl.java.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import rl.java.course.addressbook.model.Contacts;
import rl.java.course.addressbook.model.GroupData;
import rl.java.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreatingWithGroup extends TestBase {

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
  public void testContactWithGroupCreating() {
    Contacts contactsAtAll = app.db().contacts();
    ContactData contact = contactsAtAll.iterator().next();
    Groups groupsInContacts = contact.getGroups();
    Groups groupsAtAll = app.db().groups();
    GroupData assosiateGroup;
    if (groupsInContacts.size() == groupsAtAll.size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("AssosiateGroupName"));
      Groups groupsAddNew = app.db().groups();
      groupsAddNew.remove(groupsAtAll);
      assosiateGroup = groupsAddNew.iterator().next();
    } else {
      groupsAtAll.removeAll(groupsInContacts);
      assosiateGroup = groupsAtAll.iterator().next();
    }
    app.goTo().contactPage();
    app.contact().addContactToGroup(contact, assosiateGroup);
    app.goTo().contactPage();
    assertThat(app.db().contacts(contact.getId()).getGroups(), equalTo(groupsInContacts.withAdded(assosiateGroup)));


  }
}
