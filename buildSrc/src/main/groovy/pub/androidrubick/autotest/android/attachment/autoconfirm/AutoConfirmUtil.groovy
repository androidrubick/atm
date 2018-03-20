package pub.androidrubick.autotest.android.attachment.autoconfirm

import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext

import static pub.androidrubick.autotest.core.util.Utils.isEmpty

/**
 * <p>
 * Created by Yin Yong on 2018/3/12.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration", "GroovyUnnecessaryContinue"])
public class AutoConfirmUtil extends BaseAndroidAttachment {

    public AutoConfirmUtil(ATMContext context) {
        super(context)
    }

    /**
     * @param config , map or object，包含：
     * `doneFlag`: boolean, 是否预想的操作已完成，若完成，则不再进行循环等待；
     * `loopCount`: int, 总循环次数，默认为5；
     * `delayTime`: long, 第一次循环前的等待时间，默认为5000，单位为毫秒；
     * `periodTime`: long, 两次循环之间的等待时间，默认为2000，单位为毫秒；
     * `packages`: 数组两次循环之间的等待时间，默认为2000，单位为毫秒；
     */
    public void performAutoConfirm(AutoConfirmConfig config) {
        this.autoConfirmInternal(config)
    }

    private void autoConfirmInternal(AutoConfirmConfig config) {
        if (config.sync) {
            autoConfirmInternalSync(config)
        } else {
            Thread.start {
                autoConfirmInternalSync(config)
            }
        }
    }

    private void autoConfirmInternalSync(AutoConfirmConfig config) {
        long beginTime = System.currentTimeMillis()
        int curLoopCount = 0
        int loopCount = config.loopCount
        sleep(config.delayTime)
        while (loopCount-- > 0 && !config.doneFlag) {
            curLoopCount++
            if (config.maxLoopCount != null && config.maxLoopCount > 0 && curLoopCount > config.maxLoopCount) {
                break
            }

            sleep(config.periodTime)
            if (config.doneFlag) {
                break
            }
            if (config.timeout != null && config.timeout > 0 && (System.currentTimeMillis() - beginTime) >= config.timeout) {
                break
            }

            def dumpFileInLocal = androidSdk.adbShell.uiautomator.dumpUIFile2Local()
            def hierarchy = new XmlParser().parseText(dumpFileInLocal.text)
            if (isEmpty(hierarchy)) {
                continue
            }

            if (!isEmpty(config.packages)) {
                def isTargetPkg = hierarchy.'**'.find { node ->
                    // node.'@package' == 'com.android.packageinstaller'
                    config.packages.contains(node.'@package')
                }

                if (isEmpty(isTargetPkg)) {
                    atm.log("[$config.name] Not one of <config.packages> package")
                    continue
                }
            }

            def btns = hierarchy.'**'.findAll { node ->
                node.'@class' == 'android.widget.Button'
            }

            atm.log("[$config.name] Buttons in hierarchy: $btns")
            if (!isEmpty(btns)) {
                def confirmBtns = btns.findAll { btn ->
                    def nc = btn.'@text'
                    def ret = ['确定', '确认', '允许', '同意', '接受'].contains(nc)
                    ret = ret || 'ok'.equalsIgnoreCase(nc) || 'confirm'.equalsIgnoreCase(nc)
                    ret = ret || 'allow'.equalsIgnoreCase(nc) || 'agree'.equalsIgnoreCase(nc)
                    ret = ret || 'accept'.equalsIgnoreCase(nc)
                    if (config.confirmBtnTextFilter != null) {
                        def btnParam = [text: nc, id: btn.'@resource-id', pkg: btn.'@package']
                        ret = ret || config.confirmBtnTextFilter(btnParam)
                    }
                    return ret
                }

                atm.log("[$config.name] Confirm Buttons in hierarchy: $confirmBtns")

                if (!isEmpty(confirmBtns)) {
                    confirmBtns.each { btn ->
                        atm.log("[$config.name] ConfirmBtn bounds: ${androidSdk.adbShell.uiautomator.parseNodeRect(btn)}")
                        androidSdk.adbShell.uiautomator.performClick(btn)
                    }
                    loopCount++
                    continue
                }
            }

            def scrollView = hierarchy.'**'.find { node ->
                node.'@class' == 'android.widget.ScrollView'
            }

            atm.log("[$config.name] Found ScrollView in hierarchy")

            if (!isEmpty(scrollView)) {
                // performAction ACTION_SCROLL_FORWARD
                androidSdk.adbShell.uiautomator.performScrollV(scrollView)
                loopCount++
                continue
            }
        }
    }
}
