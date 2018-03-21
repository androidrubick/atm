package pub.androidrubick.autotest.android.attachment.installer

import pub.androidrubick.autotest.android.attachment.BaseAndroidAdapter
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmConfig
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmUtil
import pub.androidrubick.autotest.core.ATMContext

/**
 * 用于安装应用时的机型适配处理
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidUninstallHandlerFactory extends BaseAndroidAdapter {

    private final AutoConfirmUtil mAutoConfirmUtil
    public AndroidUninstallHandlerFactory(ATMContext context) {
        super(context)
        mAutoConfirmUtil = new AutoConfirmUtil(context)
    }

    public final AndroidUninstallHandler factory() {
        AndroidUninstallBaseHandler handler = new AndroidUninstallBaseHandler(this.context)

        handler.doBeforeUninstallRemote {
            project.ext {
                ADB_UNINSTALL_TMP_CONFIG = null
            }
            // 有些机器会弹出确认框，用脚本的方式自动确认
            autoConfirmUninstall()
        }

        handler.doOnAdbUninstallSuccess {
            ADB_UNINSTALL_TMP_CONFIG?.done()
        }

        return handler
    }

    private void autoConfirmUninstall() {
        // TODO: 适配机型，如5.0.*手机文本乱码问题
        def config = ADB_UNINSTALL_TMP_CONFIG = AutoConfirmConfig.newConfig(
                name: 'autoConfirmUninstall',
                packages: ['com.android.packageinstaller'],
                confirmBtnTextFilter: { btn ->
                    def btnText = btn.text
                    def ret = ['卸载', '完成', '确定', '确认', '允许', '同意'].contains(btnText)
                    ret = ret || 'ok'.equalsIgnoreCase(btnText) || 'confirm'.equalsIgnoreCase(btnText)
                    ret = ret || 'uninstall'.equalsIgnoreCase(btnText) || 'done'.equalsIgnoreCase(btnText)
                    ret = ret || ['下一步'].contains(btnText) || 'next'.equalsIgnoreCase(btnText)
                    return ret
                }
        )
        mAutoConfirmUtil.performAutoConfirm(config)
    }

}
