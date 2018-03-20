package pub.androidrubick.autotest.android.attachment.launch

import android.content.ComponentName
import android.content.Intent
import android.support.annotation.NonNull
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
    public void launch(@NonNull ComponentName component) {
        androidSdk.adbShell.pm.ensurePkgInstalled(component.packageName)

        performOnBeforeLaunch(component)
        androidSdk.adbShell.am.startActivity(Intent.makeMainActivity(component))
        performOnLaunchSuccess(launchInfo)

        // 等待几秒，确保启动后的一些状态
        atm.logI('sleep for launching...')
        sleep(1000)

        performOnLaunchStable(launchInfo)
    }

    private void performOnBeforeLaunch(ComponentName launchInfo) {
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

    private void performOnLaunchSuccess(ComponentName launchInfo) {
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

    private void performOnLaunchStable(Intent ComponentName) {
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