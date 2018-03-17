package pub.androidrubick.autotest.model

@SuppressWarnings("GroovyUnusedDeclaration")
public class Rect {
    public int left = 0
    public int top = 0
    public int right = 0
    public int bottom = 0

    public int centerX() {
        return (left + right) / 2
    }
    public int centerY() {
        return (top + bottom) / 2
    }

    public Point center() {
        return Point.of(centerX(), centerY())
    }

    public Rect set(left, top, right, bottom) {
        this.left = left as int
        this.top = top as int
        this.right = right as int
        this.bottom = bottom as int
        return this
    }

    public String toString() {
        return "Rect [$left, $top, $right, $bottom]".toString()
    }

    public static Rect of(left, top, right, bottom) {
        return new Rect().set(left, top, right, bottom)
    }
}
