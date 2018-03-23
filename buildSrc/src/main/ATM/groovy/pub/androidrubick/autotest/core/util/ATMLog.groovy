package pub.androidrubick.autotest.core.util

import android.support.annotation.NonNull
import org.gradle.api.Project
import pub.androidrubick.autotest.android.util.ATAndroidLog
import pub.androidrubick.autotest.core.ATMContext

@SuppressWarnings(["GroovyUnusedDeclaration", "UnnecessaryQualifiedReference"])
public abstract class ATMLog {

    public static final String IMPL_INJECT_NAME = "ATMLogger"

    /**
     * root project
     * @param rootProject root project
     */
    static synchronized void init(@NonNull Project rootProject) {
        int longestTagLen = rootProject.allprojects.collect { it.name ? it.name.length() : 0 }.max()
        rootProject.allprojects { project ->
            project.ext."$IMPL_INJECT_NAME" = new ATMLogImpl(project, longestTagLen)
        }

        ATAndroidLog.use(fromProject(rootProject))
    }

    /**
     * get the {@link ATMLog} implementation from target project
     * @param project target project
     * @return the {@link ATMLog} implementation
     * @since 1.0.0
     */
    public static ATMLog fromProject(@NonNull Project project) {
        return project.ext."$IMPL_INJECT_NAME"
    }

    /**
     * get the {@link ATMLog} implementation from target ATM context
     * @param context target ATM context
     * @return the {@link ATMLog} implementation
     * @since 1.0.0
     */
    public static ATMLog fromContext(@NonNull ATMContext context) {
        return fromProject(context.project)
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    protected void internalLog(messages) {
        println null == messages ? 'null' : messages.join(' ')
    }

    public abstract void log(ATMLogLevel level, Object...msgs)

    private static class ATMLogImpl extends ATMLog {

        private Project project
        private int longestTagLen
        ATMLogImpl(Project project, int longestTagLen) {
            this.project = project
            this.longestTagLen = longestTagLen
        }

        @Override
        void log(ATMLogLevel level, Object... msgs) {
            def innerMsg = newMsgArrWithTag(level)
            if (msgs != null) {
                msgs.each { msg ->
                    def lines = String.valueOf(msg).split('\n')
                    innerMsg << lines[0]
                    for (int i = 1; i < lines.length; i++) {
                        internalLog(innerMsg)
                        innerMsg = newMsgArrWithTag(level)
                        innerMsg << lines[i]
                    }
                }
            } else {
                innerMsg << 'null'
            }
            internalLog(innerMsg)
        }

        List newMsgArrWithTag(ATMLogLevel level) {
            def innerMsg = []
            // innerMsg << '\033[0;32m'
            innerMsg << generateTagStr(level, project.name)
            // innerMsg << '\033[0m'
            return innerMsg
        }

        String generateTagStr(ATMLogLevel level, String proName) {
            def firstChar = level?.name()?.substring(0, 1)?.toUpperCase() ?: '?'
            return [firstChar, '/', proName, '$'].join('').padLeft(longestTagLen + 3, ' ')
        }
    }

}