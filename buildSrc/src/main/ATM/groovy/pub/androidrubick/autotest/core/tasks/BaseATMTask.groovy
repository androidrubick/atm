package pub.androidrubick.autotest.core.tasks

import org.gradle.api.DefaultTask
import pub.androidrubick.autotest.core.ATM

/**
 * Base task used in this lib.
 * 
 * <p>
 * Created by Yin Yong on 2018/3/16.
 *
 * @since 1.0.0
 */
@SuppressWarnings("GroovyUnusedDeclaration")
public abstract class BaseATMTask extends DefaultTask {

    public BaseATMTask() {
    }

    /**
     * provide `atm` for tasks
     */
    public final ATM getAtm() {
        return ATM.fromProject(project)
    }
}