package pub.androidrubick.autotest.core.util

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

@SuppressWarnings("GroovyUnusedDeclaration")
public class CmdResultUtils {

    private CmdResultUtils() {}

    public static String filterOneLineValue(String result) {
        return nonEmptyLines(result)?.get(0)
    }

    public static List<String> nonEmptyLines(String result) {
        return result.split(System.lineSeparator() ?: '\n').findAll { line ->
            !isEmpty(line)
        }?.asList()
    }

    public static List<String> nonEmptyValuesOfLine(String line) {
        return line.split(/[\s]+/).findAll { n ->
            !isEmpty(n)
        }
    }

    public static List<Integer> filterNumberValuesInLine(String line) {
        def ret = nonEmptyValuesOfLine(line)?.findAll { n ->
            try {
                Integer.parseInt(n)
                return true
            } catch (Throwable ignore) {
                return false
            }
        }?.collect { n ->
            Integer.parseInt(n)
        }
        return isEmpty(ret) ? [] : ret
    }

    public static String quotedStringArg(String arg) {
        return ['"', arg, '"'].join()
    }
}