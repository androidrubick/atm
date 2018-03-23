package pub.androidrubick.autotest.core.plugin.tasktree

import org.gradle.internal.logging.text.StyledTextOutput
import org.gradle.internal.logging.text.StyledTextOutput.Style
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
class GradleReportTextOutput extends BaseAttachment implements TaskTreeTextOutput {

    protected final StyledTextOutput mTextOutput
    GradleReportTextOutput(ATMContext context, StyledTextOutput output) {
        super(context)
        mTextOutput = output
    }

    protected final List mCachedTexts = []
    public void text(Object text) {
        mCachedTexts << text
    }

    @Override
    void println() {
        mTextOutput.println()
    }

    @Override
    void printTaskName(Object text, boolean isFirst) {
        mTextOutput.withStyle(isFirst ? Style.Identifier : Style.Normal).text(text)
    }

    @Override
    void printGraph(Object text) {
        mTextOutput.withStyle(Style.Info).text(text)
    }
}