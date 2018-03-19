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
}