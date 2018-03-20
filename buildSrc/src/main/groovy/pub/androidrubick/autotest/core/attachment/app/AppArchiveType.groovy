package pub.androidrubick.autotest.core.attachment.app

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
    public static List<AppArchiveType> allAvailableOf(AppPlatform platform) {
        return allAvailable.findAll { AppArchiveType type ->
            type.platform == platform
        }
    }

    public static final AndroidApp     = new AppArchiveType(AppPlatform.Android, 'AndroidApp',     'apk')
    public static final AndroidTestApp = new AppArchiveType(AppPlatform.Android, 'AndroidTestApp', 'apk')
    public static final IOSApp         = new AppArchiveType(AppPlatform.iOS,     'IOSApp',         'ipa')

    @NonNull
    public static List<AppArchiveType> getAllAvailable() {
        return [AndroidApp, AndroidTestApp, IOSApp]
    }

    private final AppPlatform mPlatform
    private final String mName
    private final String mFileExtension
    private AppArchiveType(@NonNull AppPlatform platform, @NonNull String name,
                           @NonNull String fileExtension) {
        mPlatform = platform
        mName = name
        mFileExtension = fileExtension
    }

    @NonNull
    public AppPlatform getPlatform() {
        return mPlatform
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