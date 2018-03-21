package pub.androidrubick.autotest.android.middle;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class AndroidLog {

    private static AndroidLog sImpl = new AndroidLog() {
        @Override
        public void printLog(int level, String tag, String msg) {
            System.out.println(String.format("%s/%s$ %s", toLevelString(level, "DEBUG").charAt(0), tag, msg));
        }

        @Override
        public void printLog(int level, String tag, String msg, @NonNull Throwable tr) {
            printLog(level, tag, msg);
            printLog(Log.ERROR, tag, Log.getStackTraceString(tr));
//            tr.printStackTrace();
        }
    };

    public static void setImpl(@NonNull AndroidLog impl) {
        sImpl = impl;
    }

    public static AndroidLog getImpl() {
        return sImpl;
    }

    public abstract void printLog(int level, String tag, String msg) ;

    public abstract void printLog(int level, String tag, String msg, @NonNull Throwable tr) ;

    private static final Map<Integer, String> sLevelNameMap;
    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(Log.VERBOSE, "VERBOSE");
        map.put(  Log.DEBUG, "DEBUG");
        map.put(   Log.INFO, "INFO");
        map.put(   Log.WARN, "WARN");
        map.put(  Log.ERROR, "ERROR");
        map.put( Log.ASSERT, "ASSERT");
        sLevelNameMap = Collections.unmodifiableMap(map);
    }

    public static String toLevelString(int level) {
        return sLevelNameMap.get(level);
    }

    public static String toLevelString(int level, String defLevelString) {
        String levelString;
        return (levelString = sLevelNameMap.get(level)) == null ? defLevelString : levelString;
    }
}
