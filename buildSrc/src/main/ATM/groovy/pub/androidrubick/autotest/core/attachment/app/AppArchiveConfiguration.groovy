package pub.androidrubick.autotest.core.attachment.app

import android.support.annotation.Nullable
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

/**
 * 定义应用包管理；
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
public class AppArchiveConfiguration extends BaseAttachment {

    private AppArchiveInfo mAppArchiveInfo
    public AppArchiveConfiguration(ATMContext context) {
        super(context)
    }

    public void appTypeArchive(AppArchiveType type, String path) {
        appTypeArchive(type, new File(path))
    }

    public void appTypeArchive(AppArchiveType type, File path) {
        appTypeArchive(new AppArchiveInfo(type, path))
    }

    public void appTypeArchive(AppArchiveInfo info) {
        atm.preds.nonNull(info, 'appTypeArchive info')
        atm.preds.isFile(info.filePath, 'appTypeArchive info.filePath')
        mAppArchiveInfo = info
    }

    @Nullable
    public AppArchiveInfo getAppArchive() {
        return mAppArchiveInfo
    }

}