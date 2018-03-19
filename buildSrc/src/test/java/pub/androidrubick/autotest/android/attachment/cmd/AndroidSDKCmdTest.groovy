package pub.androidrubick.autotest.android.attachment.cmd

import android.content.Intent
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import pub.androidrubick.autotest.android.AndroidPlugin
import pub.androidrubick.autotest.android.AndroidSdk

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/19.
 */
class AndroidSDKCmdTest extends GroovyTestCase {

    private Project project
    private AndroidSdk androidSdk
    {
        project = ProjectBuilder.builder().withName('test').build()
        project.pluginManager.apply(AndroidPlugin.class)

        androidSdk = AndroidSdk.fromProject(project)

    }

    void testAdb() {
        println androidSdk.cmd.adb('version')
        println androidSdk.cmd.adb.version()
        println androidSdk.cmd.adb.devices()
    }

    void testAdbShell() {
        println androidSdk.cmd.adb.shell
        println androidSdk.cmd.adb.shell('ls -la')
        println androidSdk.cmd.adb.shell.pm('list packages')
        println androidSdk.cmd.adb.shell.pm('list packages com.taobao.taobao').exec()
        Intent intent = new Intent("test.ACTION")
        println androidSdk.cmd.adb.shell.am.broadcast(intent)
        println androidSdk.cmd.adb.shell.am.broadcast(intent).exec()

        println androidSdk.cmd.adb.util.rmRemoteTmpFiles()
        println androidSdk.cmd.adb.util.info

//        println androidSdk.cmd.adb.shell.props()
//        println androidSdk.cmd.adb.shell.prop('ro.product.brand')
    }

}
