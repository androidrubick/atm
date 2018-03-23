package pub.androidrubick.autotest.core.util

@SuppressWarnings(["GroovyUnusedDeclaration", "UnnecessaryQualifiedReference"])
public interface ATMLoggable {

    public abstract void log(Object msg)
    public abstract void logD(Object msg)
    public abstract void logI(Object msg)
    public abstract void logW(Object msg)
    public abstract void logE(Object msg)

    public abstract void setLogLevel(ATMLogLevel level)
    public abstract ATMLogLevel getLogLevel()

}