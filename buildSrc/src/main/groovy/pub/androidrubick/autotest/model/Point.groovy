package pub.androidrubick.autotest.model

@SuppressWarnings("GroovyUnusedDeclaration")
public class Point {
    public int x
    public int y

    public Point set(x, y) {
        this.x = x as int
        this.y = y as int
        return this
    }

    public String toString() {
        return "Point [$x, $y]".toString()
    }

    public static Point of(x, y) {
        return new Point().set(x, y)
    }
}
