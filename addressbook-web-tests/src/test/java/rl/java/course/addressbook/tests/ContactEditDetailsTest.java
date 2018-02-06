package rl.java.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEditDetailsTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData().withName("Rafal").withLastName("Lakomski"));
    }
  }

  @Test (enabled = false)
  public void testContactPhone() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().getInfoFromEditform(contact);

    assertThat(contact.getAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }
  @Test
  public void testContactDeteils() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactDateFromDetailsForm = app.contact().getInfoFromDeteilsForm(contact);
    ContactData contactInfoFromEditForm = convertContactToEqualsViewFormat(app.contact().getInfoFromEditform(contact));
    assertThat(contactDateFromDetailsForm.getAllDetails(), equalTo(mergeDateDeteils(contactInfoFromEditForm)));




  }
  private String mergeDateDeteils(ContactData contact) {
    return Arrays.asList(contact.getName(), contact.getLastName(), contact.getAddress(),
            contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
            contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> s != null && !s.equals(""))
            .map(ContactEditDetailsTest::cleanedEmails)
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactEditDetailsTest::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  private static String cleanedAddress(String address) {
    return address.replaceAll("(\\\\r|\\\\n)+", "");
  }

  public static String cleanedPhones(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }

  private static String cleanedEmails(String email) {
    return email.replaceAll("\\s", "");
  }

  private static String cleanedDeteils(String deteil) {
    return deteil.replaceAll("\\n", "");
  }

  public static ContactData convertContactToEqualsViewFormat(ContactData contact) {
    String firsname = contact.getName();
    String lastname = contact.getLastName();
    String home = contact.getHomePhone();
    String mobile = contact.getMobilePhone();
    String work = contact.getWorkPhone();
    String address = contact.getAddress();

    if (!firsname.equals(firsname)) {
      firsname += "\\n";
    }
    if (!lastname.equals("")) {
      lastname += "\\n";
    }

    if (!address.equals("")) {
      address += "\\n";
    }

    if (!home.equals("")) {
      home = "H: " + home;
    }

    if (!mobile.equals("")) {
      mobile = "M: " + mobile;
    }
    if (!work.equals("")) {
      work = "W: " + work + "\n";
    }

    return contact.withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address);
  }


}
