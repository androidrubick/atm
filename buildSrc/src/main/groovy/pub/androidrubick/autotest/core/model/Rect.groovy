package pub.androidrubick.autotest.core.model

import android.support.annotation.CheckResult
import android.support.annotation.NonNull
import android.support.annotation.Nullable

@SuppressWarnings(["GroovyUnusedDeclaration", "UnnecessaryQualifiedReference"])
public class Rect {

    public static Rect of(left, top, right, bottom) {
        return new Rect(left, top, right, bottom)
    }

    public int left = 0
    public int top = 0
    public int right = 0
    public int bottom = 0

    public Rect(){

    }

    public Rect(left, top, right, bottom) {
        this.set(left, top, right, bottom)
    }

    /**
     * Create a new rectangle, initialized with the values in the specified
     * rectangle (which is left unmodified).
     *
     * @param r The rectangle whose coordinates are copied into the new
     *          rectangle.
     */
    public Rect(@Nullable Rect r) {
        if (r == null) {
            setEmpty()
        } else {
            this.set(r)
        }
    }

    public final int centerX() {
        return (left + right) >> 1
    }

    public final int centerY() {
        return (top + bottom) >> 1
    }

    /**
     * Returns true if the rectangle is empty (left >= right or top >= bottom)
     */
    public final boolean isEmpty() {
        return left >= right || top >= bottom
    }

    /**
     * @return the rectangle's width. This does not check for a valid rectangle
     * (i.e. left <= right) so the result may be negative.
     */
    public final int width() {
        return right - left
    }

    /**
     * @return the rectangle's height. This does not check for a valid rectangle
     * (i.e. top <= bottom) so the result may be negative.
     */
    public final int height() {
        return bottom - top
    }

    public final Point center() {
        return Point.of(centerX(), centerY())
    }

    /**
     * Set the rectangle to (0,0,0,0)
     */
    public void setEmpty() {
        left = right = top = bottom = 0
    }

    public Rect set(left, top, right, bottom) {
        this.left   = left   as int
        this.top    = top    as int
        this.right  = right  as int
        this.bottom = bottom as int
        return this
    }

    /**
     * Copy the coordinates from src into this rectangle.
     *
     * @param src The rectangle whose coordinates are copied into this
     *           rectangle.
     */
    public void set(Rect src) {
        this.left   = src.left
        this.top    = src.top
        this.right  = src.right
        this.bottom = src.bottom
    }

    /**
     * Offset the rectangle by adding dx to its left and right coordinates, and
     * adding dy to its top and bottom coordinates.
     *
     * @param dx The amount to add to the rectangle's left and right coordinates
     * @param dy The amount to add to the rectangle's top and bottom coordinates
     */
    public void offset(int dx, int dy) {
        left   += dx
        top    += dy
        right  += dx
        bottom += dy
    }

    /**
     * Offset the rectangle to a specific (left, top) position,
     * keeping its width and height the same.
     *
     * @param newLeft   The new "left" coordinate for the rectangle
     * @param newTop    The new "top" coordinate for the rectangle
     */
    public void offsetTo(int newLeft, int newTop) {
        right += newLeft - left
        bottom += newTop - top
        left = newLeft
        top = newTop
    }

    /**
     * Inset the rectangle by (dx,dy). If dx is positive, then the sides are
     * moved inwards, making the rectangle narrower. If dx is negative, then the
     * sides are moved outwards, making the rectangle wider. The same holds true
     * for dy and the top and bottom.
     *
     * @param dx The amount to add(subtract) from the rectangle's left(right)
     * @param dy The amount to add(subtract) from the rectangle's top(bottom)
     */
    public void inset(dx, dy) {
        left   += dx as int
        top    += dy as int
        right  -= dx as int
        bottom -= dy as int
    }

    /**
     * Insets the rectangle on all sides specified by the insets.
     * @hide
     * @param left The amount to add from the rectangle's left
     * @param top The amount to add from the rectangle's top
     * @param right The amount to subtract from the rectangle's right
     * @param bottom The amount to subtract from the rectangle's bottom
     */
    public void inset(left, top, right, bottom) {
        this.left   += left as int
        this.top    += top as int
        this.right  -= right as int
        this.bottom -= bottom as int
    }

