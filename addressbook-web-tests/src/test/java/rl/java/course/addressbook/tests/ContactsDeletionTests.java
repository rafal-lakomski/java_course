package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;

public class ContactsDeletionTests extends TestBase {

  @Test
  public void contactsDeletionTests() {
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().acceptAlert();
    app.getNavigationHelper().goToHomePage();
  }

}
