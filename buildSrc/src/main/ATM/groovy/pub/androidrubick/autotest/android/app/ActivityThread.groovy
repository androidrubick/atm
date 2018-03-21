package pub.androidrubick.autotest.android.app

@SuppressWarnings("GroovyUnusedDeclaration")
public class ActivityThread {

    private static ActivityThread sCurrentActivityThread
    public static ActivityThread getCurrentActivityThread() {
        return sCurrentActivityThread
    }

    public static void attach() {
        sCurrentActivityThread = new ActivityThread()
    }

}