package pub.androidrubick.autotest.core

import org.gradle.api.DefaultTask

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

    public BaseTask() {
    }

    /**
     * provide `atm` for tasks
     */
    public final ATM getAtm() {
        return project.atm
    }
}