package pub.androidrubick.autotest.android.attachment.installer

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.android.model.UninstallInfo
import pub.androidrubick.autotest.core.ATMContext

/**
 * 卸载应用的基础实现
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GrMethodMayBeStatic"])
public class AndroidUninstallBaseHandler extends BaseAndroidAttachment implements AndroidUninstallHandler {

    public AndroidUninstallBaseHandler(ATMContext context) {
        super(context)
    }

    @Override
    public void uninstall(@NonNull UninstallInfo uninstallInfo) {
        performOnBeforeUninstallRemote(uninstallInfo)
        try {
            adbUninstall(uninstallInfo)
            performOnAdbUninstallSuccess(uninstallInfo)

            // 等待几秒，确保安装后的一些状态
            atm.logI('sleep for uninstalling...')
            sleep(3000)

            performOnAfterUninstallStable(uninstallInfo)
        } catch (Throwable e) {
            performOnAdbUninstallFailed(uninstallInfo, e)
        }
    }

    protected final void adbUninstall(UninstallInfo uninstallInfo) {
        def installResult = androidSdk.adbShell.pm.uninstall(uninstallInfo).exec()
        installResult.checkNonEmptyText('adbUninstall')
        def successFlag = installResult.text.split('\n').find { text ->
            text.matches(/(?i).*success.*/)
        }
        atm.preds.isTrue(successFlag != null, 'adbUninstall <uninstalled?>')
    }

    protected void performOnBeforeUninstallRemote(UninstallInfo uninstallInfo) {
        this.mDoBeforeUninstallRemoteActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(uninstallInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    protected void performOnAdbUninstallSuccess(UninstallInfo uninstallInfo) {
        this.mAdbUninstallSuccessActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(uninstallInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    /**
     * just throw param <code>e</code>
     * @param e any exception
     */
    protected void performOnAdbUninstallFailed(UninstallInfo uninstallInfo, Throwable e) {
        throw e
    }

    protected void performOnAfterUninstallStable(UninstallInfo uninstallInfo) {
        this.mDoAfterUninstallStableActions.each { Closure closure ->
            int paramCount = closure.getMaximumNumberOfParameters()
            switch (paramCount) {
                case 1:
                    closure.call(uninstallInfo)
                    break
                case 0:
                default:
                    closure.call()
                    break
            }
        }
    }

    private List<Closure> mDoBeforeUninstallRemoteActions = new ArrayList()
    public void doBeforeUninstallRemote(Closure closure) {
        mDoBeforeUninstallRemoteActions.add(closure)
    }

    private List<Closure> mAdbUninstallSuccessActions = new ArrayList()
    public void doOnAdbUninstallSuccess(Closure closure) {
        mAdbUninstallSuccessActions.add(closure)
    }

    private List<Closure> mDoAfterUninstallStableActions = new ArrayList()
    public void doAfterUninstallStable(Closure closure) {
        mDoAfterUninstallStableActions.add(closure)
    }
}