package pub.androidrubick.autotest.android.model

@SuppressWarnings("GroovyUnusedDeclaration")
public class LaunchInfo {

    public final String pkg
    public final String mainActivity

    public LaunchInfo(String pkg, String mainActivity) {
        this.pkg = pkg
        this.mainActivity = mainActivity
    }

    @Override
    public String toString() {
        return "LaunchInfo{" +
                "pkg='" + pkg + '\'' +
                ", mainActivity='" + mainActivity + '\'' +
                '}';
    }
}