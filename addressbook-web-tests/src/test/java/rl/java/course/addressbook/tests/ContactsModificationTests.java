package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactsModificationTests extends TestBase {

  @Test
  public void testContactsModification() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Rafal", "Lakomski", null,
              null, null, null, null));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Rafal", "Lakomski", null,
            null, null, null, null);
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}

