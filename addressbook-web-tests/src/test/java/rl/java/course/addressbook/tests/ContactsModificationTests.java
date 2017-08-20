package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase {

  @Test
  public void testContactsModification() {
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().editFirstContact();
    app.getContactHelper().fillContactForm(
            new ContactData("NameQA1", "LastNameQA1", "TestQA1", "AddressQA1",
                    "444-333-222", "testqa1@test.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }

}
