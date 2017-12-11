package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactsCreationTests extends TestBase {

  @Test
  public void contactsCreationTests() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Rafal", "Lakomski", null,
                            null, null, null, null);
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
