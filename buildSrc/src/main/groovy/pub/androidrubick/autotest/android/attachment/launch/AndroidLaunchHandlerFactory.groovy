package pub.androidrubick.autotest.android.attachment.launch

import pub.androidrubick.autotest.android.attachment.BaseAndroidAdapter
import pub.androidrubick.autotest.core.ATMContext

/**
 * 用于安装应用时的机型适配处理
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidLaunchHandlerFactory extends BaseAndroidAdapter {

    public AndroidLaunchHandlerFactory(ATMContext context) {
        super(context)
    }

    public final AndroidLaunchHandler factory() {
        AndroidLaunchBaseHandler handler = new AndroidLaunchBaseHandler(context)

        if (isHawei()) {
            handler.doAfterLaunchStable {
                autoConfirmHaweiPermission()
            }
        }

        if (isXiaomi()) {
            handler.doAfterLaunchStable {
                autoConfirmMIUIPermission()
            }
        }
        return handler
    }

    /**
     * 小米的自动授权
     */
    private void autoConfirmMIUIPermission() {
        autoConfirmPermission('MIUIPermissionConfirm', ['com.lbe.security.miui'],
                { btn ->
                    btn.id == 'com.lbe.security.miui:id/accept'
                }
        )
    }

    /**
     * 华为的自动授权
     */
    private void autoConfirmHaweiPermission() {
        autoConfirmPermission('HaweiPermissionConfirm', ['com.huawei.systemmanager'],
                { btn ->
                    btn.id == 'com.huawei.systemmanager:id/btn_allow'
                }
        )
    }

    private void autoConfirmPermission(name, packages, confirmBtnTextFilter = null) {
        def config = AutoConfirmConfig.newDef()
        config.name = name
        config.packages = packages
        config.sync = true
        config.delayTime = 2000
        config.periodTime = 1000
        config.loopCount = 3
        config.confirmBtnTextFilter = { btn ->
            def btnText = btn.text
            def ret = ['授权'].contains(btnText)
            ret = ret || 'permit'.equalsIgnoreCase(btnText)
            if (confirmBtnTextFilter != null) {
                ret = ret || confirmBtnTextFilter(btn)
            }
            return ret
        }
        android_sdk.ui.autoConfirm.performAutoConfirm(config)
    }

}
