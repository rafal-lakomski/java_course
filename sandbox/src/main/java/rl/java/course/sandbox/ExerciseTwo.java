package rl.java.course.sandbox;

public class ExerciseTwo {

  public static void main(String[] args) {

    Point p1 = new Point(2,5);
    Point p2 = new Point(5,9);

    System.out.println("Distance between Point1 (" + p1.x + ", " + p1.y + ") and Point2 ("
            + p2.x + ", " + p2.y + ") is " + p1.distance(p2) + ".");
    }

  }


