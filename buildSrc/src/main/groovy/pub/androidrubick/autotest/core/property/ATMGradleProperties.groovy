package pub.androidrubick.autotest.core.property

@SuppressWarnings("GroovyUnusedDeclaration")
class ATMGradleProperties {

    public static getAppPath() {
        return ['APP_PATH', 'ARCHIVE_PATH']
    }

    public static getTestAppPath() {
        return ['TEST_APP_PATH', 'TEST_ARCHIVE_PATH']
    }

    public static final String UNINSTALL_OLD = 'UNINSTALL_OLD'
}