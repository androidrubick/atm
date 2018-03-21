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
public enum AppPlatform {

    Android,
    iOS,
    Unknown;

    public List<AppArchiveType> appArchiveTypes() {
        return AppArchiveType.allAvailableOf(this)
    }
}