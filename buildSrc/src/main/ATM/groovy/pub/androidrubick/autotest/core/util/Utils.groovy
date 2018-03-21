package pub.androidrubick.autotest.core.util

import java.lang.reflect.Array

@SuppressWarnings("GroovyUnusedDeclaration")
public class Utils {

    private Utils() {}

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true
        }
        if (obj instanceof CharSequence && obj.length() == 0) {
            return true
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true
        }
        if (obj instanceof Collection && obj.isEmpty()) {
            return true
        }
        if (obj instanceof Map && obj.isEmpty()) {
            return true
        }
        if (obj instanceof Iterator && !obj.hasNext()) {
            return true
        }
        if (obj instanceof Iterable && !obj.iterator().hasNext()) {
            return true
        }
        return false
    }

    public static String randomStr(int len = 5) {
        def alphabet = (('A'..'Z') + ('0'..'9')).join()
        new Random().with {
            (1..len).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }

    /**
     * <p>Capitalizes a String changing the first character to title case as
     * per {@link Character#toTitleCase(int)}. No other characters are changed.</p>
     *
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * StringUtils.capitalize("'cat'") = "'cat'"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return the capitalized String, {@code null} if null String input
     * @since 2.0
     */
    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final int firstCodepoint = str.codePointAt(0);
        final int newCodePoint = Character.toTitleCase(firstCodepoint);
        if (firstCodepoint == newCodePoint) {
            // already capitalized
            return str;
        }

        def newCodePoints = []; // cannot be longer than the char array
        int outOffset = 0;
        newCodePoints[outOffset++] = newCodePoint; // copy the first codepoint
        for (int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; ) {
            final int codepoint = str.codePointAt(inOffset);
            newCodePoints[outOffset++] = codepoint; // copy the remaining ones
            inOffset += Character.charCount(codepoint);
        }
        return new String(newCodePoints as int[], 0, outOffset);
    }
}