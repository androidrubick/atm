package pub.androidrubick.autotest.android.tasks.launch

import android.content.ComponentName
import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.attachment.launch.AndroidLaunchHandlerFactory
import pub.androidrubick.autotest.core.ATMContext

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidLauncher extends BaseAndroidAttachment {

    private final AndroidLaunchHandlerFactory mLauncherFactory
    public AndroidLauncher(ATMContext context) {
        super(context)
        mLauncherFactory = new AndroidLaunchHandlerFactory(context)
    }

    /**
     * 根据已有的启动app的信息，启动机器/模拟机上的应用
     *
     * @param intent 包含 component或者action等
     * @throws RuntimeException 当执行命令行发生错误时抛出异常
     */
    public void launch(@NonNull ComponentName component) {
        mLauncherFactory.factory().launch(component)
    }

}