package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactsDeletionTests extends TestBase {

  @Test
  public void contactsDeletionTests() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "LastName1", "TestQA", "Address1",
              "555-555-555", "test@test.com", "testqa1"), true);
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
  }

}
