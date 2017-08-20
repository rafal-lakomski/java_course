package rl.java.course.addressbook.tests;

import org.testng.annotations.Test;
import rl.java.course.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {


  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("testqa1", "testqa2", "testqa3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }

}