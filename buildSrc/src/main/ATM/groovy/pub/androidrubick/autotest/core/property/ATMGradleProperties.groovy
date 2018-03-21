package pub.androidrubick.autotest.core.property

import pub.androidrubick.autotest.core.attachment.app.AppArchiveType

@SuppressWarnings("GroovyUnusedDeclaration")
class ATMGradleProperties {

    public static getAppPath() {
        return ['APP_PATH', 'ARCHIVE_PATH']
    }

    public static getTestAppPath() {
        return ['TEST_APP_PATH', 'TEST_ARCHIVE_PATH']
    }

    public static List<String> collectAppPathProps(AppArchiveType type) {
        return type?.name?.toLowerCase()?.contains('test') ? testAppPath : appPath
    }

    /**
     * flag, uninstall old app / test app before install new app / test app
     */
    public static final String UNINSTALL_OLD = 'UNINSTALL_OLD'

    /**
     * flag, skip install task
     */
    public static final String SKIP_INSTALL = 'SKIP_INSTALL'
}