    /**
     * Insets the rectangle on all sides specified by the dimensions of the {@code insets}
     * rectangle.
     * @hide
     * @param insets The rectangle specifying the insets on all side.
     */
    public void inset(@NonNull Rect insets) {
        left   += insets.left
        top    += insets.top
        right  -= insets.right
        bottom -= insets.bottom
    }

    /**
     * Returns true if (x,y) is inside the rectangle. The left and top are
     * considered to be inside, while the right and bottom are not. This means
     * that for a x,y to be contained: left <= x < right and top <= y < bottom.
     * An empty rectangle never contains any point.
     *
     * @param x The X coordinate of the point being tested for containment
     * @param y The Y coordinate of the point being tested for containment
     * @return true iff (x,y) are contained by the rectangle, where containment
     *              means left <= x < right and top <= y < bottom
     */
    public boolean contains(x, y) {
        int xVal = x as int
        int yVal = y as int
        // check for empty first
        return (left < right && top < bottom) &&
                (xVal >= left && xVal < right && yVal >= top && yVal < bottom)
    }

    /**
     * Returns true if Point(x,y) is inside the rectangle. The left and top are
     * considered to be inside, while the right and bottom are not. This means
     * that for a x,y to be contained: left <= x < right and top <= y < bottom.
     * An empty rectangle never contains any point.
     *
     * @param point the point being tested for containment
     * @return true iff Point(x,y) are contained by the rectangle, where containment
     *              means left <= x < right and top <= y < bottom
     */
    public boolean contains(@NonNull Point point) {
        return this.contains(point.x, point.y)
    }

    /**
     * Returns true iff the 4 specified sides of a rectangle are inside or equal
     * to this rectangle. i.e. is this rectangle a superset of the specified
     * rectangle. An empty rectangle never contains another rectangle.
     *
     * @param left The left side of the rectangle being tested for containment
     * @param top The top of the rectangle being tested for containment
     * @param right The right side of the rectangle being tested for containment
     * @param bottom The bottom of the rectangle being tested for containment
     * @return true iff the the 4 specified sides of a rectangle are inside or
     *              equal to this rectangle
     */
    public boolean contains(left, top, right, bottom) {
        int lVal = left as int
        int tVal = top as int
        int rVal = right as int
        int bVal = bottom as int
        // check for empty first
        return (this.left < this.right && this.top < this.bottom) &&
                // now check for containment
                (this.left <= lVal && this.top <= tVal && this.right >= rVal && this.bottom >= bVal)
    }

    /**
     * Returns true iff the specified rectangle r is inside or equal to this
     * rectangle. An empty rectangle never contains another rectangle.
     *
     * @param r The rectangle being tested for containment.
     * @return true iff the specified rectangle r is inside or equal to this
     *              rectangle
     */
    public boolean contains(@NonNull Rect r) {
        return this.contains(r.left, r.top, r.right, r.bottom)
    }/**
     * If the rectangle specified by left,top,right,bottom intersects this
     * rectangle, return true and set this rectangle to that intersection,
     * otherwise return false and do not change this rectangle. No check is
     * performed to see if either rectangle is empty. Note: To just test for
     * intersection, use {@link #intersects(Rect, Rect)}.
     *
     * @param left The left side of the rectangle being intersected with this
     *             rectangle
     * @param top The top of the rectangle being intersected with this rectangle
     * @param right The right side of the rectangle being intersected with this
     *              rectangle.
     * @param bottom The bottom of the rectangle being intersected with this
     *             rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     *              (and this rectangle is then set to that intersection) else
     *              return false and do not change this rectangle.
     */
    @CheckResult
    public boolean intersect(int left, int top, int right, int bottom) {
        if (this.left < right && left < this.right && this.top < bottom && top < this.bottom) {
            if (this.left < left) this.left = left
            if (this.top < top) this.top = top
            if (this.right > right) this.right = right
            if (this.bottom > bottom) this.bottom = bottom
            return true
        }
        return false
    }

