package rl.java.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import rl.java.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0) {
      app.contact().createContact(new ContactData().withName("Rafal").withLastName("Lakomski")
              .withAddress("Central Park West 72nd St").withHomePhone("601905851").withEmail("test@test.com"));
    }
  }

  @Test
  public void testContactsModification() {
    Contacts before = app.db().contacts();
    ContactData modifedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifedContact.getId()).withName("Kamil").withLastName("Stoch")
            .withAddress("192 5th Av.").withHomePhone("21255502130").withEmail("test1@test.com");
    app.goTo().contactPage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifedContact).withAdded(contact)));
  }

}
