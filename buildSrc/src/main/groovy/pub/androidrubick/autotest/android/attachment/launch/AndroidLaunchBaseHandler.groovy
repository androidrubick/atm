package pub.androidrubick.autotest.android.attachment.launch

import android.content.Intent
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext

/**
 * 定义android_sdk命令行调用UI相关的方法
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
public class AndroidLaunchBaseHandler extends BaseAndroidAttachment implements AndroidLaunchHandler {

    public AndroidLaunchBaseHandler(ATMContext context) {
        super(context)
    }

    @Override
    public void launch(Intent intent) {
        android_sdk.installer.checkPkgInstalled(launchInfo.pkg)

        performOnBeforeLaunch(launchInfo)
        adb_shell("am start -n \"${launchInfo.pkg}/${launchInfo.mainActivity}\" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER").exec()
                .checkSuccess('launchApp')
        performOnLaunchSuccess(launchInfo)

        // 等待几秒，确保启动后的一些状态
        atm.logI('sleep for launching...')
        sleep(1000)

        performOnLaunchStable(launchInfo)
    }

    private void performOnBeforeLaunch(LaunchInfo launchInfo) {
        this.mDoBeforeLaunchActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(launchInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    private void performOnLaunchSuccess(LaunchInfo launchInfo) {
        this.mLaunchSuccessActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(launchInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    private void performOnLaunchStable(LaunchInfo launchInfo) {
        this.mDoAfterLaunchStableActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(launchInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    private List<Closure> mDoBeforeLaunchActions = new ArrayList()
    /**
     * 启动应用执行之前执行
     */
    public void doBeforeLaunch(Closure closure) {
        mDoBeforeLaunchActions.add(closure)
    }

    private List<Closure> mLaunchSuccessActions = new ArrayList()
    /**
     * 启动应用成功后立马执行
     */
    public void doOnLaunchSuccess(Closure closure) {
        mLaunchSuccessActions.add(closure)
    }

    private List<Closure> mDoAfterLaunchStableActions = new ArrayList()
    /**
     * 启动应用成功后，等待一段时间（比如启动动画等操作完成）之后执行
     */
    public void doAfterLaunchStable(Closure closure) {
        mDoAfterLaunchStableActions.add(closure)
    }
}