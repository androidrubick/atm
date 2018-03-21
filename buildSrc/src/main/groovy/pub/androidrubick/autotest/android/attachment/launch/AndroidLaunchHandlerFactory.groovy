package pub.androidrubick.autotest.android.attachment.launch

import pub.androidrubick.autotest.android.attachment.BaseAndroidAdapter
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmConfig
import pub.androidrubick.autotest.android.attachment.autoconfirm.AutoConfirmUtil
import pub.androidrubick.autotest.core.ATMContext

/**
 * 用于安装应用时的机型适配处理
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public class AndroidLaunchHandlerFactory extends BaseAndroidAdapter {

    private final AutoConfirmUtil mAutoConfirmUtil
    public AndroidLaunchHandlerFactory(ATMContext context) {
        super(context)
        mAutoConfirmUtil = new AutoConfirmUtil(context)
    }

    public final AndroidLaunchHandler factory() {
        AndroidLaunchBaseHandler handler = new AndroidLaunchBaseHandler(context)

        if (isHuawei()) {
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
        def config = AutoConfirmConfig.newConfig(
                name: name,
                packages: packages,
                sync: true,
                delayTime: 2000,
                periodTime: 1000,
                loopCount: 3,
                confirmBtnTextFilter: { btn ->
                    def btnText = btn.text
                    def ret = ['授权'].contains(btnText)
                    ret = ret || 'permit'.equalsIgnoreCase(btnText)
                    if (confirmBtnTextFilter != null) {
                        ret = ret || confirmBtnTextFilter(btn)
                    }
                    return ret
                }
        )
        mAutoConfirmUtil.performAutoConfirm(config)
    }

}
