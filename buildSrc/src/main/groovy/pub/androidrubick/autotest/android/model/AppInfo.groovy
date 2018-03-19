package pub.androidrubick.autotest.android.model

/**
 * simple model with app information
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AppInfo {

    public String pkg
    public String versionName
    public String versionCode

    public LaunchInfo launchInfo

    public AppInfo() {}

    public AppInfo(String pkg, String versionName, String versionCode, LaunchInfo launchInfo = null) {
        this.pkg = pkg
        this.versionName = versionName
        this.versionCode = versionCode
        this.launchInfo = launchInfo
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "launchInfo=" + launchInfo +
                '}';
    }
}