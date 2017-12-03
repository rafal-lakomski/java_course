package rl.java.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {


  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("testqa1", "testqa2", "testqa3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
//    Assert.assertEquals(after, before);
  }

}