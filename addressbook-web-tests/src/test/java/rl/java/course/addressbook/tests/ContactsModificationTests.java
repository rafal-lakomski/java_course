package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase {

  @Test
  public void testContactsModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "LastName1", "TestQA",
              "Address1", "555-555-555", "test@test.com", "testqa1"), true);
    }
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().editFirstContact();
    app.getContactHelper().fillContactForm(
            new ContactData("NameQA1", "LastNameQA1", "TestQA1", "AddressQA1",
                    "444-333-222", "testqa1@test.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }

}
