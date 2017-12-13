package rl.java.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rl.java.course.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.xpath("//div[@id='nav']//a[.='add new']"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastName());
  }

  public void submitContact() {
    click(By.name("submit"));
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void selectFirstContact() {
    click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[1]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//*[@value='Delete']"));
  }

  public void editFirstContact() {
    click(By.xpath(".//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContact();
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("entry"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String name = cells.get(1).getText();
      String lastName = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, lastName, name, null,
              null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  public void modifyContact(int index, ContactData contact) {
    goToHomePage();
    editFirstContact();
    fillContactForm(contact);
    submitContactModification();
    goToHomePage();
  }

}
