package pub.androidrubick.autotest.core.util

@SuppressWarnings("GroovyUnusedDeclaration")
enum ATMLogLevel {

    Debug,
    Info,
    Warn,
    Error;

    /**
     * 如果<code>level</code>大于等于当前等级，则可以打印日志
     * @param level 是否支持该等级的日志
     * @return
     */
    public boolean isEnabled(ATMLogLevel level) {
        return level >= this
    }

    @SuppressWarnings("UnnecessaryQualifiedReference")
    public static ATMLogLevel parse(String name) {
        return ATMLogLevel.values().find { ATMLogLevel level ->
            level.name().equalsIgnoreCase(name)
        }
    }
}