package rl.java.course.addressbook.tests;

import org.testng.Assert;
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
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData().withName("Rafal").withLastName("Lakomski"));
    }
  }

  @Test
  public void testContactsModification() {
    Contacts before = app.contact().all();
    ContactData modifedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifedContact.getId()).withName("Rafal").withLastName("Lakomski");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifedContact).withAdded(contact)));
  }

}
