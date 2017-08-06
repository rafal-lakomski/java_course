package rl.java.course.sandbox;

public class MyTest {

  public static void main(String[] args) {

    Square s = new Square(5);
    System.out.println("Powierzchnia kwadratu o boku " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,5);
    System.out.println("Powierzchnia prostokąta o boku " + r.a + " i " + r.b + " = " + r.area());
  }

}