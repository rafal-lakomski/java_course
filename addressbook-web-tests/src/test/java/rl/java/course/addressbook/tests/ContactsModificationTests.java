package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactsModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Rafal", "Lakomski", null,
              null, null, null, null));
    }
  }

  @Test
  public void testContactsModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Rafal", "Lakomski", null,
            null, null, null, null);
    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

