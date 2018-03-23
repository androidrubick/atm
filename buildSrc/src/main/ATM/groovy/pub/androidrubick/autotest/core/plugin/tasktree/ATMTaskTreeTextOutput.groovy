package pub.androidrubick.autotest.core.plugin.tasktree

import pub.androidrubick.autotest.core.ATMContext
import pub.androidrubick.autotest.core.attachment.BaseAttachment

/**
 *
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedAssignment"])
class ATMTaskTreeTextOutput extends BaseAttachment implements TaskTreeTextOutput {

    ATMTaskTreeTextOutput(ATMContext context) {
        super(context)
    }

    protected final List mCachedTexts = []
    public void text(Object text) {
        mCachedTexts << text
    }

    @Override
    void println() {
        atm.logI(mCachedTexts.join())
        mCachedTexts.clear()
    }

    @Override
    void printTaskName(Object text, boolean isFirst) {
        this.text(text)
    }

    @Override
    void printGraph(Object text) {
        this.text(text)
    }
}