package pub.androidrubick.autotest.android.attachment.installer

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidInstaller extends BaseAndroidAttachment {

    private final AndroidInstallHandlerFactory mInstallFactory
    public AndroidInstaller(ATMContext context) {
        super(context)
        mInstallFactory = new AndroidInstallHandlerFactory(context)
    }

    /**
     * 执行命令，安装本地的源文件
     *
     * @param localFile 本地的源文件
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public void installLocal(File localFile) {
        mInstallFactory.factory().installLocal(localFile)
    }

}