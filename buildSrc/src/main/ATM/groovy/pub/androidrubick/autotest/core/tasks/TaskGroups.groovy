package pub.androidrubick.autotest.core.tasks

@SuppressWarnings(["GroovyUnusedDeclaration", "GroovyUnusedDeclaration"])
abstract class TaskGroups {

    /**
     * prepare environment, states,
     */
    static final String GROUP_PREPARE = 'prepare ATM'
    /**
     * collect environment, states,
     */
    static final String GROUP_COLLECT = 'collect ATM'
    /**
     * install app / test app to devices
     */
    static final String GROUP_INSTALL = 'install ATM'
    /**
     * uninstall app / test app to devices
     */
    static final String GROUP_UNINSTALL = 'uninstall ATM'
    /**
     * launch app / test app on devices
     */
    static final String GROUP_LAUNCH = 'launch ATM'
    /**
     * launch app / test app on devices
     */
    static final String GROUP_UPLOAD = 'upload ATM'

}