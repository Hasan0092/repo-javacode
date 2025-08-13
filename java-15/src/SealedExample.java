// ----- Sealed hierarchy (package-private types so they can live in one file) -----

import java.util.List;

/**
 * A sealed interface must list ALL its direct subtypes via `permits`.
 * All permitted subtypes must be in the same module (or package if unnamed).
 */
sealed interface Shape permits Circle, Rectangle, Triangle, Polygon {}

/** `final` subtype: hierarchy stops here. */
final class Circle implements Shape {
    final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override public String toString() { return "Circle(" + radius + ")"; }
}

/** `final` subtype: hierarchy stops here. */
final class Rectangle implements Shape {
    final double length, width;
    Rectangle(double length, double width) { this.length = length; this.width = width; }
    @Override public String toString() { return "Rectangle(" + length + "x" + width + ")"; }
}

/**
 * `sealed` subtype: continues the seal.
 * Must list its own permitted subclasses.
 */
sealed abstract class Triangle implements Shape permits Equilateral, Isosceles, Scalene {
    @Override public String toString() { return getClass().getSimpleName(); }
}

/** `final` subtype of Triangle. */
final class Equilateral extends Triangle {
    final double side;
    Equilateral(double side) { this.side = side; }
    @Override public String toString() { return "Equilateral(side=" + side + ")"; }
}

/**
 * `non-sealed` subtype: this re-opens the hierarchy below it.
 * ANY class can now extend Isosceles (no need to be listed in permits).
 */
non-sealed class Isosceles extends Triangle {
    final double equalSide, base;
    Isosceles(double equalSide, double base) { this.equalSide = equalSide; this.base = base; }
    @Override public String toString() { return "Isosceles(equal=" + equalSide + ", base=" + base + ")"; }
}

/** Another `final` subtype of Triangle. */
final class Scalene extends Triangle {
    final double a, b, c;
    Scalene(double a, double b, double c) {
        // Simple triangle inequality check (not exhaustive validation)
        if (a + b <= c || a + c <= b || b + c <= a) throw new IllegalArgumentException("Invalid sides");
        this.a = a; this.b = b; this.c = c;
    }
    @Override public String toString() { return "Scalene(" + a + "," + b + "," + c + ")"; }
}

/**
 * `non-sealed` type directly under Shape: open for extension.
 * This shows that a sealed root can still allow an "open" branch.
 */
non-sealed class Polygon implements Shape {
    final int sides;
    Polygon(int sides) { this.sides = sides; }
    @Override public String toString() { return "Polygon(" + sides + " sides)"; }
}

/** Anyone can extend Polygon (because it's non-sealed). */
class CustomPolygon extends Polygon {
    CustomPolygon(int sides) { super(sides); }
    @Override public String toString() { return "CustomPolygon(" + sides + " sides)"; }
}


// ----- Demo / utilities -----

public class SealedExample {

    /** Pattern matching for instanceof (Java 17) to calculate area where we can. */
    static double area(Shape s) {
        if (s instanceof Circle c) {
            return Math.PI * c.radius * c.radius;
        } else if (s instanceof Rectangle r) {
            return r.length * r.width;
        } else if (s instanceof Equilateral e) {
            return Math.sqrt(3) / 4.0 * e.side * e.side;
        } else if (s instanceof Isosceles iso) {
            // height = sqrt(equalSide^2 - (base/2)^2)
            double h = Math.sqrt(iso.equalSide * iso.equalSide - (iso.base * iso.base) / 4.0);
            return iso.base * h / 2.0;
        } else if (s instanceof Scalene sc) {
            // Heron's formula
            double p = (sc.a + sc.b + sc.c) / 2.0;
            return Math.sqrt(p * (p - sc.a) * (p - sc.b) * (p - sc.c));
        } else if (s instanceof Polygon p) {
            // General polygon area needs coordinates; we don’t have enough info.
            throw new UnsupportedOperationException("Area unknown for general polygons: " + p);
        }
        throw new IllegalStateException("Unhandled shape: " + s.getClass());
    }

    /** Human-friendly explanation using pattern variables. */
    static String describe(Shape s) {
        if (s instanceof Circle c)            return "A circle with radius " + c.radius;
        if (s instanceof Rectangle r)         return "A rectangle " + r.length + " by " + r.width;
        if (s instanceof Equilateral e)       return "An equilateral triangle of side " + e.side;
        if (s instanceof Isosceles iso)       return "An isosceles triangle (equal=" + iso.equalSide + ", base=" + iso.base + ")";
        if (s instanceof Scalene sc)          return "A scalene triangle (" + sc.a + "," + sc.b + "," + sc.c + ")";
        if (s instanceof Polygon p)           return "A polygon with " + p.sides + " sides";
        return "Unknown shape";
    }

    public static void main(String[] args) {
        var shapes = List.of(
                new Circle(5),
                new Rectangle(2, 3),
                new Equilateral(3),
                new Isosceles(5, 6),
                new Scalene(3, 4, 5),
                new CustomPolygon(5)
        );

        for (Shape s : shapes) {
            System.out.println("→ " + describe(s));
            try {
                System.out.println("   area = " + area(s));
            } catch (UnsupportedOperationException ex) {
                System.out.println("   area = (not supported): " + ex.getMessage());
            }
        }

        // ---- Compile-time enforcement demo (uncomment to see the error) ----
        // class RogueSquare implements Shape {}  // ❌ ERROR:
        // "RogueSquare is not allowed in the sealed hierarchy" because Shape permits only
        // Circle, Rectangle, Triangle, Polygon.
    }
}
