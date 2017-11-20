package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

public class ContactsDeletionTests extends TestBase {

  @Test
  public void contactsDeletionTests() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "LastName1", "TestQA", "Address1",
              "555-555-555", "test@test.com", "testqa1"), true);
    }
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().goToHomePage();
  }

}
