package pub.androidrubick.autotest.android.util

import android.support.annotation.NonNull
import android.util.Log
import pub.androidrubick.autotest.android.middle.AndroidLog
import pub.androidrubick.autotest.core.util.ATMLog
import pub.androidrubick.autotest.core.util.ATMLogLevel

/**
 * <p>
 * Created by Yin Yong on 2018/3/15.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["unused", "WeakerAccess"])
public class ATAndroidLog extends AndroidLog {

    public static void use(ATMLog logger) {
        setImpl(new ATAndroidLog(logger));
    }

    private final ATMLog mLogger;
    public ATAndroidLog(ATMLog logger) {
        mLogger = logger;
    }

    @Override
    public void printLog(int level, String tag, String msg) {
        mLogger.log(parseLevel(level), "[$tag] $msg");
    }

    @Override
    public void printLog(int level, String tag, String msg, @NonNull Throwable tr) {
        mLogger.log(parseLevel(level), "[$tag] $msg");
        mLogger.log(parseLevel(level), Log.getStackTraceString(tr));
    }

    public static ATMLogLevel parseLevel(int level) {
        return ATMLogLevel.parse(toLevelString(level, "debug")) ?: ATMLogLevel.Debug;
    }
}
