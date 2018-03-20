package pub.androidrubick.autotest.core.util

@SuppressWarnings("GroovyUnusedDeclaration")
public class FileUtils {

    private FileUtils() {}

    public static void ensureFileNotExists(File file) {
        if (null != file) {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete()
                } else {
                    file.deleteDir()
                }
            }
        }
    }

    public static void ensureDirExists(File dir) {
        if (null != dir) {
            if (dir.isFile()) {
                ensureFileNotExists(dir)
            }
            dir.mkdirs()
        }
    }
}