package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactsCreationTests extends TestBase {

  @Test
  public void contactsCreationTests() {
    app.goTo().contactPage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Rafal", "Lakomski", null,
                            null, null, null, null);
    app.contact().createContact(contact);
    app.goTo().contactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
