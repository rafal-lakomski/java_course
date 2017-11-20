package rl.java.course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rl.java.course.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  @BeforeMethod
public void setUp() throws Exception {
        app.init();
        }

@AfterMethod
public void tearDown() {
        app.stop();
        }

  public void waitAWhile() {
    try {
      wait(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
        }
