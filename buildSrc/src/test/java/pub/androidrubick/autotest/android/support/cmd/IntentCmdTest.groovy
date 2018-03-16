package pub.androidrubick.autotest.android.support.cmd

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import pub.androidrubick.autotest.core.app.AppArchiveType

/**
 * {@doc}
 *
 * Created by Yin Yong on 2018/3/16.
 */
class IntentCmdTest extends GroovyTestCase {
    void testToCmdString() {
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .addCategory(Intent.CATEGORY_DEFAULT)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .setComponent(ComponentName.createRelative('com.vip.demo', '.MainAct'))
                .putExtras(new Bundle().putInt('a', 1).putString('b', null))
                .setData(Uri.fromFile(new File('./我是中国人')))

        println new IntentCmd(intent).toCmdString()
    }

    void testToCmdString2() {
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .putExtras(new Bundle().putInt('a', 1).putString('b', null)
                            .putIntArray('cc1', [1, 2, 3] as int[])
                            .putBooleanArray('cc2', [true, false, true] as boolean[])
                            .putSerializable('c', IntentCmdTest))

        println new IntentCmd(intent).toCmdString()
    }

    void test() {
        println AppArchiveType.allAvailableOf('android')
        println AppArchiveType.allAvailableOf(null)
    }
}
