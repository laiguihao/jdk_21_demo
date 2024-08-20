package org.example.jdk_21_demo;




public class SwitchPatternDemo {
    public static void main(String[] args) {
        Object shape = new Circle(5.0);

        String result = switch (shape) {
            case Circle(double r) -> "Circle with radius: " + r;
            case Rectangle(double l, double w) -> "Rectangle with length: " + l + " and width: " + w;
            default -> "Unknown shape";
        };

        System.out.println(result);
    }
    public record Circle(double radius) {}
    public record Rectangle(double length, double width) {}


}





