package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;


public class ContactsCreationTests extends TestBase {

  @Test
  public void contactsCreationTests() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(
            new ContactData("Name1", "LastName1", "TestQA", "Address1",
                    "555-555-555", "test@test.com","none"), true);
  }
}
