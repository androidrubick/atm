package pub.androidrubick.autotest.android.attachment.cmd

import android.support.annotation.NonNull
import pub.androidrubick.autotest.android.attachment.BaseAndroidAttachment
import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.cmd.ExecProcBuilder
import pub.androidrubick.autotest.core.attachment.cmd.ExecResult
import pub.androidrubick.autotest.core.model.Point
import pub.androidrubick.autotest.core.model.Rect

@SuppressWarnings("GroovyUnusedDeclaration")
class AdbShellInput extends BaseAndroidAttachment {

    AdbShellInput(ATMContext context) {
        super(context)
    }

    public ExecProcBuilder builder(String command) {
        return androidSdk.adbShell.builder("input $command")
    }

    public ExecResult click(@NonNull Point point) {
        atm.log("perform click: ${point}")
        return builder("tap ${point.x} ${point.y}").exec()
                .checkSuccess('perform click')
    }

    public ExecResult click(@NonNull Rect rect) {
        atm.log("perform click: ${rect}")
        return this.click(rect.center())
    }

    public ExecResult scroll(@NonNull Point fromP, @NonNull Point toP) {
        atm.log("perform scroll: ${fromP} -> ${toP}")
        return builder("swipe ${fromP.x} ${fromP.y} ${toP.x} ${toP.y}").exec()
                .checkSuccess('perform scroll')
    }

}