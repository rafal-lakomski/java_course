package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import rl.java.course.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;


public class ContactsCreationTests extends TestBase {

  @Test
  public void contactsCreationTests() {
    app.goTo().contactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/java.png");
    ContactData contact = new ContactData().withName("Rafal").withLastName("Lakomski").withPhoto(photo);
    app.contact().createContact(contact);
    app.goTo().contactPage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/Everest.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

  }


}
