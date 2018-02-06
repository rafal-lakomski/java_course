package rl.java.course.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rl.java.course.addressbook.model.GroupData;
import rl.java.course.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }

    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> goups = (List<GroupData>) xStream.fromXML(xml);
    return goups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroups")
  public void GroupCreationTest(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void GroupBadCreationTest() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before));
  }

}
