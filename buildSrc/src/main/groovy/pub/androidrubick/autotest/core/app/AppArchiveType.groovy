package pub.androidrubick.autotest.core.app

import android.support.annotation.NonNull

/**
 * 定义应用包类型；
 *
 * 如安卓，包含应用程序包和测试程序包；
 *
 * iOS，包含ipa应用程序包
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AppArchiveType {

    @NonNull
    public static List<AppArchiveType> getAllAvailable() {
        return [AndroidApp, AndroidTestApp, IOSApp]
    }

    @NonNull
    public static List<AppArchiveType> allAvailableOf(String platform) {
        return allAvailable.findAll { AppArchiveType type ->
            type.name?.toLowerCase()?.contains(platform?.toLowerCase() ?: '')
        }
    }

    public static final AndroidApp = new AppArchiveType('AndroidApp', 'apk')
    public static final AndroidTestApp = new AppArchiveType('AndroidTestApp', 'apk')
    public static final IOSApp = new AppArchiveType('IOSApp', 'ipa')

    private final String mName
    private final String mFileExtension
    private AppArchiveType(@NonNull String name, @NonNull String fileExtension) {
        mName = name
        mFileExtension = fileExtension
    }

    @NonNull
    public String getName() {
        return mName
    }

    @NonNull
    public String getFileExtension() {
        return mFileExtension
    }

    @Override
    public String toString() {
        return mName
    }
}