    /**
     * If the specified rectangle intersects this rectangle, return true and set
     * this rectangle to that intersection, otherwise return false and do not
     * change this rectangle. No check is performed to see if either rectangle
     * is empty. To just test for intersection, use intersects()
     *
     * @param r The rectangle being intersected with this rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     *              (and this rectangle is then set to that intersection) else
     *              return false and do not change this rectangle.
     */
    @CheckResult
    public boolean intersect(Rect r) {
        return intersect(r.left, r.top, r.right, r.bottom)
    }

    /**
     * If rectangles a and b intersect, return true and set this rectangle to
     * that intersection, otherwise return false and do not change this
     * rectangle. No check is performed to see if either rectangle is empty.
     * To just test for intersection, use intersects()
     *
     * @param a The first rectangle being intersected with
     * @param b The second rectangle being intersected with
     * @return true iff the two specified rectangles intersect. If they do, set
     *              this rectangle to that intersection. If they do not, return
     *              false and do not change this rectangle.
     */
    @CheckResult
    public boolean setIntersect(Rect a, Rect b) {
        if (a.left < b.right && b.left < a.right && a.top < b.bottom && b.top < a.bottom) {
            left = Math.max(a.left, b.left)
            top = Math.max(a.top, b.top)
            right = Math.min(a.right, b.right)
            bottom = Math.min(a.bottom, b.bottom)
            return true
        }
        return false
    }

    /**
     * Returns true if this rectangle intersects the specified rectangle.
     * In no event is this rectangle modified. No check is performed to see
     * if either rectangle is empty. To record the intersection, use intersect()
     * or setIntersect().
     *
     * @param left The left side of the rectangle being tested for intersection
     * @param top The top of the rectangle being tested for intersection
     * @param right The right side of the rectangle being tested for
     *              intersection
     * @param bottom The bottom of the rectangle being tested for intersection
     * @return true iff the specified rectangle intersects this rectangle. In
     *              no event is this rectangle modified.
     */
    public boolean intersects(int left, int top, int right, int bottom) {
        return this.left < right && left < this.right && this.top < bottom && top < this.bottom
    }

    public boolean intersects(@NonNull Rect r) {
        return this.intersects(r.left, r.top, r.right, r.bottom)
    }

    /**
     * Returns true iff the two specified rectangles intersect. In no event are
     * either of the rectangles modified. To record the intersection,
     * use {@link #intersect(Rect)} or {@link #setIntersect(Rect, Rect)}.
     *
     * @param a The first rectangle being tested for intersection
     * @param b The second rectangle being tested for intersection
     * @return true iff the two specified rectangles intersect. In no event are
     *              either of the rectangles modified.
     */
    public static boolean intersects(@NonNull Rect a, @NonNull Rect b) {
        return a.intersects(b)
    }

    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     *
     * @param left The left edge being unioned with this rectangle
     * @param top The top edge being unioned with this rectangle
     * @param right The right edge being unioned with this rectangle
     * @param bottom The bottom edge being unioned with this rectangle
     */
    public void union(int left, int top, int right, int bottom) {
        if ((left < right) && (top < bottom)) {
            if ((this.left < this.right) && (this.top < this.bottom)) {
                if (this.left > left) this.left = left
                if (this.top > top) this.top = top
                if (this.right < right) this.right = right
                if (this.bottom < bottom) this.bottom = bottom
            } else {
                this.left = left
                this.top = top
                this.right = right
                this.bottom = bottom
            }
        }
    }

    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     *
     * @param r The rectangle being unioned with this rectangle
     */
    public void union(@NonNull Rect r) {
        union(r.left, r.top, r.right, r.bottom)
    }

    public String toString() {
        return "Rect [$left, $top, $right, $bottom]".toString()
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false

        Rect r = (Rect) o
        return left == r.left && top == r.top && right == r.right && bottom == r.bottom
    }

    @Override
    public int hashCode() {
        int result = left
        result = 31 * result + top
        result = 31 * result + right
        result = 31 * result + bottom
        return result
    }

    @NonNull
    public Rect plus(@NonNull Rect rect) {
        Rect retRect = new Rect(this)
        retRect.union(rect)
        return retRect
    }

    @NonNull
    public Rect negative() {
        return Rect.of(-this.left, -this.top, -this.right, -this.bottom)
    }
}
