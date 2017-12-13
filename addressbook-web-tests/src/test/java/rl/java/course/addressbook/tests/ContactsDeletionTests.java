package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import java.util.List;

public class ContactsDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().createContact(new ContactData("Rafal", "Lakomski",
              null,null,null,null,null));
    }
  }

  @Test
  public void contactsDeletionTests() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
