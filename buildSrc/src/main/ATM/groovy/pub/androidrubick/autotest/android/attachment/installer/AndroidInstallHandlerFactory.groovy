package pub.androidrubick.autotest.android.attachment.installer

import pub.androidrubick.autotest.android.attachment.BaseAndroidAdapter
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmConfig
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmUtil
import pub.androidrubick.autotest.core.ATMContext

/**
 * 用于安装应用时的机型适配处理
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidInstallHandlerFactory extends BaseAndroidAdapter {

    private final AutoConfirmUtil mAutoConfirmUtil
    public AndroidInstallHandlerFactory(ATMContext context) {
        super(context)
        mAutoConfirmUtil = new AutoConfirmUtil(context)
    }

    public final AndroidInstallHandler factory() {
        AndroidInstallBaseHandler handler = new AndroidInstallBaseHandler(this.context)

        handler.doBeforeInstallRemote {
            project.ext {
                ADB_INSTALL_TMP_CONFIG = null
            }
            // 有些机器会弹出确认框，用脚本的方式自动确认
            autoConfirmInstall()
        }

        handler.doOnAdbInstallSuccess {
            ADB_INSTALL_TMP_CONFIG?.done()
        }

        if (isXiaomi()) {
            // do nothing now
        }

        if (isSamsung()) {
            handler.doAfterInstallStable {
                autoConfirmSamsungPermissionAfterInstall()
            }
        }
        return handler
    }

    private void autoConfirmInstall() {
        // TODO: 适配机型，如5.0.*手机文本乱码问题
        def config = ADB_INSTALL_TMP_CONFIG = AutoConfirmConfig.newConfig(
                name: 'autoConfirmInstall',
                packages: ['com.android.packageinstaller'],
                confirmBtnTextFilter: { btn ->
                    def btnText = btn.text
                    def ret = ['安装', '完成', '确定', '确认', '允许', '同意'].contains(btnText)
                    ret = ret || 'ok'.equalsIgnoreCase(btnText) || 'confirm'.equalsIgnoreCase(btnText)
                    ret = ret || 'install'.equalsIgnoreCase(btnText) || 'done'.equalsIgnoreCase(btnText)
                    ret = ret || ['下一步'].contains(btnText) || 'next'.equalsIgnoreCase(btnText)
                    return ret
                }
        )
        mAutoConfirmUtil.performAutoConfirm(config)
    }

    /**
     * 三星系列手机安装后，会弹出"应用程序许可界面"，此处直接处理掉
     */
    private void autoConfirmSamsungPermissionAfterInstall() {
        mAutoConfirmUtil.performAutoConfirm(AutoConfirmConfig.newConfig(
                name: 'SamsungPermissionConfirm',
                packages: ['com.android.settings'],
                sync: true,
                delayTime: 1000,
                periodTime: 1000,
                loopCount: 3,
                confirmBtnTextFilter: { btn ->
                    btn.text == '??' || btn.id == 'com.android.settings:id/button'
                }
        ))
    }

}
