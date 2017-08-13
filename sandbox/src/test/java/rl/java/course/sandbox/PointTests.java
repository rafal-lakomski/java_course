package rl.java.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void pointsDistanceTrue() {
    Point p1 = new Point(2,5);
    Point p2 = new Point(5,9);
    Assert.assertEquals(p1.distance(p2),5.0);
  }

  @Test
  public void pointsDistanceFalse() {
    Point p1 = new Point(4,3);
    Point p2 = new Point(7,2);
    Assert.assertNotEquals(p1.distance(p2),5.0);
  }

}
