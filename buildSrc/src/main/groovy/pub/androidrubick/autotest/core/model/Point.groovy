package pub.androidrubick.autotest.core.model

import android.support.annotation.NonNull

@SuppressWarnings(["GroovyUnusedDeclaration", "UnnecessaryQualifiedReference"])
public class Point {
    public int x
    public int y
    public Point() {

    }
    public Point(x, y) {
        this.set(x, y)
    }

    public Point set(x, y) {
        this.x = x as int
        this.y = y as int
        return this
    }

    public String toString() {
        return "Point [$x, $y]".toString()
    }

    public static Point of(x, y) {
        return new Point(x, y)
    }

    public Point plus(@NonNull Point other) {
        return Point.of(this.x + other.x, this.y + other.y)
    }

    public Point minus(@NonNull Point other) {
        return Point.of(this.x - other.x, this.y - other.y)
    }

    public Point negative() {
        return Point.of(-this.x, -this.y)
    }
}
